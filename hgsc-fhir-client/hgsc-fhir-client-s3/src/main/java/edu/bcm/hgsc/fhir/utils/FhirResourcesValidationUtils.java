package edu.bcm.hgsc.fhir.utils;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import edu.bcm.hgsc.fhir.models.HgscReport;
import edu.bcm.hgsc.fhir.models.Variant;
import edu.bcm.hgsc.fhir.utils.validator.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Locale;

public class FhirResourcesValidationUtils {

    private static Logger logger = LoggerFactory.getLogger(FhirResourcesValidationUtils.class);

    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
    SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");

    public boolean validateData(ArrayList<String> resultURLArr, HgscReport hgscReport, IGenericClient client, String orgName, HashMap<String, HashMap<String, String>> loincCodeMap) throws ParseException {

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

    public boolean validateBundleFhirResourceFormat(String serverURL, String bundle) {

        FileUtils fileUtils = new FileUtils();
        String hgscFhirServerUsername = fileUtils.loadPropertyValue("application.properties", "jpaserver.username");
        String hgscFhirServerPassword = fileUtils.loadPropertyValue("application.properties", "jpaserver.password");
        String authString = hgscFhirServerUsername + ":" + hgscFhirServerPassword;
        String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());

        Client restClient = Client.create();
        WebResource webResource = restClient.resource(serverURL + "/Bundle/$validate");
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic " + authStringEnc)
                .post(ClientResponse.class, bundle);

        if(response.getStatus() != 200 && response.getStatus() != 412){
            logger.error("Unexpected error response code for validateBundleFhirResourceFormat.");
            return false;
        }

        JSONObject jsonResp = null;
        try {
            jsonResp = (JSONObject) new JSONParser().parse(response.getEntity(String.class));
        } catch (org.json.simple.parser.ParseException e) {
            logger.error("Failed to Parse response from validateBundleFhirResourceFormat.", e);
            return false;
        }

        JSONArray issueArr = (JSONArray) jsonResp.get("issue");
        for (Object o : issueArr) {
            JSONObject jso = (JSONObject) o;
            String severity = jso.get("severity").toString();
            if(severity.equalsIgnoreCase("warning") || severity.equalsIgnoreCase("information")) {
                continue;
            }else{
                String diagnostics = jso.get("diagnostics").toString();
                if(!diagnostics.contains("hgvs")) {
                    logger.error("Failed to validate bundle resources with error:" + diagnostics);
                    return false;
                }else{
                    logger.info("Validation ERROR Expected for bundle resources with error:" + diagnostics);
                }
            }
        }

        return true;
    }
}
