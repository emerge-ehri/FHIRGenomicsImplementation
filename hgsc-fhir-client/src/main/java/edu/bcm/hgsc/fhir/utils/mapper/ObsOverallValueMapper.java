package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class ObsOverallValueMapper {

    public Observation obsOverallValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Observation obsOverall = new Observation();

        //Profile
        obsOverall.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/overall-interpretation");
        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if (hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                obsOverall.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Category
        obsOverall.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        obsOverall.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("51968-6").setDisplay("Genetic disease analysis overall interpretation")));
        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                obsOverall.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }
        //ValueCodeableConcept
        if (mappingConfig.containsKey("HgscReport.overallInterpretation")) {
            if(hgscReport.getOverallInterpretation() != null && !hgscReport.getOverallInterpretation().equals("")) {
                obsOverall.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                        .setCode("LA6576-8").setDisplay(hgscReport.getOverallInterpretation())));
            }
        }

        //extensions
        Extension ext = new Extension("https://emerge.hgsc.bcm.edu/fhir/StructureDefinition/interpretation-summary-text",
                new StringType("Summary text for the overall interpretation if available"

                        //map; panelSummary

                ));
        obsOverall.addExtension(ext);

        return obsOverall;
    }
}
