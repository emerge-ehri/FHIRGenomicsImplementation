package edu.bcm.hgsc.fhir.utils.validator;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import edu.bcm.hgsc.fhir.models.HgscReport;
import org.apache.log4j.Logger;
import org.hl7.fhir.r4.model.Patient;

public class PatientValidator {

    private static Logger logger = Logger.getLogger(PatientValidator.class);

    public boolean validatePatientById(String resourceId, HgscReport hgscReport, IGenericClient client) {

        Patient patient = client.read().resource(Patient.class).withId(resourceId).execute();

        boolean validateIdentifierResult = true;
        if(hgscReport.getPatientID() != null && !hgscReport.getPatientID().equals("")) {
            validateIdentifierResult = patient.getIdentifierFirstRep().getValue().equals(hgscReport.getPatientID());
        }

        boolean validateLastNameResult = true;
        if(hgscReport.getPatientLastName() != null && !hgscReport.getPatientLastName().equals("")) {
            validateLastNameResult = patient.getNameFirstRep().getFamily().equals(hgscReport.getPatientLastName());
        }

        boolean validateFirstNameResult = true;
        if(hgscReport.getPatientFirstName() != null && !hgscReport.getPatientFirstName().equals("")) {
            validateFirstNameResult = patient.getNameFirstRep().getGivenAsSingleString().contains(hgscReport.getPatientFirstName());
        }

        String sex = patient.getExtensionByUrl("http://hl7.org/fhir/us/core/StructureDefinition/us-core-birthsex").getValue().toString();
        boolean validateSexResult = true;
        if(sex != null && !sex.equals("")) {
            validateSexResult = sex.equals(hgscReport.getSex().substring(0, 1)) || sex.equals(hgscReport.getSex());
        }

        if(!validateIdentifierResult || !validateLastNameResult || !validateFirstNameResult || !validateSexResult) {
            logger.error("Failed to validate FHIR Patient resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }
}
