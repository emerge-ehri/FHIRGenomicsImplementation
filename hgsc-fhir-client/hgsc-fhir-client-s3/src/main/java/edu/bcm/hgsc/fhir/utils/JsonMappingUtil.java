package edu.bcm.hgsc.fhir.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bcm.hgsc.fhir.models.HgscReport;
import org.apache.log4j.Logger;

public class JsonMappingUtil {

    private static Logger logger = Logger.getLogger(JsonMappingUtil.class);

    public HgscReport readHgscReportJson(byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        HgscReport hgscReport = null;

        try {
            hgscReport = mapper.readValue(bytes, HgscReport.class);
            logger.info("JsonMappingUtil: File parsing is completed.");
        } catch (Exception e) {
            logger.error("JsonMappingUtil: File parsing is completed.", e);
        }
        return hgscReport;
    }
}
