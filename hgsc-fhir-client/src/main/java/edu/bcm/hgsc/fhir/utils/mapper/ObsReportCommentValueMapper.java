package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class ObsReportCommentValueMapper {

    public Observation obsReportCommentValueMapping(HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport, SimpleDateFormat sdf) throws ParseException {

        Observation obsReportComment = new Observation();

        //Status
        if (mappingConfig.containsKey("HgscEmergeReport.reportStatus")) {
            obsReportComment.setStatus(Observation.ObservationStatus.fromCode(hgscEmergeReport.getReportStatus().toLowerCase()));
        }
        //Code
        obsReportComment.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("86467-8").setDisplay("Report comment")));
        //EffectiveDateTime
        if (mappingConfig.containsKey("HgscEmergeReport.sampleCollectedDate")) {
            obsReportComment.setEffective(new DateTimeType(sdf.parse(hgscEmergeReport.getSampleCollectedDate())));
        }
        //Issued
        if (mappingConfig.containsKey("HgscEmergeReport.reportDate")) {
            obsReportComment.setIssued(sdf.parse(hgscEmergeReport.getReportDate()));
        }
        //ValueString
        if (mappingConfig.containsKey("HgscEmergeReport.reportComment")) {
            obsReportComment.setValue(new StringType(hgscEmergeReport.getReportComment()));
        }

        return obsReportComment;
    }
}
