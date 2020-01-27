package edu.bcm.hgsc.fhir.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Base64;
import java.util.HashMap;

public class LoincCodeUtil {

    private static Logger logger = Logger.getLogger(LoincCodeUtil.class);

    public HashMap<String, HashMap<String, String>> loadLoincCodeToMap() {
        HashMap<String, HashMap<String, String>> loincCodeMap = new HashMap<String, HashMap<String, String>>();

        HashMap<String, String> humanReferenceSequenceAssemblyVersionCodeMap = loadSingleLoincCodeToMap("https://fhir.loinc.org/ValueSet/?url=http://loinc.org/vs/LL1040-6");
        loincCodeMap.put("humanReferenceSequenceAssemblyVersionCodeMap", humanReferenceSequenceAssemblyVersionCodeMap);

        HashMap<String, String> variantsCodeMap = loadSingleLoincCodeToMap("https://fhir.loinc.org/ValueSet/?url=http://loinc.org/vs/LL1971-2");
        loincCodeMap.put("variantsCodeMap", variantsCodeMap);

        HashMap<String, String> genomicCoordinateSystemCodeMap = loadSingleLoincCodeToMap("https://fhir.loinc.org/ValueSet/?url=http://loinc.org/vs/LL5323-2");
        loincCodeMap.put("genomicCoordinateSystemCodeMap", genomicCoordinateSystemCodeMap);

        HashMap<String, String> genomicSourceCodeMap = loadSingleLoincCodeToMap("https://fhir.loinc.org/ValueSet/?url=http://loinc.org/vs/LL378-1");
        loincCodeMap.put("genomicSourceCodeMap", genomicSourceCodeMap);

        HashMap<String, String> variantInterpretationCodeMap = loadSingleLoincCodeToMap("https://fhir.loinc.org/ValueSet/?url=http://loinc.org/vs/LL4034-6");
        loincCodeMap.put("variantInterpretationCodeMap", variantInterpretationCodeMap);

        HashMap<String, String> variantInheritanceCodeMap = loadSingleLoincCodeToMap("https://fhir.loinc.org/ValueSet/?url=http://loinc.org/vs/LL3731-8");
        loincCodeMap.put("variantInheritanceCodeMap", variantInheritanceCodeMap);

        HashMap<String, String> variantZygosityCodeMap = loadSingleLoincCodeToMap("https://fhir.loinc.org/ValueSet/?url=http://loinc.org/vs/LL381-5");
        loincCodeMap.put("variantZygosityCodeMap", variantZygosityCodeMap);

        HashMap<String, String> overallInterpretationCodeMap = loadSingleLoincCodeToMap("https://fhir.loinc.org/ValueSet/?url=http://loinc.org/vs/LL541-4");
        loincCodeMap.put("overallInterpretationCodeMap", overallInterpretationCodeMap);

        HashMap<String, String> variantChromosomeCodeMap = loadSingleLoincCodeToMap("https://fhir.loinc.org/ValueSet/?url=http://loinc.org/vs/LL2938-0");
        loincCodeMap.put("variantChromosomeCodeMap", variantChromosomeCodeMap);

        HashMap<String, String> pgxDataPhenotypeCodeMap = loadSingleLoincCodeToMap("https://fhir.loinc.org/ValueSet/?url=http://loinc.org/vs/LL3856-3");
        loincCodeMap.put("pgxDataPhenotypeCodeMap", pgxDataPhenotypeCodeMap);

        return loincCodeMap;
    }

    private HashMap<String, String> loadSingleLoincCodeToMap(String url) {

        String username = "hgsc";
        String password = "HGSCCLaa1";
        String authString = username + ":" + password;
        String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
        Client restClient = Client.create();
        WebResource webResource = restClient.resource(url);
        ClientResponse resp = webResource.accept("application/json")
                .header("Authorization", "Basic " + authStringEnc)
                .get(ClientResponse.class);
        if(resp.getStatus() != 200){
            logger.error("Unable to connect to the LOINC server.");
        }
        String output = resp.getEntity(String.class);

        JSONObject response = null;
        try {
            response = (JSONObject) new JSONParser().parse(output);
            logger.info("Get LOINC Bundle:" + response);
        } catch (ParseException e) {
            logger.error("Failed to convert LOINC bundle to JSON object.", e);
        }

        JSONArray entryArr = (JSONArray) response.get("entry");
        JSONObject resEntry = (JSONObject) entryArr.get(0);
        JSONObject resource = (JSONObject) resEntry.get("resource");
        JSONObject compose = (JSONObject) resource.get("compose");
        JSONArray includeArr = (JSONArray) compose.get("include");
        JSONObject includeEntry = (JSONObject) includeArr.get(0);
        JSONArray conceptArr = (JSONArray) includeEntry.get("concept");

        HashMap<String, String> singleLoincCodeMap = new HashMap<String, String>();
        for (Object o : conceptArr) {
            JSONObject jso = (JSONObject) o;
            String code = jso.get("code").toString();
            String display = jso.get("display").toString();
            singleLoincCodeMap.put(display, code);
        }

        return singleLoincCodeMap;
    }
}
