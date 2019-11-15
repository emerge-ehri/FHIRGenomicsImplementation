package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.r4.model.Observation.ObservationComponentComponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class DxSNPINDELVariantsValueMapper {

    public Observation dxSNPINDELVariantsValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Observation dxSNPINDELVariants = new Observation();

        //Profile
        dxSNPINDELVariants.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/obs-variant");
        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            dxSNPINDELVariants.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
        }
        //Category
        dxSNPINDELVariants.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        dxSNPINDELVariants.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("69548-6").setDisplay("Genetic variant assessment")));

        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            dxSNPINDELVariants.setIssued(sdf.parse(hgscReport.getReportDate()));
        }
        //ValueCodeableConcept
        if(hgscReport.getVariants().size() > 0){
            dxSNPINDELVariants.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                    .setCode("LA9633-4").setDisplay("Present")));
        }else{
            dxSNPINDELVariants.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                    .setCode("LA9634-2").setDisplay("No Variant")));
        }

        //Component:gene-studied
        dxSNPINDELVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("48018-6").setDisplay("Gene studied [ID]")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://genenames.org")
                        .setCode("HGNC:6294").setDisplay(hgscReport.getVariants().get(0).getGene()))));
        //Component:ref-sequence-assembly
        dxSNPINDELVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("62374-4").setDisplay("Human reference sequence assembly version")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                        .setCode("LA14029-5").setDisplay("GRCH37")).setText("hg19")));
        //Component:dna-chg
        dxSNPINDELVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("48004-6").setDisplay("DNA change (c.HGVS)")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://varnomen.hgvs.org")
                        .setCode(hgscReport.getVariants().get(0).getCDNA())
                        .setDisplay(hgscReport.getVariants().get(0).getCDNA()))));

        //Component:variation-code
        dxSNPINDELVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("81252-9").setDisplay("Discrete genetic variant")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://www.ncbi.nlm.nih.gov/clinvar/variation/")
                        .setCode("3131").setDisplay("NM_181798.1(KCNQ1):c.1171C>T (p.Arg391Ter)"))));
        //Component:dbSNP-id
        dxSNPINDELVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("81255-2").setDisplay("dbSNP [ID]")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://www.ncbi.nlm.nih.gov/snp/"))));

        //Component:genomic-dna-chg
        dxSNPINDELVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("81290-9").setDisplay("Genomic DNA change (gHGVS)")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://varnomen.hgvs.org/")
                        .setCode(hgscReport.getVariants().get(0).getGenomic())
                        .setDisplay(hgscReport.getVariants().get(0).getGenomic()))));
        //Component:genomic-source-class
        dxSNPINDELVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("48002-0").setDisplay("Genomic source class [Type]")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                        .setCode("LL378-1").setDisplay(hgscReport.getGenomicSource()))));
        //Component:amino-acid-chg
        dxSNPINDELVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("48005-3").setDisplay("Amino acid change (pHGVS)")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://varnomen.hgvs.org/")
                        .setCode(hgscReport.getVariants().get(0).getProteinChange())
                        .setDisplay(hgscReport.getVariants().get(0).getProteinChange()))));

        //Component:amino-acid-chg-type
        dxSNPINDELVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("48006-1").setDisplay("Amino acid change [Type]")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                        .setCode("???").setDisplay("???"))));
        //Component:transcript-ref-seq
        dxSNPINDELVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("51958-7").setDisplay("Transcript reference sequence [ID]")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://www.ncbi.nlm.nih.gov/")
                        .setCode(hgscReport.getVariants().get(0).getTranscript())
                        .setDisplay(hgscReport.getVariants().get(0).getTranscript()))));
        //Component:allelic-state
        dxSNPINDELVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("53034-5").setDisplay("Allelic state")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://www.ncbi.nlm.nih.gov/protein/")
                        .setCode("LA6706-1").setDisplay(hgscReport.getVariants().get(0).getZygosity()))));

        //Component:ref-allele
        dxSNPINDELVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("69547-8").setDisplay("Genomic ref allele [ID]")))
                .setValue(new StringType(hgscReport.getVariants().get(0).getRef())));
        //Component:alt-allele
        dxSNPINDELVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("69551-0").setDisplay("Genomic alt allele [ID]")))
                .setValue(new StringType(hgscReport.getVariants().get(0).getAlt())));
        //Component:coordinate-system
        dxSNPINDELVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("92822-6").setDisplay("Genomic coordinate system [Type]")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                        .setCode("LA30102-0").setDisplay("1-based character counting"))));

        //Component:allele-start-end
        dxSNPINDELVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("81254-5").setDisplay("Genomic allele start-end")))
                .setValue(new Range().setLow(new Quantity(Long.parseLong(hgscReport.getVariants().get(0).getPosition())))
                        .setHigh(new Quantity(Long.parseLong(hgscReport.getVariants().get(0).getPosition()))))
        );

        return dxSNPINDELVariants;
    }
}
