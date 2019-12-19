package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class ObsReportCommentValueMapper {

    public Observation obsReportCommentValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Observation obsReportComment = new Observation();

        obsReportComment.setLanguage("en-US");

        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if (hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                obsReportComment.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Code
        obsReportComment.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("86467-8").setDisplay("Report comment")));
        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                obsReportComment.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }
        //ValueString
        if (mappingConfig.containsKey("HgscReport.obsReportComment")) {
            if(hgscReport.getComments() != null && !hgscReport.getComments().equals("")) {
                obsReportComment.setValue(new StringType(hgscReport.getComments()));
            }
        }

        return obsReportComment;
    }
}
