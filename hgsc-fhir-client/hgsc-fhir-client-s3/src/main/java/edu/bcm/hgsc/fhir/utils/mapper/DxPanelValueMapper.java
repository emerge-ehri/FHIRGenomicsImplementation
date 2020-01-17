package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class DxPanelValueMapper {

    public Observation dxPanelValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Observation dxPanel = new Observation();

        dxPanel.setLanguage(hgscReport.getLanguage());

        //Profile
        dxPanel.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/grouper");
    	//Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if (hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                dxPanel.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Category
        dxPanel.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        dxPanel.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://hl7.org/fhir/uv/genomics-reporting/CodeSystem/tbd-codes")
                .setCode("grouper")));
        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                dxPanel.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }

        return dxPanel;
    }
}
