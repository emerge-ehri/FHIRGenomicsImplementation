package edu.bcm.hgsc.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.BasicAuthInterceptor;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import edu.bcm.hgsc.fhir.models.HgscReport;
import edu.bcm.hgsc.fhir.models.Variant;
import edu.bcm.hgsc.fhir.utils.*;
import org.apache.log4j.Logger;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FHIRClientS3 {

    private static Logger logger = Logger.getLogger(FHIRClientS3.class);

    FileUtils fileUtils = new FileUtils();
    FhirContext ctx = FhirContext.forR4();
    String serverURL = fileUtils.loadPropertyValue("application.properties", "jpaserver.url");

    public static void main(String[] args) {
        FHIRClientS3 fhirClientS3 = new FHIRClientS3();

        HashMap<String, HashMap<String, String>> loincCodeMap = new LoincCodeUtil().loadLoincCodeToMap();
        ArrayList<String> resultList = fhirClientS3.createFhirResourcesFromS3(args[0], loincCodeMap);
        fhirClientS3.outputFile(resultList, args[0]);
    }

    private ArrayList<String> createFhirResourcesFromS3(String orgName, HashMap<String, HashMap<String, String>> loincCodeMap) {

        ArrayList<String> resourceURLList = new ArrayList<String>();
        HashMap<String, String> pdfFileMap = null;
        HashMap<String, String> excidFileMap = null;

        if(orgName.equals("NU")) {
            pdfFileMap = fileUtils.getFileNameMapFromS3(orgName, ".pdf");
            excidFileMap = fileUtils.getFileNameMapFromS3(orgName, ".txt");
        }

        for(String key : fileUtils.getJSONFileListFromS3(orgName)) {
            logger.info("Start loading " + key + " from S3...");
            byte[] bytes = fileUtils.getS3ObjectAsByteArray(key);
            HgscReport report = new JsonMappingUtil().readHgscReportJson(bytes);

            Map<String, Object> fhirResources = null;

            if(orgName.equals("NU")) {
                String mapKey = key.split("\\.")[1];
                String pdfFileKey = pdfFileMap.get(mapKey);
                String excidFileKey = excidFileMap.get(mapKey);
                if(pdfFileKey == null || pdfFileKey.equals("")) {
                    logger.error("Failed to find PDF File with MapKey " + mapKey + " from S3: Related PDF File is missing.");
                    continue;
                }
                if(excidFileKey == null || excidFileKey.equals("")) {
                    logger.error("Failed to find Excid File with MapKey " + mapKey + " from S3: Related Excid File is missing.");
                    continue;
                }

                byte[] pdfBytes = fileUtils.getS3ObjectAsByteArray(pdfFileKey);
                byte[] excidBytes = fileUtils.getS3ObjectAsByteArray(excidFileKey);
                if(pdfBytes == null || pdfBytes.length == 0 || excidBytes == null || excidBytes.length == 0) {
                    logger.error("Failed to load " + key + " from S3: Related Pdf File or Excid File is missing or empty.");
                    continue;
                }

                fhirResources = createIndividualFhirResources(report, pdfBytes, excidBytes, loincCodeMap);
            } else {
                fhirResources = createIndividualFhirResources(report, null, null, loincCodeMap);
            }

            resourceURLList.add("Start creating FHIR resources from " + key + " in S3 bucket...");
            resourceURLList.addAll(createBundle(fhirResources, report, orgName, key, loincCodeMap));
            logger.info("Completed loading " + key + " from S3 and creating FHIR resources.");
        }

        return resourceURLList;
    }

    private Map<String, Object> createIndividualFhirResources(HgscReport hgscReport, byte[] pdfBytes, byte[] excidBytes, HashMap<String, HashMap<String, String>> loincCodeMap) {

        HashMap<String, String> mappingConfig = fileUtils.readMapperConfig("mapping.properties");
        Map<String, Object> newResources = null;
        try {
            newResources = new FhirResourcesMappingUtils().mapping(mappingConfig, hgscReport, pdfBytes, excidBytes, loincCodeMap);
        } catch (java.text.ParseException e) {
            logger.error("Failed to Parse Date data type:", e);
        }
        return newResources;
    }

    private ArrayList<String> createBundle(Map<String, Object> fhirResources, HgscReport hgscReport, String orgName, String key, HashMap<String, HashMap<String, String>> loincCodeMap) {

        Patient patient = (Patient)fhirResources.get("Patient");
        // Give the patient a temporary UUID so that other resources in the transaction can refer to it
        patient.setId(IdDt.newRandomUuid());

        Specimen specimen = (Specimen)fhirResources.get("Specimen");
        specimen.setId(IdDt.newRandomUuid());
        specimen.setSubject(new Reference(patient.getId()));

        ServiceRequest serviceRequest = (ServiceRequest) fhirResources.get("ServiceRequest");
        serviceRequest.setId(IdDt.newRandomUuid());
        serviceRequest.setSubject(new Reference(patient.getId()));

        Organization organizationHGSC = (Organization) fhirResources.get("OrganizationHGSC");
        organizationHGSC.setId(IdDt.newRandomUuid());

        Organization organizationBCM = (Organization) fhirResources.get("OrganizationBCM");
        organizationBCM.setId(IdDt.newRandomUuid());

        Organization organizationCHP = (Organization) fhirResources.get("OrganizationCHP");
        organizationCHP.setId(IdDt.newRandomUuid());

        Observation obsOverall = (Observation) fhirResources.get("ObsOverall");
        obsOverall.setId(IdDt.newRandomUuid());

//        Observation dxCNVVariants = (Observation) fhirResources.get("DxCNVVariants");
//        dxCNVVariants.setId(IdDt.newRandomUuid());

        HashMap<String, Observation> dxSNPINDELVariants = (HashMap<String, Observation>) fhirResources.get("DxSNPINDELVariants");

        Observation obsReportComment = (Observation) fhirResources.get("ObsReportComment");
        obsReportComment.setId(IdDt.newRandomUuid());

        Observation dxPanel = (Observation) fhirResources.get("DxPanel");
        dxPanel.setId(IdDt.newRandomUuid());

        Observation pgxPanel = (Observation) fhirResources.get("PgxPanel");
        pgxPanel.setId(IdDt.newRandomUuid());

        Observation pgxResult_1001 = (Observation) fhirResources.get("PgxResult_1001");
        pgxResult_1001.setId(IdDt.newRandomUuid());
        Observation pgxResult_2001 = (Observation) fhirResources.get("PgxResult_2001");
        pgxResult_2001.setId(IdDt.newRandomUuid());
        Observation pgxResult_3001 = (Observation) fhirResources.get("PgxResult_3001");
        pgxResult_3001.setId(IdDt.newRandomUuid());
        Observation pgxResult_4001 = (Observation) fhirResources.get("PgxResult_4001");
        pgxResult_4001.setId(IdDt.newRandomUuid());
        Observation pgxResult_5001 = (Observation) fhirResources.get("PgxResult_5001");
        pgxResult_5001.setId(IdDt.newRandomUuid());
        Observation pgxResult_6001 = (Observation) fhirResources.get("PgxResult_6001");
        pgxResult_6001.setId(IdDt.newRandomUuid());

        Observation pgxGeno_1001 = (Observation) fhirResources.get("PgxGeno_1001");
        pgxGeno_1001.setId(IdDt.newRandomUuid());
        Observation pgxGeno_2001 = (Observation) fhirResources.get("PgxGeno_2001");
        pgxGeno_2001.setId(IdDt.newRandomUuid());
        Observation pgxGeno_3001 = (Observation) fhirResources.get("PgxGeno_3001");
        pgxGeno_3001.setId(IdDt.newRandomUuid());
        Observation pgxGeno_4001 = (Observation) fhirResources.get("PgxGeno_4001");
        pgxGeno_4001.setId(IdDt.newRandomUuid());
        Observation pgxGeno_5001 = (Observation) fhirResources.get("PgxGeno_5001");
        pgxGeno_5001.setId(IdDt.newRandomUuid());
        Observation pgxGeno_6001 = (Observation) fhirResources.get("PgxGeno_6001");
        pgxGeno_6001.setId(IdDt.newRandomUuid());
        Observation pgxGeno_7001 = (Observation) fhirResources.get("PgxGeno_7001");
        pgxGeno_7001.setId(IdDt.newRandomUuid());

        HashMap<String, Observation> obsInhDisPaths = (HashMap<String, Observation>) fhirResources.get("ObsInhDisPaths");

        Practitioner geneticistOne = (Practitioner) fhirResources.get("GeneticistOne");
        geneticistOne.setId(IdDt.newRandomUuid());
        Practitioner geneticistTwo = (Practitioner) fhirResources.get("GeneticistTwo");
        geneticistTwo.setId(IdDt.newRandomUuid());
        Practitioner orderingPhysician = (Practitioner) fhirResources.get("OrderingPhysician");
        orderingPhysician.setId(IdDt.newRandomUuid());

        PractitionerRole practitionerRole = (PractitionerRole) fhirResources.get("PractitionerRole");
        practitionerRole.setId(IdDt.newRandomUuid());
        PractitionerRole practitionerRoleGeneticistOne = (PractitionerRole) fhirResources.get("PractitionerRoleGeneticistOne");
        practitionerRoleGeneticistOne.setId(IdDt.newRandomUuid());
        PractitionerRole practitionerRoleGeneticistTwo = (PractitionerRole) fhirResources.get("PractitionerRoleGeneticistTwo");
        practitionerRoleGeneticistTwo.setId(IdDt.newRandomUuid());

        Task task = (Task) fhirResources.get("Task");
        task.setId(IdDt.newRandomUuid());
        task.setFor(new Reference(patient.getId()));

        PlanDefinition planDefinition = (PlanDefinition) fhirResources.get("PlanDefinition");
        planDefinition.setId(IdDt.newRandomUuid());

        organizationHGSC.setPartOf(new Reference(organizationBCM.getId()));
        specimen.addRequest(new Reference(serviceRequest.getId()));
        serviceRequest.addSpecimen(new Reference(specimen.getId()));
        serviceRequest.addPerformer(new Reference(organizationHGSC.getId()));
        serviceRequest.addInstantiatesCanonical(planDefinition.getId());
        practitionerRole.setPractitioner(new Reference(orderingPhysician.getId()));
        practitionerRole.setOrganization(new Reference(organizationCHP.getId()));
        practitionerRoleGeneticistOne.setPractitioner(new Reference(geneticistOne.getId()));
        practitionerRoleGeneticistOne.setOrganization(new Reference(organizationHGSC.getId()));
        practitionerRoleGeneticistTwo.setPractitioner(new Reference(geneticistTwo.getId()));
        practitionerRoleGeneticistTwo.setOrganization(new Reference(organizationHGSC.getId()));

        dxPanel.addBasedOn(new Reference(serviceRequest.getId()));
        dxPanel.setSubject(new Reference(patient.getId()));
        dxPanel.setSpecimen(new Reference(specimen.getId()));
        dxPanel.addPerformer(new Reference(organizationHGSC.getId()));

        pgxPanel.addBasedOn(new Reference(serviceRequest.getId()));
        pgxPanel.setSubject(new Reference(patient.getId()));
        pgxPanel.setSpecimen(new Reference(specimen.getId()));
        pgxPanel.addPerformer(new Reference(organizationHGSC.getId()));

        obsOverall.addBasedOn(new Reference(serviceRequest.getId()));
        obsOverall.setSubject(new Reference(patient.getId()));
        obsOverall.setSpecimen(new Reference(specimen.getId()));
        obsOverall.addPerformer(new Reference(organizationHGSC.getId()));

        if(orgName.equals("JHU")) {
            specimen.addIdentifier(new Identifier().setSystem("https://emerge.hgsc.bcm.edu/").setValue(hgscReport.getPatientID() + "--specimen"));
            serviceRequest.addIdentifier(new Identifier().setSystem("https://emerge.hgsc.bcm.edu/").setValue(hgscReport.getPatientID() + "--serviceRequest"));
            obsOverall.addIdentifier(new Identifier().setSystem("https://emerge.hgsc.bcm.edu/").setValue(hgscReport.getReportIdentifier() + "--overallInterpretation"));
        }

        if(hgscReport.getOverallInterpretation().toLowerCase().equals("positive")
                && hgscReport.getVariants() != null && hgscReport.getVariants().size() > 0) {
            for(Variant v : hgscReport.getVariants()) {
                Observation snpVariant = dxSNPINDELVariants.get(v.getExternalId());
                snpVariant.setId(IdDt.newRandomUuid());
                snpVariant.addBasedOn(new Reference(serviceRequest.getId()));
                snpVariant.setSubject(new Reference(patient.getId()));
                snpVariant.setSpecimen(new Reference(specimen.getId()));
                snpVariant.addPerformer(new Reference(organizationHGSC.getId()));
                snpVariant.addNote(new Annotation().setAuthor(new Reference(organizationHGSC.getId())).setText(v.getNotes()));

                if(orgName.equals("JHU")) {
                    snpVariant.addIdentifier(new Identifier().setSystem("https://emerge.hgsc.bcm.edu/").setValue(hgscReport.getReportIdentifier() + "--variant--" + v.getExternalId().replaceAll(",", "")));
                }

//                dxCNVVariants.addBasedOn(new Reference(serviceRequest.getId()));
//                dxCNVVariants.setSubject(new Reference(patient.getId()));
//                dxCNVVariants.setSpecimen(new Reference(specimen.getId()));
//                dxCNVVariants.addPerformer(new Reference(organization.getId()));
//                dxCNVVariants.addNote(new Annotation().setAuthor(new Reference(organizationHGSC.getId())).setText(v.getNotes()));

                Observation inhDisPath = obsInhDisPaths.get(v.getExternalId());
                inhDisPath.setId(IdDt.newRandomUuid());
                inhDisPath.addBasedOn(new Reference(serviceRequest.getId()));
                inhDisPath.setSubject(new Reference(patient.getId()));
                inhDisPath.addPerformer(new Reference(organizationHGSC.getId()));
                inhDisPath.addNote(new Annotation().setText(v.getNotes()));
                inhDisPath.setSpecimen(new Reference(specimen.getId()));

                if(orgName.equals("JHU")) {
                    inhDisPath.addIdentifier(new Identifier().setSystem("https://emerge.hgsc.bcm.edu/").setValue(hgscReport.getReportIdentifier() + "--inheritedDiseasePathogenicity--" + v.getExternalId().replaceAll(",", "")));
                }

                inhDisPath.addDerivedFrom(new Reference(snpVariant.getId()));
                obsOverall.addDerivedFrom(new Reference(inhDisPath.getId()));
                dxPanel.addHasMember(new Reference(snpVariant.getId()))
                        .addHasMember(new Reference(inhDisPath.getId()));
                task.setReasonReference(new Reference(inhDisPath.getId()));

                dxSNPINDELVariants.put(v.getExternalId(), snpVariant);
                obsInhDisPaths.put(v.getExternalId(), inhDisPath);
            }
        }

        pgxGeno_1001.setSubject(new Reference(patient.getId()));
        pgxGeno_1001.addPerformer(new Reference(organizationHGSC.getId()));
        pgxGeno_1001.addBasedOn(new Reference(serviceRequest.getId()));
        pgxGeno_1001.setSpecimen(new Reference(specimen.getId()));

        pgxGeno_2001.setSubject(new Reference(patient.getId()));
        pgxGeno_2001.addPerformer(new Reference(organizationHGSC.getId()));
        pgxGeno_2001.addBasedOn(new Reference(serviceRequest.getId()));
        pgxGeno_2001.setSpecimen(new Reference(specimen.getId()));

        pgxGeno_3001.setSubject(new Reference(patient.getId()));
        pgxGeno_3001.addPerformer(new Reference(organizationHGSC.getId()));
        pgxGeno_3001.addBasedOn(new Reference(serviceRequest.getId()));
        pgxGeno_3001.setSpecimen(new Reference(specimen.getId()));

        pgxGeno_4001.setSubject(new Reference(patient.getId()));
        pgxGeno_4001.addPerformer(new Reference(organizationHGSC.getId()));
        pgxGeno_4001.addBasedOn(new Reference(serviceRequest.getId()));
        pgxGeno_4001.setSpecimen(new Reference(specimen.getId()));

        pgxGeno_5001.setSubject(new Reference(patient.getId()));
        pgxGeno_5001.addPerformer(new Reference(organizationHGSC.getId()));
        pgxGeno_5001.addBasedOn(new Reference(serviceRequest.getId()));
        pgxGeno_5001.setSpecimen(new Reference(specimen.getId()));

        pgxGeno_6001.setSubject(new Reference(patient.getId()));
        pgxGeno_6001.addPerformer(new Reference(organizationHGSC.getId()));
        pgxGeno_6001.addBasedOn(new Reference(serviceRequest.getId()));
        pgxGeno_6001.setSpecimen(new Reference(specimen.getId()));

        pgxGeno_7001.setSubject(new Reference(patient.getId()));
        pgxGeno_7001.addPerformer(new Reference(organizationHGSC.getId()));
        pgxGeno_7001.addBasedOn(new Reference(serviceRequest.getId()));
        pgxGeno_7001.setSpecimen(new Reference(specimen.getId()));

        obsReportComment.setSubject(new Reference(patient.getId()));
        obsReportComment.addPerformer(new Reference(organizationHGSC.getId()));
        pgxResult_1001.setSubject(new Reference(patient.getId()));
        pgxResult_1001.addPerformer(new Reference(organizationHGSC.getId()));
        pgxResult_1001.addDerivedFrom(new Reference(pgxGeno_1001.getId()));
        pgxResult_2001.setSubject(new Reference(patient.getId()));
        pgxResult_2001.addPerformer(new Reference(organizationHGSC.getId()));
        pgxResult_2001.addDerivedFrom(new Reference(pgxGeno_2001.getId()));
        pgxResult_3001.setSubject(new Reference(patient.getId()));
        pgxResult_3001.addPerformer(new Reference(organizationHGSC.getId()));
        pgxResult_3001.addDerivedFrom(new Reference(pgxGeno_3001.getId()));
        pgxResult_4001.setSubject(new Reference(patient.getId()));
        pgxResult_4001.addPerformer(new Reference(organizationHGSC.getId()));
        pgxResult_4001.addDerivedFrom(new Reference(pgxGeno_4001.getId()));
        pgxResult_5001.setSubject(new Reference(patient.getId()));
        pgxResult_5001.addPerformer(new Reference(organizationHGSC.getId()));
        pgxResult_5001.addDerivedFrom(new Reference(pgxGeno_5001.getId()));
        pgxResult_6001.setSubject(new Reference(patient.getId()));
        pgxResult_6001.addPerformer(new Reference(organizationHGSC.getId()));
        pgxResult_6001.addDerivedFrom(new Reference(pgxGeno_6001.getId()));
        pgxResult_6001.addDerivedFrom(new Reference(pgxGeno_7001.getId()));

        pgxPanel.addHasMember(new Reference(pgxResult_1001.getId()))
                .addHasMember(new Reference(pgxResult_2001.getId()))
                .addHasMember(new Reference(pgxResult_3001.getId()))
                .addHasMember(new Reference(pgxResult_4001.getId()))
                .addHasMember(new Reference(pgxResult_5001.getId()))
                .addHasMember(new Reference(pgxResult_6001.getId()));

        dxPanel.addHasMember(new Reference(obsOverall.getId()));
        //.addHasMember(new Reference(dxCNVVariants.getId()));

        if(orgName.equals("NU")) {
            serviceRequest.setRequester(new Reference(practitionerRole.getId()));
        }

        DiagnosticReport diagnosticReport = (DiagnosticReport)fhirResources.get("DiagnosticReport");
        diagnosticReport.addBasedOn(new Reference(serviceRequest.getId()));
        diagnosticReport.addPerformer(new Reference(organizationHGSC.getId()));
        diagnosticReport.addResultsInterpreter(new Reference(practitionerRoleGeneticistOne.getId()))
                .addResultsInterpreter(new Reference(practitionerRoleGeneticistTwo.getId()));
        diagnosticReport.addSpecimen(new Reference(specimen.getId()));
        diagnosticReport.setSubject(new Reference(patient.getId()));
        //Extension
        if(hgscReport.getOverallInterpretation().toLowerCase().equals("positive")) {
            Extension ext2 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/recommendedAction",
                    new Reference(task.getId()));
            diagnosticReport.addExtension(ext2);
        }

        diagnosticReport.addResult(new Reference(obsOverall.getId()))
                .addResult(new Reference(dxPanel.getId()))
                .addResult(new Reference(pgxPanel.getId()))
                .addResult(new Reference(obsReportComment.getId()));

        //Narrative
        patient.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(patient))));
        specimen.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(specimen))));
        serviceRequest.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(serviceRequest))));

        //hard code organization narrative
        organizationHGSC.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue("<div><p><b>Generated Narrative with Details</b></p><p><b>id</b>: Human Genome Sequencing Center Clinical Laboratory</p><p>One Baylor Plaza • Houston • TX 77030</p><p>Phone: 713.798.6539 • Fax: 713.798.5741 • www.hgsc.bcm.edu • email: questions@hgsc.bcm.edu</p><p>CAP# 8004250 / CLIA# 45D2027450</p></div>")));
        organizationBCM.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue("<div><p><b>Generated Narrative with Details</b></p><p><b>id</b>: Baylor College of Medicine</p><p>One Baylor Plaza • Houston • TX 77030</p><p>Phone: (713) 798-4951 • https://www.bcm.edu/ </p></div>")));
        organizationCHP.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue("<div><p><b>Generated Narrative with Details</b></p><p><b>id</b>: Children's Hospital of Philadelphia</p><p>3401 Civic Center Blvd, Philadelphia, PA 19104</p></div>")));

        obsOverall.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(obsOverall))));
        //dxCNVVariants.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
        //.setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(dxCNVVariants))));

        obsReportComment.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(obsReportComment))));
        dxPanel.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(dxPanel))));
        pgxPanel.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(pgxPanel))));
        pgxResult_1001.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(pgxResult_1001))));
        pgxResult_2001.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(pgxResult_2001))));
        pgxResult_3001.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(pgxResult_3001))));
        pgxResult_4001.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(pgxResult_4001))));
        pgxResult_5001.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(pgxResult_5001))));
        pgxResult_6001.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(pgxResult_6001))));

        pgxGeno_1001.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(pgxGeno_1001))));
        pgxGeno_2001.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(pgxGeno_2001))));
        pgxGeno_3001.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(pgxGeno_3001))));
        pgxGeno_4001.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(pgxGeno_4001))));
        pgxGeno_5001.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(pgxGeno_5001))));
        pgxGeno_6001.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(pgxGeno_6001))));
        pgxGeno_7001.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(pgxGeno_7001))));

        geneticistOne.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(geneticistOne))));
        geneticistTwo.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(geneticistTwo))));
        orderingPhysician.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(orderingPhysician))));

        practitionerRole.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(practitionerRole))));
        practitionerRoleGeneticistOne.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(practitionerRoleGeneticistOne))));
        practitionerRoleGeneticistTwo.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(practitionerRoleGeneticistTwo))));

        task.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(task))));
        diagnosticReport.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(diagnosticReport))));


        // Create a bundle that will be used as a transaction
        Bundle bundle = new Bundle();
        bundle.setType(Bundle.BundleType.TRANSACTION);

        bundle.addEntry()
                .setFullUrl(patient.getId())
                .setResource(patient)
                .getRequest()
                .setUrl("Patient")
                .setIfNoneExist("identifier=" + patient.getIdentifier().get(0).getValue())
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(specimen.getId())
                .setResource(specimen)
                .getRequest()
                .setUrl("Specimen")
                .setIfNoneExist("identifier=" + specimen.getIdentifier().get(0).getValue())
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(serviceRequest.getId())
                .setResource(serviceRequest)
                .getRequest()
                .setUrl("ServiceRequest")
                .setIfNoneExist("identifier=" + serviceRequest.getIdentifier().get(0).getValue())
                .setMethod(Bundle.HTTPVerb.POST);

        if(orgName.equals("JHU")) {
            bundle.addEntry()
                    .setFullUrl(obsOverall.getId())
                    .setResource(obsOverall)
                    .getRequest()
                    .setUrl("Observation")
                    .setIfNoneExist("identifier=" + obsOverall.getIdentifier().get(0).getValue())
                    .setMethod(Bundle.HTTPVerb.POST);
        }else{
            bundle.addEntry()
                    .setFullUrl(obsOverall.getId())
                    .setResource(obsOverall)
                    .getRequest()
                    .setUrl("Observation")
                    .setMethod(Bundle.HTTPVerb.POST);
        }

//        bundle.addEntry()
//                .setFullUrl(dxCNVVariants.getId())
//                .setResource(dxCNVVariants)
//                .getRequest()
//                .setUrl("Observation")
//                .setMethod(Bundle.HTTPVerb.POST);

        if(hgscReport.getOverallInterpretation().toLowerCase().equals("positive")
                && hgscReport.getVariants() != null && hgscReport.getVariants().size() > 0) {
            for(Variant v : hgscReport.getVariants()) {
                Observation snpV = dxSNPINDELVariants.get(v.getExternalId());
                Observation inhDP = obsInhDisPaths.get(v.getExternalId());

                snpV.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                        .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(snpV))));
                inhDP.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                        .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(inhDP))));

                if(orgName.equals("JHU")) {
                    bundle.addEntry()
                            .setFullUrl(snpV.getId())
                            .setResource(snpV)
                            .getRequest()
                            .setUrl("Observation")
                            .setIfNoneExist("identifier=" + snpV.getIdentifier().get(0).getValue())
                            .setMethod(Bundle.HTTPVerb.POST);
                    bundle.addEntry()
                            .setFullUrl(inhDP.getId())
                            .setResource(inhDP)
                            .getRequest()
                            .setUrl("Observation")
                            .setIfNoneExist("identifier=" + inhDP.getIdentifier().get(0).getValue())
                            .setMethod(Bundle.HTTPVerb.POST);
                }else{
                    bundle.addEntry()
                            .setFullUrl(snpV.getId())
                            .setResource(snpV)
                            .getRequest()
                            .setUrl("Observation")
                            .setMethod(Bundle.HTTPVerb.POST);
                    bundle.addEntry()
                            .setFullUrl(inhDP.getId())
                            .setResource(inhDP)
                            .getRequest()
                            .setUrl("Observation")
                            .setMethod(Bundle.HTTPVerb.POST);
                }
            }
        }

        bundle.addEntry()
                .setFullUrl(obsReportComment.getId())
                .setResource(obsReportComment)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(dxPanel.getId())
                .setResource(dxPanel)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(pgxPanel.getId())
                .setResource(pgxPanel)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(pgxResult_1001.getId())
                .setResource(pgxResult_1001)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(pgxResult_2001.getId())
                .setResource(pgxResult_2001)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(pgxResult_3001.getId())
                .setResource(pgxResult_3001)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(pgxResult_4001.getId())
                .setResource(pgxResult_4001)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(pgxResult_5001.getId())
                .setResource(pgxResult_5001)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(pgxResult_6001.getId())
                .setResource(pgxResult_6001)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);


        bundle.addEntry()
                .setFullUrl(pgxGeno_1001.getId())
                .setResource(pgxGeno_1001)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(pgxGeno_2001.getId())
                .setResource(pgxGeno_2001)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(pgxGeno_3001.getId())
                .setResource(pgxGeno_3001)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(pgxGeno_4001.getId())
                .setResource(pgxGeno_4001)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(pgxGeno_5001.getId())
                .setResource(pgxGeno_5001)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(pgxGeno_6001.getId())
                .setResource(pgxGeno_6001)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(pgxGeno_7001.getId())
                .setResource(pgxGeno_7001)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(geneticistOne.getId())
                .setResource(geneticistOne)
                .getRequest()
                .setUrl("Practitioner")
                .setIfNoneExist("identifier=" + geneticistOne.getIdentifier().get(0).getValue())
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(geneticistTwo.getId())
                .setResource(geneticistTwo)
                .getRequest()
                .setUrl("Practitioner")
                .setIfNoneExist("identifier=" + geneticistTwo.getIdentifier().get(0).getValue())
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(organizationHGSC.getId())
                .setResource(organizationHGSC)
                .getRequest()
                .setUrl("Organization")
                .setIfNoneExist("identifier=" + organizationHGSC.getIdentifier().get(0).getValue())
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(organizationBCM.getId())
                .setResource(organizationBCM)
                .getRequest()
                .setUrl("Organization")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(practitionerRoleGeneticistOne.getId())
                .setResource(practitionerRoleGeneticistOne)
                .getRequest()
                .setUrl("PractitionerRole")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(practitionerRoleGeneticistTwo.getId())
                .setResource(practitionerRoleGeneticistTwo)
                .getRequest()
                .setUrl("PractitionerRole")
                .setMethod(Bundle.HTTPVerb.POST);

        if(orgName.equals("NU")) {
            bundle.addEntry()
                    .setFullUrl(orderingPhysician.getId())
                    .setResource(orderingPhysician)
                    .getRequest()
                    .setUrl("Practitioner")
                    //.setIfNoneExist("identifier=" + orderingPhysician.getIdentifier().get(0).getValue())
                    .setMethod(Bundle.HTTPVerb.POST);
            bundle.addEntry()
                    .setFullUrl(organizationCHP.getId())
                    .setResource(organizationCHP)
                    .getRequest()
                    .setUrl("Organization")
                    .setMethod(Bundle.HTTPVerb.POST);
            bundle.addEntry()
                    .setFullUrl(practitionerRole.getId())
                    .setResource(practitionerRole)
                    .getRequest()
                    .setUrl("PractitionerRole")
                    .setMethod(Bundle.HTTPVerb.POST);
        }

        if(hgscReport.getOverallInterpretation().toLowerCase().equals("positive")) {
            bundle.addEntry()
                    .setFullUrl(task.getId())
                    .setResource(task)
                    .getRequest()
                    .setUrl("Task")
                    .setMethod(Bundle.HTTPVerb.POST);
        }

        bundle.addEntry()
                .setFullUrl(planDefinition.getId())
                .setResource(planDefinition)
                .getRequest()
                .setUrl("PlanDefinition")
                .setMethod(Bundle.HTTPVerb.POST);

        //Validate Bundle Fhir Resource Format
        FhirResourcesValidationUtils fhirResourcesValidationUtils = new FhirResourcesValidationUtils();
        JSONObject jsonBundle = null;
        try {
            jsonBundle = (JSONObject) new JSONParser().parse(ctx.newJsonParser().encodeResourceToString(bundle));
        } catch (ParseException e) {
            logger.error("Failed to convert bundle resources to JSON format for validation.", e);
            return null;
        }

        if(!fhirResourcesValidationUtils.validateBundleFhirResourceFormat(serverURL, jsonBundle.toJSONString())) {
            logger.error("Failed to validate FHIR resources format.");
            return null;
        }else{
            logger.info("Completed validating FHIR resources format.");
        }

        //Add DiagnosticReport to the bundle after FHIR format validation
        bundle.addEntry()
                .setResource(diagnosticReport)
                .getRequest()
                .setUrl("DiagnosticReport")
                .setIfNoneExist("identifier=" + diagnosticReport.getIdentifier().get(0).getValue())
                .setMethod(Bundle.HTTPVerb.POST);

        //Check security for jpaserver
        String hgscFhirServerUsername = fileUtils.loadPropertyValue("application.properties", "jpaserver.username");
        String hgscFhirServerPassword = fileUtils.loadPropertyValue("application.properties", "jpaserver.password");
        IClientInterceptor authInterceptor = new BasicAuthInterceptor(hgscFhirServerUsername, hgscFhirServerPassword);
        IGenericClient client = ctx.newRestfulGenericClient(serverURL);
        client.registerInterceptor(authInterceptor);

        //Send bundle to jpaserver
        Bundle resp = client.transaction().withBundle(bundle).execute();

        //Convert bundle resp to actual resource URL and send as htmlResponse
        JSONObject response = null;
        try {
            response = (JSONObject) new JSONParser().parse(ctx.newJsonParser().encodeResourceToString(resp));
            logger.info("Create Bundle:" + response);
        } catch (ParseException e) {
            logger.error("Failed to convert bundle result to JSON response.", e);
            return null;
        }

        ArrayList<String> resultURLArr = new ArrayList<String>();
        JSONArray resourcesURLArr = (JSONArray) response.get("entry");
        for (Object o : resourcesURLArr) {
            JSONObject jso = (JSONObject) o;
            JSONObject jso2 = (JSONObject) jso.get("response");
            resultURLArr.add(serverURL + "/" + jso2.get("location"));
        }

        try {
            if(!fhirResourcesValidationUtils.validateData(resultURLArr, hgscReport, client, orgName, loincCodeMap)) {
                logger.error("Failed to validate FHIR resources data.");
                return null;
            }else{
                logger.info("Completed validating FHIR resources data.");
            }
        } catch (java.text.ParseException e) {
            logger.error("Failed to validate FHIR resources data.", e);
            return null;
        }

        //Upload bundle to S3 for NU only
        if(orgName.equals("NU")) {
            String bundleString = ctx.newJsonParser().encodeResourceToString(bundle);
            String key_name = key;
            logger.info("Uploading bundle " + key_name + " to S3 bucket\n");

            final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
            String s3bucket = fileUtils.loadPropertyValue("application.properties", "s3.bucketname");

            try {
                s3.putObject(s3bucket + "/bundle", key_name, bundleString);
                logger.info("Completed uploading bundle " + key_name + " to S3 bucket for NU\n");
            } catch (AmazonServiceException e) {
                logger.error("PutS3Object for Fhir bundle Failed:" + e.getMessage());
            }
        }

        //POST to JHU server
        if(orgName.equals("JHU")) {
            JHUPostUtil jhuPostUtil = new JHUPostUtil();
            String jhuToken = jhuPostUtil.postForJHUToken();
            if(jhuToken == null || jhuToken.equals("")) {
                logger.error("Unable to get token from the JHU server.");
                return null;
            }

            JSONObject jhuBundle = null;
            //Send Bundle as BATCH for JHU
            //bundle.setType(Bundle.BundleType.BATCH);
            try {
                jhuBundle = (JSONObject) new JSONParser().parse(ctx.newJsonParser().encodeResourceToString(bundle));
            } catch (ParseException e) {
                logger.error("Failed to convert bundle resources to JSON format for POST request to JHU.", e);
                return null;
            }

            if(!jhuPostUtil.postForJHU(jhuBundle.toJSONString(), jhuToken)) {
                logger.error("Failed to Post Fhir resources to the JHU server.");
                return null;
            }else{
                logger.info("Completed Posting Fhir resources to the JHU server for the eMerge JSON file:" + key);
            }
        }

        return resultURLArr;
    }

    private void outputFile(ArrayList<String> resultList, String orgName) {

        try {Files.write(Paths.get(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()
                .replace("hgsc-fhir-client-s3.jar", "") + "FhirResources-" + orgName + ".txt"), resultList);
        } catch (IOException e) {
            logger.error("Failed to output created Fhir Resources to a file.", e);
        }
    }
}
