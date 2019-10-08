package edu.bcm.hgsc.fhir.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bcm.hgsc.fhir.models.HgscEmergeReport;
import org.apache.log4j.Logger;

import java.io.File;

public class JsonMappingUtil {

    private static Logger logger = Logger.getLogger(JsonMappingUtil.class);

    public HgscEmergeReport readFromEmergeReportJsonFile(File jsonFile) {
        ObjectMapper mapper = new ObjectMapper();
        HgscEmergeReport hgscEmergeReport = null;

        try {
            hgscEmergeReport = mapper.readValue(jsonFile, HgscEmergeReport.class);
            logger.info("JsonMappingUtil: File parsing is completed.");
        } catch (Exception e) {
            logger.error("JsonMappingUtil: File parsing is completed.", e);
        }
        return hgscEmergeReport;
    }
}
