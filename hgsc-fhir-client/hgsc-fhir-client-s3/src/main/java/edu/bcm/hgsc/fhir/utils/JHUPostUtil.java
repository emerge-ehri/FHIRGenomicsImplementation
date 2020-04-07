package edu.bcm.hgsc.fhir.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.core.MediaType;

public class JHUPostUtil {

    private static Logger logger = Logger.getLogger(JHUPostUtil.class);

    public String postForJHUToken() {

        FileUtils fileUtils = new FileUtils();

        String tenantId = fileUtils.loadPropertyValue("application.properties", "jhu.tenantId");
        String clientId = fileUtils.loadPropertyValue("application.properties", "jhu.clientId");
        String clientSecret = fileUtils.loadPropertyValue("application.properties", "jhu.clientSecret");

        String url = "https://login.microsoftonline.com/" + tenantId + "/oauth2/token";
        Form form = new Form();
        form.add("grant_type", "client_credentials");
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);

        Client restClient = Client.create();
        WebResource webResource = restClient.resource(url);
        ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED)
                .post(ClientResponse.class, form);

        if(response.getStatus() != 200){
            logger.error("Unable to get token from the JHU server.");
            return "";
        }

        JSONObject jsonResp = null;
        try {
            jsonResp = (JSONObject) new JSONParser().parse(response.getEntity(String.class));
        } catch (ParseException e) {
            logger.error("Failed to Parse JHU token.", e);
        }
        String jhuToken = jsonResp.get("access_token").toString();
        logger.info("Get JHU token Done..");

        return jhuToken;
    }

    public boolean postForJHU(String bundle, String jhuToken) {

        FileUtils fileUtils = new FileUtils();

        String url = fileUtils.loadPropertyValue("application.properties", "jhu.url");

        String requestEntity = "{\n" +
                "  \"resourceType\": \"Bundle\",\n" +
                "  \"type\": \"batch\",\n" +
                "  \"entry\": [\n" +
                "    {\n" +
                "      \"fullUrl\": \"urn:uuid:d1e6e85c-5615-4987-87df-2f5e78aa44e2\",\n" +
                "      \"resource\": {\n" +
                "        \"resourceType\": \"Patient\",\n" +
                "        \"language\": \"en-US\",\n" +
                "        \"identifier\": [\n" +
                "          {\n" +
                "            \"type\": {\n" +
                "              \"coding\": [\n" +
                "                {\n" +
                "                  \"system\": \"http://terminology.hl7.org/CodeSystem/v2-0203\",\n" +
                "                  \"code\": \"PI\",\n" +
                "                  \"display\": \"Patient internal identifier\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"request\": {\n" +
                "        \"method\": \"POST\",\n" +
                "        \"url\": \"Patient\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";
        //String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
        Client restClient = Client.create();
        WebResource webResource = restClient.resource(url);

        ClientResponse response = webResource.header("Accept", MediaType.APPLICATION_JSON)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + jhuToken)
                .post(ClientResponse.class, requestEntity);

        if(response.getStatus() != 200){
            logger.error("Failed to Post Fhir resources to the JHU server.");
            return false;
        }

        JSONObject jsonResp = null;
        try {
            jsonResp = (JSONObject) new JSONParser().parse(response.getEntity(String.class));
        } catch (ParseException e) {
            logger.error("Failed to Parse JHU token.", e);
        }

        logger.info("Post Fhir resources to JHU server Done..");
//
//        JSONArray entryArr = (JSONArray) response.get("entry");
//        JSONObject resEntry = (JSONObject) entryArr.get(0);
//        JSONObject resource = (JSONObject) resEntry.get("resource");
//        JSONObject compose = (JSONObject) resource.get("compose");
//
//        HashMap<String, String> singleLoincCodeMap = new HashMap<String, String>();
//        for (Object o : conceptArr) {
//            JSONObject jso = (JSONObject) o;
//            String code = jso.get("code").toString();
//            String display = jso.get("display").toString();
//            singleLoincCodeMap.put(display, code);
//        }
//
        return true;
    }
}
