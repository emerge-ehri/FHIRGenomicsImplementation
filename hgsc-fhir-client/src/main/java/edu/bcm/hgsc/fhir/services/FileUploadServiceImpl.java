package edu.bcm.hgsc.fhir.services;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.narrative.DefaultThymeleafNarrativeGenerator;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import edu.bcm.hgsc.fhir.models.HgscEmergeReport;
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
   IGenericClient client = ctx.newRestfulGenericClient(fileUtils.loadPropertyValue("config.properties", "jpaserver.url"));

   public ArrayList<String> createFhirResources(File file) {

      JsonMappingUtil util = new JsonMappingUtil();
      HgscEmergeReport report = util.readFromEmergeReportJsonFile(file);

      Map<String, Object> fhirResources = this.createIndividualFhirResources(report);
      return this.createBundle(fhirResources, report);
   }

   public Map<String, Object> createIndividualFhirResources(HgscEmergeReport hgscEmergeReport) {

      HashMap<String, String> mappingConfig = fileUtils.readMapperConfig(getClass().getClassLoader().getResource("mapping.conf").getPath());
      Map<String, Object> newResources = new FhirResourcesMappingUtils().mapping(mappingConfig, hgscEmergeReport);
      return newResources;
   }

   public ArrayList<String> createBundle(Map<String, Object> fhirResources, HgscEmergeReport hgscEmergeReport) {

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

      Observation dxCNVVariants = (Observation) fhirResources.get("DxCNVVariants");
      dxCNVVariants.setId(IdDt.newRandomUuid());

      Observation dxSNPINDELVariants = (Observation) fhirResources.get("DxSNPINDELVariants");
      dxSNPINDELVariants.setId(IdDt.newRandomUuid());

      Practitioner geneticistOne = (Practitioner) fhirResources.get("GeneticistOne");
      geneticistOne.setId(IdDt.newRandomUuid());
      Practitioner geneticistTwo = (Practitioner) fhirResources.get("GeneticistTwo");
      geneticistTwo.setId(IdDt.newRandomUuid());

      PlanDefinition planDefinition = (PlanDefinition) fhirResources.get("PlanDefinition");
      planDefinition.setId(IdDt.newRandomUuid());

      organization.setPartOf(new Reference(organizationBCM.getId()));
      specimen.addRequest(new Reference(serviceRequest.getId()));
      serviceRequest.addSpecimen(new Reference(specimen.getId()));
      obsOverall.addBasedOn(new Reference(serviceRequest.getId()));
      obsOverall.setSubject(new Reference(patient.getId()));
      obsOverall.setSpecimen(new Reference(specimen.getId()));
      obsOverall.addPerformer(new Reference(organization.getId()));
      dxCNVVariants.addBasedOn(new Reference(serviceRequest.getId()));
      dxCNVVariants.setSubject(new Reference(patient.getId()));
      dxCNVVariants.setSpecimen(new Reference(specimen.getId()));
      dxCNVVariants.addPerformer(new Reference(organization.getId()));
      dxCNVVariants.addNote(new Annotation().setAuthor(new Reference(organization.getId())).setText("Comments"));
      dxSNPINDELVariants.addBasedOn(new Reference(serviceRequest.getId()));
      dxSNPINDELVariants.setSubject(new Reference(patient.getId()));
      dxSNPINDELVariants.setSpecimen(new Reference(specimen.getId()));
      dxSNPINDELVariants.addPerformer(new Reference(organization.getId()));
      dxSNPINDELVariants.addNote(new Annotation().setAuthor(new Reference(organization.getId())).setText(hgscEmergeReport.getVariants().get(0).getNotes()));

      DiagnosticReport diagnosticReport = (DiagnosticReport)fhirResources.get("DiagnosticReport");
      // The diagnosticReport refers to the patient/specimen using the ID, which is already set to a temporary UUID
      diagnosticReport.addBasedOn(new Reference(serviceRequest.getId()));
      diagnosticReport.addPerformer(new Reference(organization.getId()));
      diagnosticReport.addResultsInterpreter(new Reference(geneticistOne.getId()))
              .addResultsInterpreter(new Reference(geneticistTwo.getId()));
      diagnosticReport.addSpecimen(new Reference(specimen.getId()));
      diagnosticReport.setSubject(new Reference(patient.getId()));
      diagnosticReport.addResult(new Reference(obsOverall.getId()));

      //Narrative
      ctx.setNarrativeGenerator(new DefaultThymeleafNarrativeGenerator());
      //patient.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
              //.setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(patient))));
      specimen.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
              .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(specimen))));
      serviceRequest.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
              .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(serviceRequest))));
      //organization.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
              //.setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(organization))));
      //hard code organization narrative
      organization.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
              .setDiv(new XhtmlNode().setValue("<div><p><b>Generated Narrative with Details</b></p><p><b>id</b>: Human Genome Sequencing Center Clinical Laboratory</p><p>One Baylor Plaza • Houston • TX 77030</p><p>Phone: 713.798.6539 • Fax: 713.798.5741 • www.hgsc.bcm.edu • email: questions@hgsc.bcm.edu</p><p>CAP# 8004250 / CLIA# 45D2027450</p></div>")));
      organizationBCM.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
              .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(organizationBCM))));
      obsOverall.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
              .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(obsOverall))));
      dxCNVVariants.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
              .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(dxCNVVariants))));
      dxSNPINDELVariants.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
              .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(dxSNPINDELVariants))));
      //geneticistOne.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
              //.setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(geneticistOne))));
      geneticistTwo.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
              .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(geneticistTwo))));
      //hard code organization narrative
      geneticistOne.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
              .setDiv(new XhtmlNode().setValue("<div><p><b>Generated Narrative with Details</b></p><p><b>Practitioner Name</b>: David Murdock, M.D., F.A.C.M.G.</p><p><b>Title</b>: ABMGG Certified Molecular Geneticist, Assistant Laboratory Director</p>")));
      //diagnosticReport.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
              //.setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(diagnosticReport))));
      planDefinition.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
              .setDiv(new XhtmlNode().setValue(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(planDefinition))));

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

      bundle.addEntry()
              .setFullUrl(dxCNVVariants.getId())
              .setResource(dxCNVVariants)
              .getRequest()
              .setUrl("Observation")
              .setMethod(Bundle.HTTPVerb.POST);

      bundle.addEntry()
              .setFullUrl(dxSNPINDELVariants.getId())
              .setResource(dxSNPINDELVariants)
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
