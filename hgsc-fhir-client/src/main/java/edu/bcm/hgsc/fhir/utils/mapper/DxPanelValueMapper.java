package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class DxPanelValueMapper {

    public Observation dxPanelValueMapping(Observation dxPanel, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport, SimpleDateFormat sdf) throws ParseException {

    	//Profile
        dxPanel.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/grouper");
    	//Status
        if (mappingConfig.containsKey("HgscEmergeReport.reportStatus")) {
            dxPanel.setStatus(Observation.ObservationStatus.fromCode(hgscEmergeReport.getReportStatus().toLowerCase()));
        }
        //Category
        dxPanel.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        dxPanel.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("TBD-grouper")));
        //EffectiveDateTime
        if (mappingConfig.containsKey("HgscEmergeReport.sampleCollectedDate")) {
            dxPanel.setEffective(new DateTimeType(sdf.parse(hgscEmergeReport.getSampleCollectedDate())));
        }
        //Issued
        if (mappingConfig.containsKey("HgscEmergeReport.reportDate")) {
            dxPanel.setIssued(sdf.parse(hgscEmergeReport.getReportDate()));
        }
        //BodySite
        if (mappingConfig.containsKey("HgscEmergeReport.sampleCollectionSource")) {
            dxPanel.setBodySite(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                    .setCode("119297000").setDisplay(hgscEmergeReport.getSampleCollectionSource())));
        }

        return dxPanel;
    }
}
