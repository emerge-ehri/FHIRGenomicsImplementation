package edu.bcm.hgsc.fhir.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.core.MediaType;
import java.nio.charset.StandardCharsets;

public class JHUPostUtil {

    private static Logger logger = Logger.getLogger(JHUPostUtil.class);

    FileUtils fileUtils = new FileUtils();

    public String postForJHUToken() {

        String tenantId = fileUtils.loadPropertyValue("application.properties", "jhu.tenantId");
        String clientId = fileUtils.loadPropertyValue("application.properties", "jhu.clientId");
        String clientSecret = fileUtils.loadPropertyValue("application.properties", "jhu.clientSecret");
        String resource = fileUtils.loadPropertyValue("application.properties", "jhu.resource");

        String url = "https://login.microsoftonline.com/" + tenantId + "/oauth2/token";
        Form form = new Form();
        form.add("grant_type", "client_credentials");
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("resource", resource);

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
            return "";
        }
        String jhuToken = jsonResp.get("access_token").toString();
        logger.info("Get JHU token Done..");

        return jhuToken;
    }

    public boolean postForJHU(String bundle, String jhuToken) {

        FileUtils fileUtils = new FileUtils();

        String url = fileUtils.loadPropertyValue("application.properties", "jhu.url");

        Client restClient = Client.create();
        WebResource webResource = restClient.resource(url);

        ClientResponse response = webResource.header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + jhuToken)
                .post(ClientResponse.class, bundle);

        if(response.getStatus() != 200){
            logger.error("Failed to Post Fhir resources to the JHU server.");
            return false;
        }

        byte[] responseByteArr = fileUtils.generateByteArray(response.getEntityInputStream());

        JSONObject jsonResp = null;
        try {
            jsonResp = (JSONObject) new JSONParser().parse(new String(responseByteArr, StandardCharsets.UTF_8));
        } catch (ParseException e) {
            logger.error("Failed to Parse JHU token.", e);
        }

        JSONArray entryArr = (JSONArray) jsonResp.get("entry");
        for (Object o : entryArr) {
            JSONObject resEntry = (JSONObject) o;
            JSONObject resp = (JSONObject) resEntry.get("response");
            String status = resp.get("status").toString();
            if(!status.equals("201")) {
                logger.error("Failed to Post the Fhir resources to the JHU server.");
                return false;
            }
        }

        logger.info("Post Fhir resources to JHU server Done..");

        return true;
    }
}
