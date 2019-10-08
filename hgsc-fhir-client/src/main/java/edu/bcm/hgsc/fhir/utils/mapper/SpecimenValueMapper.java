package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.*;

import java.util.Date;
import java.util.HashMap;

public class SpecimenValueMapper {

    public Specimen specimenValueMapping(Specimen specimen, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport) {

        //Profile
        specimen.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/specimen");
        //Identifier
        //The code SID creates warnings during validation but until the HL7 Vocabulary group resolves this, we will be ignoring the warnings.
        if (mappingConfig.containsKey("HgscEmergeReport.patientSampleID")) {
            specimen.addIdentifier(new Identifier().setSystem("https://emerge.mc.vanderbilt.edu/").setValue(hgscEmergeReport.getPatientSampleID())
                    .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                            .setCode("SID").setDisplay("Specimen ID"))));
        }
        //AccessionIdentifier
        if (mappingConfig.containsKey("HgscEmergeReport.accessionNumber")) {
            specimen.addIdentifier(new Identifier().setSystem("https://emerge.hgsc.bcm.edu/").setValue(hgscEmergeReport.getAccessionNumber())
                    .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                            .setCode("ACSN").setDisplay("Accession ID"))));
        }
        //Type
        specimen.setType(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                .setCode("258566005").setDisplay("Deoxyribonucleic acid sample")));
        //ReceivedTime
        if (mappingConfig.containsKey("HgscEmergeReport.sampleReceivedDate")) {
            specimen.setReceivedTime(new Date(hgscEmergeReport.getSampleReceivedDate()));
        }
        //Collection
        if (mappingConfig.containsKey("HgscEmergeReport.sampleCollectedDate")) {
            specimen.setCollection(new Specimen.SpecimenCollectionComponent().setCollected(new DateTimeType(new Date(hgscEmergeReport.getSampleCollectedDate())))
                    .setBodySite(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                            .setCode("119297000").setDisplay("Blood specimen (specimen)"))));
        }

        return specimen;
    }
}
