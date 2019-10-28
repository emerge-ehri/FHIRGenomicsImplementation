package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Observation.ObservationComponentComponent;

import java.util.Date;
import java.util.HashMap;

public class DxCNVVariantsValueMapper {

    public Observation dxCNVVariantsValueMapping(Observation dxCNVVariants, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport) {
        //Profile
        dxCNVVariants.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/obs-variant");
        //Status
        if (mappingConfig.containsKey("HgscEmergeReport.reportStatus")) {
            dxCNVVariants.setStatus(Observation.ObservationStatus.fromCode(hgscEmergeReport.getReportStatus().toLowerCase()));
        }
        //Category
        dxCNVVariants.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        dxCNVVariants.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("69548-6").setDisplay("Genetic variant assessment")));
        //EffectiveDateTime
        if (mappingConfig.containsKey("HgscEmergeReport.sampleCollectedDate")) {
            dxCNVVariants.setEffective(new DateTimeType(new Date(hgscEmergeReport.getSampleCollectedDate())));
        }
        //Issued
        if (mappingConfig.containsKey("HgscEmergeReport.reportDate")) {
            dxCNVVariants.setIssued(new Date(hgscEmergeReport.getReportDate()));
        }
        //ValueCodeableConcept
        dxCNVVariants.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("LA9633-4").setDisplay("Present")));
        //Component:gene-studied
        dxCNVVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("48018-6").setDisplay("Gene studied [ID]")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://genenames.org")
                        .setCode("HGNC:6294").setDisplay("KCNQ1"))));
        //Component:ref-sequence-assembly
        dxCNVVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("62374-4").setDisplay("Human reference sequence assembly version")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                        .setCode("LA14029-5").setDisplay("GRCH37")).setText("hg19")));
        //Component:dna-chg
        dxCNVVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("48004-6").setDisplay("DNA change (c.HGVS)")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://varnomen.hgvs.org")
                        .setCode("c.1552C>T").setDisplay("c.1552C>T"))));
        //Component:dna-chg-type
        dxCNVVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("48019-4").setDisplay("DNA change type")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                        .setCode("LA6690-7").setDisplay("Substitution"))));
        //Component:variation-code
        dxCNVVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("81252-9").setDisplay("Discrete genetic variant")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://www.ncbi.nlm.nih.gov/clinvar/variation/")
                        .setCode("3131").setDisplay("NM_181798.1(KCNQ1):c.1171C>T (p.Arg391Ter)"))));
        //Component:dbSNP-id
        dxCNVVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("81255-2").setDisplay("dbSNP [ID]")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://www.ncbi.nlm.nih.gov/snp/"))));
        //Component:genomic-dna-chg
        dxCNVVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("81290-9").setDisplay("Genomic DNA change (gHGVS)")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://varnomen.hgvs.org/")
                        .setCode("g.2790111C>T").setDisplay("g.2790111C>T"))));
        //Component:genomic-source-class
        dxCNVVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("48002-0").setDisplay("Genomic source class [Type]")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                        .setCode("LL378-1").setDisplay("Germline"))));
        //Component:amino-acid-chg
        dxCNVVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("48005-3").setDisplay("Amino acid change (pHGVS)")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://varnomen.hgvs.org/")
                        .setCode("p.Arg518*").setDisplay("p.Arg518*"))));
        //Component:amino-acid-chg-type
        dxCNVVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("48006-1").setDisplay("Amino acid change [Type]")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                        .setCode("???").setDisplay("???"))));
        //Component:transcript-ref-seq
        dxCNVVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("51958-7").setDisplay("Transcript reference sequence [ID]")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://www.ncbi.nlm.nih.gov/")
                        .setCode("NM_000218.2").setDisplay("NM_000218.2"))));
        //Component:genomic-ref-seq
        dxCNVVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("51958-7").setDisplay("Transcript reference sequence [ID]")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://www.ncbi.nlm.nih.gov/")
                        .setCode("NM_000218.2").setDisplay("NM_000218.2"))));
        //Component:copy-number
        dxCNVVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("82155-3").setDisplay("Genomic structural variant copy number")))
                //.setValue(new Quantity().setValue("???"))
        );
        dxCNVVariants.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("69547-8").setDisplay("Genomic ref allele [ID]")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://www.ncbi.nlm.nih.gov/")
                        .setCode("C").setDisplay("C"))));

        return dxCNVVariants;
    }
}
