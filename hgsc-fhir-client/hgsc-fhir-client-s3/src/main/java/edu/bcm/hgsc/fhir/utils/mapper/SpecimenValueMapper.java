package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class SpecimenValueMapper {

    public Specimen specimenValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Specimen specimen = new Specimen();

        specimen.setLanguage(hgscReport.getLanguage());

        //Profile
        specimen.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/specimen");
        //Identifier
        //The code SID creates warnings during validation but until the HL7 Vocabulary group resolves this, we will be ignoring the warnings.
        if (mappingConfig.containsKey("HgscReport.patientSampleID")) {
            if(hgscReport.getPatientSampleID() != null && !hgscReport.getPatientSampleID().equals("")) {
                specimen.addIdentifier(new Identifier().setSystem("http://namingsystem.org/").setValue(hgscReport.getPatientSampleID())
                        .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                                .setCode("SID").setDisplay("Specimen ID"))));
                //AccessionIdentifier
                specimen.setAccessionIdentifier(new Identifier().setSystem("http://namingsystem.org/").setValue(hgscReport.getPatientSampleID())
                        .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                                .setCode("ACSN").setDisplay("Accession ID"))));
            }
        }

        //Type
        specimen.setType(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                .setCode("258566005").setDisplay("Deoxyribonucleic acid sample")));
        //ReceivedTime
        if (mappingConfig.containsKey("HgscReport.sampleReceivedDate")) {
            if(hgscReport.getSampleReceivedDate() != null && !hgscReport.getSampleReceivedDate().equals("")) {
                specimen.setReceivedTime(sdf.parse(hgscReport.getSampleReceivedDate()));
            }
        }
        //Collection
        if (mappingConfig.containsKey("HgscReport.sampleCollectedDate")) {
            if(hgscReport.getSampleCollectedDate() != null && !hgscReport.getSampleCollectedDate().equals("")) {
                specimen.setCollection(new Specimen.SpecimenCollectionComponent().setCollected(new DateTimeType(sdf.parse(hgscReport.getSampleCollectedDate())))
                        .setBodySite(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                                .setCode("119297000").setDisplay("Blood specimen (specimen)"))));
            }
        }

        return specimen;
    }
}
