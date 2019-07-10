package demo1;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import com.google.gson.Gson;

import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.instance.model.api.IBaseOperationOutcome;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IIdType;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class TestApplication {

   // Create a client
   static FhirContext ctx = FhirContext.forR4();
   //static IGenericClient client = ctx.newRestfulGenericClient("http://fhirtest.uhn.ca/baseDstu3");
   static IGenericClient client = ctx.newRestfulGenericClient("http://localhost:8080/hapi-fhir-jpaserver/fhir/");
   //static IGenericClient client = ctx.newRestfulGenericClient("http://10.51.152.68:8080/hapi-fhir-jpaserver/fhir/");

   public static void main(String[] args) {

      TestApplication t = new TestApplication();

      List<EmergeReport> list = t.readJSONDataFile("eMergeReport.json");

      //t.createPatient(list);

      //t.createResources(list);

      //t.createResourcesWithDozerMapping(list);

      //t.searchPatientsById("1968992");

      //t.searchPatientsByName("Yan", "Fei", "1111");

      //t.updatePatient("1968992", "1111");

      //t.deletePatientById("1974161");

      //t.deletePatient("Aaa", "Bbb", "1234");

      //t.createBundle(list);

      //t.searchBundle();
   }

   public List<EmergeReport> readJSONDataFile(String fileName) {
      String data = readJsonData(fileName);
      Gson gson = new Gson();

      List<EmergeReport> emergeReportList = gson.fromJson(data, MainType.class).getEmergeReports();

      for(EmergeReport emergeReport : emergeReportList){
         System.out.println("Patient Name:" + emergeReport.getFamilyName()
            + " " + emergeReport.getGivenName()
            + ", Identifier:"+emergeReport.getPatientID()
            + ", Gender:"+emergeReport.getSex()
            + ", BirthDate:"+emergeReport.getDateOfBirth()
            + ", Observation Status:"+emergeReport.getStatus()
            + ", Observation Code System:"+emergeReport.getCode().getSystem()
            + ", Observation Code Code:"+emergeReport.getCode().getCode()
            + ", Observation Code Display:"+emergeReport.getCode().getDisplay());
      }

      return emergeReportList;
   }

   public String readJsonData(String fileName) {
      StringBuffer strbuffer = new StringBuffer();
      File myFile = new File(fileName);
      if (!myFile.exists()) {
         System.err.println("File Not Found:" + fileName);
      }
      try (FileInputStream fis = new FileInputStream(fileName);
           InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8");
           BufferedReader in  = new BufferedReader(inputStreamReader)) {

         String str;
         while ((str = in.readLine()) != null) {
            strbuffer.append(str);
         }
      } catch (IOException e) {
         e.getStackTrace();
      }
      return strbuffer.toString();
   }

   public HashMap<String, String> readMapperConfig(String fileName) {
      HashMap<String, String> mappingConfig = new HashMap<>();
      File myFile = new File(fileName);
      if (!myFile.exists()) {
         System.err.println("File Not Found:" + fileName);
      }
      try (FileInputStream fis = new FileInputStream(fileName);
           InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8");
           BufferedReader in  = new BufferedReader(inputStreamReader)) {

         String str;
         while ((str = in.readLine()) != null) {
            String[] arr = str.split(":");
            mappingConfig.put(arr[0], arr[1]);
         }
      } catch (IOException e) {
         e.getStackTrace();
      }
      return mappingConfig;
   }

   public void createPatient(List<HGSCPatient> list) {
      for(HGSCPatient hgscPatient : list) {
         // Create a patient
         Patient newPatient = new Patient();

         newPatient.addName().setFamily(hgscPatient.getFamilyName()).addGiven(hgscPatient.getGivenName());
         newPatient.addIdentifier().setSystem("http://acme.org/mrn").setValue(hgscPatient.getIdentifier());
         newPatient.setGender("MALE".equals(hgscPatient.getGender().toUpperCase()) ?
            Enumerations.AdministrativeGender.MALE : Enumerations.AdministrativeGender.FEMALE);
         newPatient.setBirthDateElement(new DateType(hgscPatient.getBirthDate()));

         // Create the resource on the server
         MethodOutcome outcome = client.create().resource(newPatient).execute();

         // Log the ID that the server assigned
         IIdType id = outcome.getId();
         System.out.println("Created patient, got ID: " + id);
      }
   }

   public void createResources(List<EmergeReport> emergeReportList) {
      HashMap<String, String> mappingConfig = readMapperConfig("mapping.conf");
      List<Object> newResources = new MappingUtils().setMappingValues(mappingConfig, emergeReportList.get(0));

      for (Object o : newResources) {
         MethodOutcome outcome = client.create().resource((IBaseResource) o).execute();
         IIdType id = outcome.getId();
         System.out.println("Created resource, got ID: " + id);
      }
   }

   public void createResourcesWithDozerMapping(List<EmergeReport> emergeReportList) {
      List<Object> newResources = new MappingUtils().dozerMapping("emergemapper.xml", emergeReportList.get(0));

      for (Object o : newResources) {
         MethodOutcome outcome = client.create().resource((IBaseResource) o).execute();
         IIdType id = outcome.getId();
         System.out.println("Created resource, got ID: " + id);
      }
   }

   public Patient searchPatientsById(String patientId) {
      // Read a patient with the given ID
      Patient patient = client.read().resource(Patient.class).withId(patientId).execute();

      String string = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(patient);
      System.out.println(string);

      return patient;
   }

   public void searchPatientsByName(String familyName, String givenName, String identifier) {
      org.hl7.fhir.r4.model.Bundle results = client.search().forResource(Patient.class)
         .where(Patient.FAMILY.matchesExactly().value(familyName))
         .where(Patient.GIVEN.matchesExactly().value(givenName))
         .where(Patient.IDENTIFIER.exactly().identifier(identifier))
         .returnBundle(org.hl7.fhir.r4.model.Bundle.class)
         .execute();

      System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(results));
   }

   public void updatePatient(String patientId, String newIdentifier) {
      Patient patient = searchPatientsById(patientId);

      patient.setIdentifier(null);
      patient.addIdentifier().setValue(newIdentifier);

      MethodOutcome outcome = client.update().resource(patient).execute();

      IIdType id = outcome.getId();
      System.out.println("Got ID: " + id.getValue());
   }

   public void deletePatientById(String patientId) {
      IBaseOperationOutcome response = client.delete().resourceById(new IdDt("Patient", patientId)).execute();

      if (response != null) {
         OperationOutcome outcome = (OperationOutcome) response;
         System.out.println(outcome.getIssueFirstRep().getDiagnosticsElement().getValue());
      }
   }

   public void deletePatient(String familyName, String givenName, String identifier) {
      //IBaseOperationOutcome response = client.delete()
         //.resourceConditionalByUrl("Patient?name="+patientName+"&identifier="+identifierValue).execute();

      IBaseOperationOutcome response = client.delete().resourceConditionalByType("Patient")
         .where(Patient.FAMILY.matchesExactly().value(familyName))
         .where(Patient.GIVEN.matchesExactly().value(givenName))
         .where(Patient.IDENTIFIER.exactly().identifier(identifier))
         .execute();

      if (response != null) {
         OperationOutcome outcome = (OperationOutcome) response;
         System.out.println(outcome.getIssueFirstRep().getDiagnosticsElement().getValue());
      }
   }

   public void createBundle(List<EmergeReport> emergeReportList) {

      EmergeReport emergeReport = emergeReportList.get(0);

      Patient patient = new Patient();
      patient.addIdentifier().setSystem("http://acme.org/mrns").setValue(emergeReport.getPatientID());
      patient.addName().setFamily(emergeReport.getFamilyName()).addGiven(emergeReport.getGivenName());
      patient.setGender("MALE".equals(emergeReport.getSex().toUpperCase()) ?
         Enumerations.AdministrativeGender.MALE : Enumerations.AdministrativeGender.FEMALE);

      // Give the patient a temporary UUID so that other resources in the transaction can refer to it
      patient.setId(IdDt.newRandomUuid());

      Observation observation = new Observation();
      observation.setStatus(Observation.ObservationStatus.FINAL);
      observation.getCode().addCoding().setSystem(emergeReport.getCode().getSystem()).setCode(emergeReport.getCode().getCode())
         .setDisplay(emergeReport.getCode().getDisplay());

      // The observation refers to the patient using the ID, which is already set to a temporary UUID
      observation.setSubject(new Reference(patient.getId()));

      // Create a bundle that will be used as a transaction
      Bundle bundle = new Bundle();
      bundle.setType(Bundle.BundleType.TRANSACTION);

      bundle.addEntry()
         .setFullUrl(patient.getId())
         .setResource(patient)
         .getRequest()
         .setUrl("Patient")
         .setIfNoneExist("name=Yan&identifier=1234567")
         .setMethod(Bundle.HTTPVerb.POST);

      bundle.addEntry()
         .setResource(observation)
         .getRequest()
         .setUrl("Observation")
         .setMethod(Bundle.HTTPVerb.POST);

      System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle));
      Bundle resp = client.transaction().withBundle(bundle).execute();
      System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(resp));
   }

   public void searchBundle() {
      Bundle results = client.search()
         .forResource(Patient.class)
         .where(Patient.FAMILY.matches().value("Yan"))
         .where(Patient.GIVEN.matches().value("Fei"))
         .returnBundle(org.hl7.fhir.r4.model.Bundle.class)
         .execute();

      Patient p = (Patient)results.getEntryFirstRep().getResource();
      System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(p));
   }
}
