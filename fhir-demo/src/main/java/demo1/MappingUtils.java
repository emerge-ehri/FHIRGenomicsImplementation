package demo1;

import java.util.*;

import org.dozer.DozerBeanMapper;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;

public class MappingUtils {

    public List<Object> dozerMapping(String mappingConfigFileName, EmergeReport emergeReport) {
        List<Object> results = new ArrayList<Object>();

        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.setMappingFiles(Arrays.asList(mappingConfigFileName));

        Patient patient = dozerBeanMapper.map(emergeReport, Patient.class);
        Observation observation = dozerBeanMapper.map(emergeReport, Observation.class);

        if (patient != null) {
            results.add(patient);
        }
        if (observation != null) {
            results.add(observation);
        }

        return results;
    }

    public List<Object> setMappingValues(HashMap<String, String> mappingConfig, EmergeReport emergeReport) {

        List<Object> results = new ArrayList<Object>();

        // Get resource types from the mapper config file
        HashSet<String> resources = new HashSet<String>();
        for (String value : mappingConfig.values()) {
            String[] strArr = value.split("\\.");
            resources.add(strArr[0]);
        }

        // init all possible resource types
        Patient patient = null;
        Observation observation = null;

        // create all resources
        for (String resource : resources) {
            try {
                switch (resource) {
                    case "Patient":
                        patient = (Patient) Class.forName("org.hl7.fhir.r4.model.Patient").newInstance();
                        break;
                    case "Observation":
                        observation = (Observation) Class.forName("org.hl7.fhir.r4.model.Observation").newInstance();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        patient.addIdentifier().setSystem("http://acme.org/mrns").setValue(emergeReport.getPatientID());
        if (mappingConfig.containsKey("EmergeReport.patientName")) {
            patient.addName().setFamily(emergeReport.getFamilyName())
                    .addGiven(emergeReport.getGivenName());
        }
        if (mappingConfig.containsKey("EmergeReport.sex")) {
            patient.setGender("MALE".equals(emergeReport.getSex().toUpperCase())
                    ? Enumerations.AdministrativeGender.MALE : Enumerations.AdministrativeGender.FEMALE);
        }

        if (mappingConfig.containsKey("EmergeReport.status")) {
            observation.setStatus(Observation.ObservationStatus.FINAL);
        }

        if (patient != null) {
            results.add(patient);
        }
        if (observation != null) {
            results.add(observation);
        }

        return results;
    }
}
