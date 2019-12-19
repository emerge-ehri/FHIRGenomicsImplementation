package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import edu.bcm.hgsc.fhir.models.Variant;
import edu.bcm.hgsc.fhir.utils.FhirResourcesMappingUtils;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.r4.model.Observation.ObservationComponentComponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class DxSNPINDELVariantsValueMapper {

    public HashMap<String, Observation> dxSNPINDELVariantsValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        FhirResourcesMappingUtils util = new FhirResourcesMappingUtils();

        HashMap<String, Observation> dxSNPINDELVariants = new HashMap<String, Observation>();

        Observation dxSNPINDELVariant = new Observation();

        dxSNPINDELVariant.setLanguage("en-US");

        //Profile
        dxSNPINDELVariant.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/variant");
        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if (hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                dxSNPINDELVariant.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Category
        dxSNPINDELVariant.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        dxSNPINDELVariant.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("69548-6").setDisplay("Genetic variant assessment")));

        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                dxSNPINDELVariant.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }
        //ValueCodeableConcept
        if(hgscReport.getVariants().size() > 0){
            dxSNPINDELVariant.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                    .setCode("LA9633-4").setDisplay("Present")));
        }else{
            dxSNPINDELVariant.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                    .setCode("LA9634-2").setDisplay("No Variant")));
        }

        //Component:ref-sequence-assembly
        if(hgscReport.getHumanReferenceSequenceAssemblyVersion() != null && !hgscReport.getHumanReferenceSequenceAssemblyVersion().equals("")) {
            dxSNPINDELVariant.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                    new Coding().setSystem("http://loinc.org").setCode("62374-4").setDisplay("Human reference sequence assembly version")))
                    .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                            .setCode("LA14029-5").setDisplay(hgscReport.getHumanReferenceSequenceAssemblyVersion()))));
        }

        //Component:coordinate-system
        if(hgscReport.getGenomicCoordinateSystem() != null && !hgscReport.getGenomicCoordinateSystem().equals("")) {
            dxSNPINDELVariant.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                    new Coding().setSystem("http://loinc.org").setCode("92822-6").setDisplay("Genomic coordinate system [Type]")))
                    .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                            .setCode("LA30102-0").setDisplay(hgscReport.getGenomicCoordinateSystem()))));
        }

        //Component:genomic-source-class
        if(hgscReport.getGenomicSource() != null && !hgscReport.getGenomicSource().equals("")) {
            dxSNPINDELVariant.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                    new Coding().setSystem("http://loinc.org").setCode("48002-0").setDisplay("Genomic source class [Type]")))
                    .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                            .setCode("LA6683-2").setDisplay(hgscReport.getGenomicSource()))));
        }

        //Create multiple dxSNPINDELVariants if there is multiple variants
        if(hgscReport.getVariants() != null && hgscReport.getVariants().size() > 0) {
            for(Variant v : hgscReport.getVariants()) {
                Observation temp = (Observation)(util.deepCopy(dxSNPINDELVariant));

                //Component:chromosome (extension)
                if(v.getChromosome() != null && !v.getChromosome().equals("")) {
                    dxSNPINDELVariant.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                            new Coding().setSystem("http://loinc.org").setCode("48000-4").setDisplay("Chromosome, Blood or Tissue Specimen")))
                            .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                                    .setCode("LA21263-1").setDisplay("Chromosome " + v.getChromosome()))));
                }

                //Component:ref-allele
                if(v.getRef() != null && !v.getRef().equals("")) {
                    temp.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                            new Coding().setSystem("http://loinc.org").setCode("69547-8").setDisplay("Genomic ref allele [ID]")))
                            .setValue(new StringType(v.getRef())));
                }

                //Component:alt-allele
                if(v.getAlt() != null && !v.getAlt().equals("")) {
                    temp.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                            new Coding().setSystem("http://loinc.org").setCode("69551-0").setDisplay("Genomic alt allele [ID]")))
                            .setValue(new StringType(v.getAlt())));
                }

                //Component:exact-start-end
                if(v.getPosition() != null && !v.getPosition().equals("")) {
                    temp.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                            new Coding().setSystem("http://loinc.org").setCode("81254-5").setDisplay("Genomic allele start-end")))
                            .setValue(new Range().setLow(new Quantity(Long.parseLong(v.getPosition())))));
                }

                //Component:allelic-state
                if(v.getZygosity() != null && !v.getZygosity().equals("")) {
                    temp.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                            new Coding().setSystem("http://loinc.org").setCode("53034-5").setDisplay("Allelic state")))
                            .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                                    .setCode("LA6706-1").setDisplay(v.getZygosity()))));
                }

                //Component:gene-studied
                if(v.getHgncID() != null && !v.getHgncID().equals("") && v.getGene() != null && !v.getGene().equals("")) {
                    temp.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                            new Coding().setSystem("http://loinc.org").setCode("48018-6").setDisplay("Gene studied [ID]")))
                            .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://www.genenames.org/geneId")
                                    .setCode(v.getHgncID()).setDisplay(v.getGene()))));
                }

                //Component:genomic-dna-chg
                if(v.getGenomic() != null && !v.getGenomic().equals("")) {
                    temp.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                            new Coding().setSystem("http://loinc.org").setCode("81290-9").setDisplay("Genomic DNA change (gHGVS)")))
                            .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://varnomen.hgvs.org/")
                                    .setCode(v.getGenomic()).setDisplay(v.getGenomic()))));
                }

                //Component:transcript-ref-seq
                if(v.getTranscript() != null && !v.getTranscript().equals("")) {
                    temp.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                            new Coding().setSystem("http://loinc.org").setCode("51958-7").setDisplay("Transcript reference sequence [ID]")))
                            .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://www.ncbi.nlm.nih.gov/nuccore")
                                    .setCode(v.getTranscript()).setDisplay(v.getTranscript()))));
                }

                //Component:dna-chg
                if(v.getCDNA() != null && !v.getCDNA().equals("")) {
                    temp.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                            new Coding().setSystem("http://loinc.org").setCode("48004-6").setDisplay("DNA change (c.HGVS)")))
                            .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://varnomen.hgvs.org")
                                    .setCode(v.getCDNA()).setDisplay(v.getCDNA()))));
                }

                //Component:variation-inheritance
                if(v.getInheritance() != null && !v.getInheritance().equals("")) {
                    temp.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                            new Coding().setSystem("http://loinc.org").setCode("79742-3").setDisplay("Inheritance pattern based on family history")))
                            .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                                    .setCode(v.getInheritance()).setDisplay(v.getInheritance()))));
                }

                //Component:amino-acid-chg
                if(v.getProteinChange() != null && !v.getProteinChange().equals("")) {
                    temp.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                            new Coding().setSystem("http://loinc.org").setCode("48005-3").setDisplay("Amino acid change (pHGVS)")))
                            .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://varnomen.hgvs.org/")
                                    .setCode(v.getProteinChange()).setDisplay(v.getProteinChange()))));
                }

                //Component:dbSNP-id
                if(v.getDbSNPID() != null && !v.getDbSNPID().equals("")) {
                    temp.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                            new Coding().setSystem("http://loinc.org").setCode("81255-2").setDisplay("dbSNP [ID]")))
                            .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://www.ncbi.nlm.nih.gov/snp/")
                                    .setCode(v.getDbSNPID()).setDisplay(v.getDbSNPID()))));
                }

                dxSNPINDELVariants.put(v.getGene(), temp);
            }
        }

        return dxSNPINDELVariants;
    }
}
