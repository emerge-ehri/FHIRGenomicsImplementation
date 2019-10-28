package edu.bcm.hgsc.fhir.utils.mapper;

import java.util.HashMap;

import edu.bcm.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.StringType;

public class PgxPanelValueMapper {

    public Observation pgxPanelValueMapping(Observation pgxPanel, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport) {

    	//Profile
        pgxPanel.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/grouper");
    	//extensions
        Extension ext = new Extension("http://hl7.org/fhir/StructureDefinition/interpretation-summary-text",
                new StringType("Summary text if available"));
        pgxPanel.addExtension(ext);
    	//Status
        if (mappingConfig.containsKey("HgscEmergeReport.reportStatus")) {
            pgxPanel.setStatus(Observation.ObservationStatus.fromCode(hgscEmergeReport.getReportStatus().toLowerCase()));
        }
        //Category
        pgxPanel.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxPanel.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("TBD-grouper")));

        return pgxPanel;
    }
}
