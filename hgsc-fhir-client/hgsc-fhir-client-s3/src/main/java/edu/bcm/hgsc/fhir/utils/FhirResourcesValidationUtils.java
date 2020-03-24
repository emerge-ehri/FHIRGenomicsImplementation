package edu.bcm.hgsc.fhir.utils;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import edu.bcm.hgsc.fhir.models.HgscReport;
import edu.bcm.hgsc.fhir.models.Variant;
import edu.bcm.hgsc.fhir.utils.validator.*;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class FhirResourcesValidationUtils {

    private static Logger logger = Logger.getLogger(FhirResourcesValidationUtils.class);

    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
    SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");

    public boolean validate(ArrayList<String> resultURLArr, HgscReport hgscReport, IGenericClient client, String orgName, HashMap<String, HashMap<String, String>> loincCodeMap) throws ParseException {

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

        if(!new ObsOverallValidator().validateObsOverallById(resultURLArr.get(3).split("Observation/")[1].split("/_history")[0], hgscReport, client)) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        int snpVinhDPNum = 0;
        if(hgscReport.getOverallInterpretation().toLowerCase().equals("positive")
                && hgscReport.getVariants() != null && hgscReport.getVariants().size() > 0) {
            snpVinhDPNum = hgscReport.getVariants().size() * 2;

            int startIndex = 1;
            for(Variant v : hgscReport.getVariants()) {
                if(!new DxSNPVariantsValidator().validateDxSNPVariantsById(resultURLArr.get(3 + startIndex).split("Observation/")[1].split("/_history")[0], hgscReport, client, v)) {
                    logger.error("Failed to validate FHIR resources.");
                    return false;
                }
                startIndex++;

                if(!new ObsInhDisPathsValidator().validateObsInhDisPathById(resultURLArr.get(3 + startIndex).split("Observation/")[1].split("/_history")[0], hgscReport, client, v)) {
                    logger.error("Failed to validate FHIR resources.");
                    return false;
                }
                startIndex++;
            }
        }

        if(!new DxPanelValidator().validateDxPanelById(resultURLArr.get(5 + snpVinhDPNum).split("Observation/")[1].split("/_history")[0], hgscReport, client)) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new PgxPanelValidator().validatePgxPanelById(resultURLArr.get(6 + snpVinhDPNum).split("Observation/")[1].split("/_history")[0], hgscReport, client)) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new PgxMedImplicationsValidator().validatePgxResult_1001_ById(resultURLArr.get(7 + snpVinhDPNum).split("Observation/")[1].split("/_history")[0], hgscReport, client, loincCodeMap.get("pgxDataPhenotypeCodeMap"))) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new PgxMedImplicationsValidator().validatePgxResult_2001_ById(resultURLArr.get(8 + snpVinhDPNum).split("Observation/")[1].split("/_history")[0], hgscReport, client, loincCodeMap.get("pgxDataPhenotypeCodeMap"))) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new PgxMedImplicationsValidator().validatePgxResult_3001_ById(resultURLArr.get(9 + snpVinhDPNum).split("Observation/")[1].split("/_history")[0], hgscReport, client, loincCodeMap.get("pgxDataPhenotypeCodeMap"))) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new PgxMedImplicationsValidator().validatePgxResult_4001_ById(resultURLArr.get(10 + snpVinhDPNum).split("Observation/")[1].split("/_history")[0], hgscReport, client, loincCodeMap.get("pgxDataPhenotypeCodeMap"))) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new PgxMedImplicationsValidator().validatePgxResult_5001_ById(resultURLArr.get(11 + snpVinhDPNum).split("Observation/")[1].split("/_history")[0], hgscReport, client, loincCodeMap.get("pgxDataPhenotypeCodeMap"))) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new PgxMedImplicationsValidator().validatePgxResult_6001_ById(resultURLArr.get(12 + snpVinhDPNum).split("Observation/")[1].split("/_history")[0], hgscReport, client, loincCodeMap.get("pgxDataPhenotypeCodeMap"))) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new PgxgenotypesValidator().validatePgxGeno_1001_ById(resultURLArr.get(13 + snpVinhDPNum).split("Observation/")[1].split("/_history")[0], hgscReport, client)) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new PgxgenotypesValidator().validatePgxGeno_2001_ById(resultURLArr.get(14 + snpVinhDPNum).split("Observation/")[1].split("/_history")[0], hgscReport, client)) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new PgxgenotypesValidator().validatePgxGeno_3001_ById(resultURLArr.get(15 + snpVinhDPNum).split("Observation/")[1].split("/_history")[0], hgscReport, client)) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new PgxgenotypesValidator().validatePgxGeno_4001_ById(resultURLArr.get(16 + snpVinhDPNum).split("Observation/")[1].split("/_history")[0], hgscReport, client)) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new PgxgenotypesValidator().validatePgxGeno_5001_ById(resultURLArr.get(17 + snpVinhDPNum).split("Observation/")[1].split("/_history")[0], hgscReport, client)) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new PgxgenotypesValidator().validatePgxGeno_6001_ById(resultURLArr.get(18 + snpVinhDPNum).split("Observation/")[1].split("/_history")[0], hgscReport, client)) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new PgxgenotypesValidator().validatePgxGeno_7001_ById(resultURLArr.get(19 + snpVinhDPNum).split("Observation/")[1].split("/_history")[0], hgscReport, client)) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        if(!new DiagnosticReportValidator().validateDiagnosticReportById(resultURLArr.get(resultURLArr.size() - 1).split("DiagnosticReport/")[1].split("/_history")[0], resultURLArr, hgscReport, client, sdf, sdf2, orgName)) {
            logger.error("Failed to validate FHIR resources.");
            return false;
        }

        return true;
    }
}
