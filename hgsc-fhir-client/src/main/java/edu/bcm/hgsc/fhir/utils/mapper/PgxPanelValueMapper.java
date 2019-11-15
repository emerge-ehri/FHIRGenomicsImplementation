package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class PgxPanelValueMapper {

    public Observation pgxPanelValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxPanel = new Observation();

        //Profile
        pgxPanel.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/grouper");
    	//Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            pgxPanel.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
        }
        //Category
        pgxPanel.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxPanel.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("TBD-grouper")));
        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            pgxPanel.setIssued(sdf.parse(hgscReport.getReportDate()));
        }

        return pgxPanel;
    }
}
