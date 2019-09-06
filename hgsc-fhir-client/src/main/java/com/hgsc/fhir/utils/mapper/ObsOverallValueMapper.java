package com.hgsc.fhir.utils.mapper;

import com.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

import java.util.Date;
import java.util.HashMap;

public class ObsOverallValueMapper {

    public Observation obsOverallValueMapping(Observation obsOverall, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport) {

        //Text
        obsOverall.setText(new Narrative().setStatus(Narrative.NarrativeStatus.EMPTY).setDiv(new XhtmlNode().setValue("fixed")));
        //Profile
        obsOverall.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/obs-overall");
        //Identifier
        if (mappingConfig.containsKey("HgscEmergeReport.reportIdentifier")) {
            obsOverall.addIdentifier(new Identifier().setSystem("https://emerge.hgsc.bcm.edu/").setValue(hgscEmergeReport.getReportIdentifier())
                    .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                            .setCode("FILL").setDisplay("Filler Identifier"))));
        }
        //Status
        if (mappingConfig.containsKey("HgscEmergeReport.reportStatus")) {
            obsOverall.setStatus(Observation.ObservationStatus.fromCode(hgscEmergeReport.getReportStatus().toLowerCase()));
        }
        //Category
        obsOverall.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        obsOverall.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("51968-6").setDisplay("Genetic disease analysis overall interpretation")));
        //EffectiveDateTime
        if (mappingConfig.containsKey("HgscEmergeReport.sampleCollectedDate")) {
            obsOverall.setEffective(new DateTimeType(new Date(hgscEmergeReport.getSampleCollectedDate())));
        }
        //Issued
        if (mappingConfig.containsKey("HgscEmergeReport.reportDate")) {
            obsOverall.setIssued(new Date(hgscEmergeReport.getReportDate()));
        }
        //ValueCodeableConcept
        if (mappingConfig.containsKey("HgscEmergeReport.overallInterpretation")) {
            obsOverall.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.orgvs/LL541-4")
                    .setCode("LA6576-8").setDisplay(hgscEmergeReport.getOverallInterpretation())));
        }

        //extensions
        Extension ext = new Extension("http://hl7.org/fhir/StructureDefinition/interpretation-summary-text",
                new StringType("Summary text for the overall interpretation if available"));
        obsOverall.addExtension(ext);

        //BodySite
        if (mappingConfig.containsKey("HgscEmergeReport.sampleCollectionSource")) {
            obsOverall.setBodySite(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                    .setCode("119297000").setDisplay(hgscEmergeReport.getSampleCollectionSource()))
                    .setText(hgscEmergeReport.getSampleCollectionSource()));
        }

        return obsOverall;
    }
}
