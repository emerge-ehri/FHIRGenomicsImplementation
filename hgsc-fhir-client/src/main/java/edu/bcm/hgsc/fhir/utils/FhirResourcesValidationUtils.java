package edu.bcm.hgsc.fhir.utils;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import edu.bcm.hgsc.fhir.models.HgscReport;
import edu.bcm.hgsc.fhir.utils.validator.*;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class FhirResourcesValidationUtils {

    private static Logger logger = Logger.getLogger(FhirResourcesValidationUtils.class);

    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
    SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");

    public boolean validate(ArrayList<String> resultURLArr, HgscReport hgscReport, IGenericClient client) throws ParseException {

        if(!new PatientValidator().validatePatientById(resultURLArr.get(0).split("Patient/")[1].split("/_history")[0], hgscReport, client)) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new SpecimenValidator().validateSpecimenById(resultURLArr.get(1).split("Specimen/")[1].split("/_history")[0], hgscReport, client, sdf, sdf2)) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new ServiceRequestValidator().validateServiceRequestById(resultURLArr.get(2).split("ServiceRequest/")[1].split("/_history")[0], hgscReport, client)) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new ObsOverallValidator().validateObsOverallById(resultURLArr.get(6).split("Observation/")[1].split("/_history")[0], hgscReport, client)) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new DiagnosticReportValidator().validateDiagnosticReportById(resultURLArr.get(resultURLArr.size() - 1).split("DiagnosticReport/")[1].split("/_history")[0], resultURLArr, hgscReport, client, sdf, sdf2)) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        return true;
    }
}
