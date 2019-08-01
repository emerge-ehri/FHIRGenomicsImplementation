package com.hgsc.fhir.services;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import com.hgsc.fhir.models.HgscEmergeReport;
import com.hgsc.fhir.utils.FhirResourcesMappingUtils;
import com.hgsc.fhir.utils.FileUtils;
import com.hgsc.fhir.utils.JsonMappingUtil;
import org.apache.log4j.Logger;
import org.hl7.fhir.r4.model.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileUploadServiceImpl {

   private static Logger logger = Logger.getLogger(FileUploadServiceImpl.class);

   static FhirContext ctx = FhirContext.forR4();
   static IGenericClient client = ctx.newRestfulGenericClient("http://localhost:8080/hapi-fhir-jpaserver/fhir/");
   FileUtils fileUtils = new FileUtils();

   public void createFhirResources(File file) {

      JsonMappingUtil util = new JsonMappingUtil();
      HgscEmergeReport report = util.readFromEmergeReportJsonFile(file);

      Map<String, Object> fhirResources = this.createIndividualFhirResources(report);
      this.createBundle(fhirResources);
//      this.searchDiagnosticReportById("61");
   }

   public Map<String, Object> createIndividualFhirResources(HgscEmergeReport hgscEmergeReport) {
      HashMap<String, String> mappingConfig = fileUtils.readMapperConfig(FileUtils.PROJECT_DIRECTORY + "/src/main/resources/mapping.conf");
      Map<String, Object> newResources = new FhirResourcesMappingUtils().mapping(mappingConfig, hgscEmergeReport);
      return newResources;

//      for (String resourceName : newResources.keySet()) {
//         MethodOutcome outcome = client.create().resource((IBaseResource)newResources.get(resourceName)).execute();
//         IIdType id = outcome.getId();
//         System.out.println("created resource, got ID: " + id);
//      }
   }

   public void createBundle(Map<String, Object> fhirResources) {

      Patient patient = (Patient)fhirResources.get("Patient");
      // Give the patient a temporary UUID so that other resources in the transaction can refer to it
      patient.setId(IdDt.newRandomUuid());

      Specimen specimen = (Specimen)fhirResources.get("Specimen");
      specimen.setId(IdDt.newRandomUuid());

      DiagnosticReport diagnosticReport = (DiagnosticReport)fhirResources.get("DiagnosticReport");
      // The diagnosticReport refers to the patient/specimen using the ID, which is already set to a temporary UUID
      diagnosticReport.addSpecimen(new Reference(specimen.getId()));
      diagnosticReport.setSubject(new Reference(patient.getId()));

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
         .setResource(diagnosticReport)
         .getRequest()
         .setUrl("DiagnosticReport")
         .setMethod(Bundle.HTTPVerb.POST);

      Bundle resp = client.transaction().withBundle(bundle).execute();
      logger.info("Create Bundle:" + resp);
      System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(resp));
   }

   public DiagnosticReport searchDiagnosticReportById(String diagnosticReportId) {
      DiagnosticReport diagnosticReport = client.read().resource(DiagnosticReport.class).withId(diagnosticReportId).execute();

      String string = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(diagnosticReport);
      System.out.println(string);

      byte[] byteArray = diagnosticReport.getPresentedFormFirstRep().getData();
      File outputFile = new File(FileUtils.PROJECT_DIRECTORY + "outputFile.pdf");

      // save byte[] into the output file
      try (FileOutputStream fos = new FileOutputStream(outputFile)) {
         fos.write(byteArray);
      } catch (IOException e) {
         e.printStackTrace();
      }

      return diagnosticReport;
   }
}
