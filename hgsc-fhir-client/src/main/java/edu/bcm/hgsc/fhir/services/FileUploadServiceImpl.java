package edu.bcm.hgsc.fhir.services;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import edu.bcm.hgsc.fhir.models.HgscReport;
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
        HgscReport report = util.readFromEmergeReportJsonFile(file);

        Map<String, Object> fhirResources = this.createIndividualFhirResources(report);
        return this.createBundle(fhirResources, report);
    }

    public Map<String, Object> createIndividualFhirResources(HgscReport hgscReport) {

        HashMap<String, String> mappingConfig = fileUtils.readMapperConfig(getClass().getClassLoader().getResource("mapping.properties").getPath());
        Map<String, Object> newResources = null;
        try {
            newResources = new FhirResourcesMappingUtils().mapping(mappingConfig, hgscReport);
        } catch (java.text.ParseException e) {
            logger.error("Failed to Parse Date data type:", e);
        }
        return newResources;
    }

    public ArrayList<String> createBundle(Map<String, Object> fhirResources, HgscReport hgscReport) {

        Patient patient = (Patient)fhirResources.get("Patient");
        // Give the patient a temporary UUID so that other resources in the transaction can refer to it
        patient.setId(IdDt.newRandomUuid());

        Specimen specimen = (Specimen)fhirResources.get("Specimen");
        specimen.setId(IdDt.newRandomUuid());
        specimen.setSubject(new Reference(patient.getId()));

        ServiceRequest serviceRequest = (ServiceRequest) fhirResources.get("ServiceRequest");
        serviceRequest.setId(IdDt.newRandomUuid());
        serviceRequest.setSubject(new Reference(patient.getId()));

        Organization organization = (Organization) fhirResources.get("Organization");
        organization.setId(IdDt.newRandomUuid());

        Organization organizationBCM = (Organization) fhirResources.get("OrganizationBCM");
        organizationBCM.setId(IdDt.newRandomUuid());

        Observation obsOverall = (Observation) fhirResources.get("ObsOverall");
        obsOverall.setId(IdDt.newRandomUuid());

//        Observation dxCNVVariants = (Observation) fhirResources.get("DxCNVVariants");
//        dxCNVVariants.setId(IdDt.newRandomUuid());

        Observation dxSNPINDELVariants = (Observation) fhirResources.get("DxSNPINDELVariants");
        dxSNPINDELVariants.setId(IdDt.newRandomUuid());

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
        Observation pgxGeno_5001 = (Observation) fhirResources.get("PgxGeno_5001");
        pgxGeno_5001.setId(IdDt.newRandomUuid());
        Observation pgxGeno_6001 = (Observation) fhirResources.get("PgxGeno_6001");
        pgxGeno_6001.setId(IdDt.newRandomUuid());

        Observation obsInhDisPaths = (Observation) fhirResources.get("ObsInhDisPaths");
        obsInhDisPaths.setId(IdDt.newRandomUuid());

        Practitioner geneticistOne = (Practitioner) fhirResources.get("GeneticistOne");
        geneticistOne.setId(IdDt.newRandomUuid());
        Practitioner geneticistTwo = (Practitioner) fhirResources.get("GeneticistTwo");
        geneticistTwo.setId(IdDt.newRandomUuid());

        Task task = (Task) fhirResources.get("Task");
        task.setId(IdDt.newRandomUuid());

        PlanDefinition planDefinition = (PlanDefinition) fhirResources.get("PlanDefinition");
        planDefinition.setId(IdDt.newRandomUuid());

        organization.setPartOf(new Reference(organizationBCM.getId()));
        specimen.addRequest(new Reference(serviceRequest.getId()));
        serviceRequest.addSpecimen(new Reference(specimen.getId()));
        serviceRequest.addPerformer(new Reference(organization.getId()));
        serviceRequest.addInstantiatesCanonical(planDefinition.getId());
        //serviceRequest.setRequester(new Reference(????));

        dxPanel.addBasedOn(new Reference(serviceRequest.getId()));
        dxPanel.setSubject(new Reference(patient.getId()));
        dxPanel.setSpecimen(new Reference(specimen.getId()));
        dxPanel.addPerformer(new Reference(organization.getId()));

        pgxPanel.addBasedOn(new Reference(serviceRequest.getId()));
        pgxPanel.setSubject(new Reference(patient.getId()));
        pgxPanel.setSpecimen(new Reference(specimen.getId()));
        pgxPanel.addPerformer(new Reference(organization.getId()));

        obsOverall.addBasedOn(new Reference(serviceRequest.getId()));
        obsOverall.setSubject(new Reference(patient.getId()));
        obsOverall.setSpecimen(new Reference(specimen.getId()));
        obsOverall.addPerformer(new Reference(organization.getId()));
        obsOverall.addDerivedFrom(new Reference(obsInhDisPaths.getId()));
//        dxCNVVariants.addBasedOn(new Reference(serviceRequest.getId()));
//        dxCNVVariants.setSubject(new Reference(patient.getId()));
//        dxCNVVariants.setSpecimen(new Reference(specimen.getId()));
//        dxCNVVariants.addPerformer(new Reference(organization.getId()));
//        dxCNVVariants.addNote(new Annotation().setAuthor(new Reference(organization.getId())).setText("Comments"));
        dxSNPINDELVariants.addBasedOn(new Reference(serviceRequest.getId()));
        dxSNPINDELVariants.setSubject(new Reference(patient.getId()));
        dxSNPINDELVariants.setSpecimen(new Reference(specimen.getId()));
        dxSNPINDELVariants.addPerformer(new Reference(organization.getId()));
        dxSNPINDELVariants.addNote(new Annotation().setAuthor(new Reference(organization.getId())).setText(hgscReport.getVariants().get(0).getNotes()));

        pgxGeno_1001.setSubject(new Reference(patient.getId()));
        pgxGeno_1001.addPerformer(new Reference(organization.getId()));
        pgxGeno_2001.setSubject(new Reference(patient.getId()));
        pgxGeno_2001.addPerformer(new Reference(organization.getId()));
        pgxGeno_5001.setSubject(new Reference(patient.getId()));
        pgxGeno_5001.addPerformer(new Reference(organization.getId()));
        pgxGeno_6001.setSubject(new Reference(patient.getId()));
        pgxGeno_6001.addPerformer(new Reference(organization.getId()));

        obsReportComment.setSubject(new Reference(patient.getId()));
        obsReportComment.addPerformer(new Reference(organization.getId()));
        pgxResult_1001.setSubject(new Reference(patient.getId()));
        pgxResult_1001.addPerformer(new Reference(organization.getId()));
        pgxResult_1001.addDerivedFrom(new Reference(pgxGeno_1001.getId()));
        pgxResult_2001.setSubject(new Reference(patient.getId()));
        pgxResult_2001.addPerformer(new Reference(organization.getId()));
        pgxResult_2001.addDerivedFrom(new Reference(pgxGeno_2001.getId()));
        pgxResult_3001.setSubject(new Reference(patient.getId()));
        pgxResult_3001.addPerformer(new Reference(organization.getId()));
        pgxResult_4001.setSubject(new Reference(patient.getId()));
        pgxResult_4001.addPerformer(new Reference(organization.getId()));
        pgxResult_5001.setSubject(new Reference(patient.getId()));
        pgxResult_5001.addPerformer(new Reference(organization.getId()));
        pgxResult_5001.addDerivedFrom(new Reference(pgxGeno_5001.getId()));
        pgxResult_6001.setSubject(new Reference(patient.getId()));
        pgxResult_6001.addPerformer(new Reference(organization.getId()));
        pgxResult_6001.addDerivedFrom(new Reference(pgxGeno_6001.getId()));
        pgxPanel.addHasMember(new Reference(pgxResult_1001.getId()))
                .addHasMember(new Reference(pgxResult_2001.getId()))
                .addHasMember(new Reference(pgxResult_3001.getId()))
                .addHasMember(new Reference(pgxResult_4001.getId()))
                .addHasMember(new Reference(pgxResult_5001.getId()))
                .addHasMember(new Reference(pgxResult_6001.getId()));

        obsInhDisPaths.addBasedOn(new Reference(serviceRequest.getId()));
        obsInhDisPaths.setSubject(new Reference(patient.getId()));
        obsInhDisPaths.addPerformer(new Reference(organization.getId()));
        obsInhDisPaths.addNote(new Annotation().setAuthor(new Reference(organization.getId())).setText(hgscReport.getVariants().get(0).getNotes()));
        obsInhDisPaths.setSpecimen(new Reference(specimen.getId()));
        obsInhDisPaths.addDerivedFrom(new Reference(dxSNPINDELVariants.getId()));

        dxPanel.addHasMember(new Reference(obsOverall.getId()))
                .addHasMember(new Reference(obsInhDisPaths.getId()))
                .addHasMember(new Reference(dxSNPINDELVariants.getId()));

        task.setFor(new Reference(patient.getId()));

        DiagnosticReport diagnosticReport = (DiagnosticReport)fhirResources.get("DiagnosticReport");
        diagnosticReport.addBasedOn(new Reference(serviceRequest.getId()));
        diagnosticReport.addPerformer(new Reference(organization.getId()));
        diagnosticReport.addResultsInterpreter(new Reference(geneticistOne.getId()))
                .addResultsInterpreter(new Reference(geneticistTwo.getId()));
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
        organization.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(organization))));
        //hard code organization narrative
        //organization.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
        //.setDiv(new XhtmlNode().setValue("<div><p><b>Generated Narrative with Details</b></p><p><b>id</b>: Human Genome Sequencing Center Clinical Laboratory</p><p>One Baylor Plaza • Houston • TX 77030</p><p>Phone: 713.798.6539 • Fax: 713.798.5741 • www.hgsc.bcm.edu • email: questions@hgsc.bcm.edu</p><p>CAP# 8004250 / CLIA# 45D2027450</p></div>")));
        organizationBCM.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(organizationBCM))));
        obsOverall.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(obsOverall))));
        //dxCNVVariants.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                //.setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(dxCNVVariants))));
        dxSNPINDELVariants.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(dxSNPINDELVariants))));

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
        pgxGeno_5001.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(pgxGeno_5001))));
        pgxGeno_6001.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(pgxGeno_6001))));

        obsInhDisPaths.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(obsInhDisPaths))));

        geneticistOne.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(geneticistOne))));
        geneticistTwo.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(geneticistTwo))));
        //hard code organization narrative
        //geneticistOne.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                //.setDiv(new XhtmlNode().setValue("<div><p><b>Generated Narrative with Details</b></p><p><b>Practitioner Name</b>: David Murdock, M.D., F.A.C.M.G.</p><p><b>Title</b>: ABMGG Certified Molecular Geneticist, Assistant Laboratory Director</p>")));
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
                //.setIfNoneExist("name=Yan&identifier=1234567")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(specimen.getId())
                .setResource(specimen)
                .getRequest()
                .setUrl("Specimen")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(serviceRequest.getId())
                .setResource(serviceRequest)
                .getRequest()
                .setUrl("ServiceRequest")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(organization.getId())
                .setResource(organization)
                .getRequest()
                .setUrl("Organization")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(organizationBCM.getId())
                .setResource(organizationBCM)
                .getRequest()
                .setUrl("Organization")
                //.setIfNoneExist("name=Baylor College of Medicine")
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

        bundle.addEntry()
                .setFullUrl(dxSNPINDELVariants.getId())
                .setResource(dxSNPINDELVariants)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);

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
                .setFullUrl(obsInhDisPaths.getId())
                .setResource(obsInhDisPaths)
                .getRequest()
                .setUrl("Observation")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(geneticistOne.getId())
                .setResource(geneticistOne)
                .getRequest()
                .setUrl("Practitioner")
                .setMethod(Bundle.HTTPVerb.POST);

        bundle.addEntry()
                .setFullUrl(geneticistTwo.getId())
                .setResource(geneticistTwo)
                .getRequest()
                .setUrl("Practitioner")
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

    public DiagnosticReport searchDiagnosticReportById(String projectDir, String diagnosticReportId) {
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
