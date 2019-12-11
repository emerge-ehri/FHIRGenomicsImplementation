package edu.bcm.hgsc.fhir.services;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import edu.bcm.hgsc.fhir.models.HgscReport;
import edu.bcm.hgsc.fhir.models.Variant;
import edu.bcm.hgsc.fhir.utils.FhirResourcesMappingUtils;
import edu.bcm.hgsc.fhir.utils.FileUtils;
import edu.bcm.hgsc.fhir.utils.JsonMappingUtil;
import org.apache.log4j.Logger;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileUploadServiceImpl {

    private static Logger logger = Logger.getLogger(FileUploadServiceImpl.class);

    FileUtils fileUtils = new FileUtils();

    FhirContext ctx = FhirContext.forR4();
    IGenericClient client = ctx.newRestfulGenericClient(fileUtils.loadPropertyValue("application.properties", "jpaserver.url"));

    public ArrayList<String> createFhirResources(File file) {

        JsonMappingUtil util = new JsonMappingUtil();
        HgscReport report = util.readFromHgscReportJsonFile(file);

        Map<String, Object> fhirResources = this.createIndividualFhirResources(report);
        return this.createBundle(fhirResources, report);
    }

    private Map<String, Object> createIndividualFhirResources(HgscReport hgscReport) {

        HashMap<String, String> mappingConfig = fileUtils.readMapperConfig(getClass().getClassLoader().getResource("mapping.properties").getPath());
        Map<String, Object> newResources = null;
        try {
            newResources = new FhirResourcesMappingUtils().mapping(mappingConfig, hgscReport);
        } catch (java.text.ParseException e) {
            logger.error("Failed to Parse Date data type:", e);
        }
        return newResources;
    }

    private ArrayList<String> createBundle(Map<String, Object> fhirResources, HgscReport hgscReport) {

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

        PlanDefinition planDefinition = (PlanDefinition) fhirResources.get("PlanDefinition");
        planDefinition.setId(IdDt.newRandomUuid());

        organizationHGSC.setPartOf(new Reference(organizationBCM.getId()));
        specimen.addRequest(new Reference(serviceRequest.getId()));
        serviceRequest.addSpecimen(new Reference(specimen.getId()));
        serviceRequest.addPerformer(new Reference(organizationHGSC.getId()));
        serviceRequest.addInstantiatesCanonical(planDefinition.getId());
        practitionerRole.setPractitioner(new Reference(geneticistOne.getId()));
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
//        dxCNVVariants.addBasedOn(new Reference(serviceRequest.getId()));
//        dxCNVVariants.setSubject(new Reference(patient.getId()));
//        dxCNVVariants.setSpecimen(new Reference(specimen.getId()));
//        dxCNVVariants.addPerformer(new Reference(organization.getId()));
//        dxCNVVariants.addNote(new Annotation().setAuthor(new Reference(organization.getId())).setText("Comments"));

        if(hgscReport.getVariants() != null && hgscReport.getVariants().size() > 0) {
            for(Variant v : hgscReport.getVariants()) {
                Observation snpVariant = dxSNPINDELVariants.get(v.getGene());
                snpVariant.setId(IdDt.newRandomUuid());
                snpVariant.addBasedOn(new Reference(serviceRequest.getId()));
                snpVariant.setSubject(new Reference(patient.getId()));
                snpVariant.setSpecimen(new Reference(specimen.getId()));
                snpVariant.addPerformer(new Reference(organizationHGSC.getId()));
                snpVariant.addNote(new Annotation().setAuthor(new Reference(organizationHGSC.getId())).setText(v.getNotes()));

                Observation inhDisPath = obsInhDisPaths.get(v.getGene());
                inhDisPath.setId(IdDt.newRandomUuid());
                inhDisPath.addBasedOn(new Reference(serviceRequest.getId()));
                inhDisPath.setSubject(new Reference(patient.getId()));
                inhDisPath.addPerformer(new Reference(organizationHGSC.getId()));
                inhDisPath.addNote(new Annotation().setText(v.getNotes()));
                inhDisPath.setSpecimen(new Reference(specimen.getId()));

                inhDisPath.addDerivedFrom(new Reference(snpVariant.getId()));
                obsOverall.addDerivedFrom(new Reference(inhDisPath.getId()));
                dxPanel.addHasMember(new Reference(snpVariant.getId()))
                        .addHasMember(new Reference(inhDisPath.getId()));
                //task.setReasonReference(new Reference(inhDisPath.getId()));

                dxSNPINDELVariants.put(v.getGene(), snpVariant);
                obsInhDisPaths.put(v.getGene(), inhDisPath);
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

        task.setFor(new Reference(patient.getId()));
        serviceRequest.setRequester(new Reference(practitionerRole.getId()));

        DiagnosticReport diagnosticReport = (DiagnosticReport)fhirResources.get("DiagnosticReport");
        diagnosticReport.addBasedOn(new Reference(serviceRequest.getId()));
        diagnosticReport.addPerformer(new Reference(organizationHGSC.getId()));
        diagnosticReport.addResultsInterpreter(new Reference(practitionerRoleGeneticistOne.getId()))
                .addResultsInterpreter(new Reference(practitionerRoleGeneticistTwo.getId()));
        diagnosticReport.addSpecimen(new Reference(specimen.getId()));
        diagnosticReport.setSubject(new Reference(patient.getId()));
        //Extension
        Extension ext2 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/recommendedAction",
                new Reference(task.getId()));
        diagnosticReport.addExtension(ext2);
        diagnosticReport.addResult(new Reference(obsOverall.getId()))
                .addResult(new Reference(dxPanel.getId()))
                .addResult(new Reference(pgxPanel.getId()))
                .addResult(new Reference(obsReportComment.getId()));

        //Narrative
        //ctx.setNarrativeGenerator(new DefaultThymeleafNarrativeGenerator());
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
        planDefinition.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(planDefinition))));
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
                //.setIfNoneExist("identifier=" + serviceRequest.getIdentifier().get(0).getValue())
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
                .setFullUrl(organizationCHP.getId())
                .setResource(organizationCHP)
                .getRequest()
                .setUrl("Organization")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(obsOverall.getId())
                .setResource(obsOverall)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);

//        bundle.addEntry()
//                .setFullUrl(dxCNVVariants.getId())
//                .setResource(dxCNVVariants)
//                .getRequest()
//                .setUrl("Observation")
//                .setMethod(Bundle.HTTPVerb.POST);

        if(hgscReport.getVariants() != null && hgscReport.getVariants().size() > 0) {
            for(Variant v : hgscReport.getVariants()) {
                Observation snpV = dxSNPINDELVariants.get(v.getGene());
                Observation inhDP = obsInhDisPaths.get(v.getGene());

                snpV.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                        .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(snpV))));
                inhDP.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                        .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(inhDP))));

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
                .setFullUrl(orderingPhysician.getId())
                .setResource(orderingPhysician)
                .getRequest()
                .setUrl("Practitioner")
                .setIfNoneExist("identifier=" + orderingPhysician.getIdentifier().get(0).getValue())
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(practitionerRole.getId())
                .setResource(practitionerRole)
                .getRequest()
                .setUrl("PractitionerRole")
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

        bundle.addEntry()
                .setFullUrl(task.getId())
                .setResource(task)
                .getRequest()
                .setUrl("Task")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(planDefinition.getId())
                .setResource(planDefinition)
                .getRequest()
                .setUrl("PlanDefinition")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setResource(diagnosticReport)
                .getRequest()
                .setUrl("DiagnosticReport")
                .setIfNoneExist("identifier=" + diagnosticReport.getIdentifier().get(0).getValue())
                .setMethod(Bundle.HTTPVerb.POST);

        Bundle resp = client.transaction().withBundle(bundle).execute();

        //Convert bundle resp to actual resource URL and send as htmlResponse
        JSONObject response = null;
        try {
            response = (JSONObject) new JSONParser().parse(ctx.newJsonParser().encodeResourceToString(resp));
            logger.info("Create Bundle:" + response);
        } catch (ParseException e) {
            logger.error("Failed to convert bundle result to JSON response.", e);
        }

        JSONArray urlArr = (JSONArray) response.get("link");
        JSONObject jsonUrl = (JSONObject) urlArr.get(0);
        String serverURL = jsonUrl.get("url").toString();

        ArrayList<String> resultURLArr = new ArrayList<String>();
        JSONArray resourcesURLArr = (JSONArray) response.get("entry");
        for (Object o : resourcesURLArr) {
            JSONObject jso = (JSONObject) o;
            JSONObject jso2 = (JSONObject) jso.get("response");
            resultURLArr.add(serverURL + "/" + jso2.get("location"));
        }

        return resultURLArr;
    }

    private DiagnosticReport searchDiagnosticReportById(String projectDir, String diagnosticReportId) {
        DiagnosticReport diagnosticReport = client.read().resource(DiagnosticReport.class).withId(diagnosticReportId).execute();

        String string = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(diagnosticReport);
        System.out.println(string);

        byte[] byteArray = diagnosticReport.getPresentedFormFirstRep().getData();
        File outputFile = new File(projectDir + "outputFile.pdf");

        // save byte[] into the output file
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return diagnosticReport;
    }
}
