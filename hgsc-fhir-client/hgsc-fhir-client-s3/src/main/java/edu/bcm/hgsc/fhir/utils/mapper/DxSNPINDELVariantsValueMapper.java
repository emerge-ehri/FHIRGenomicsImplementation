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

    public HashMap<String, Observation> dxSNPINDELVariantsValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf, HashMap<String, HashMap<String, String>> loincCodeMap) throws ParseException {

        FhirResourcesMappingUtils util = new FhirResourcesMappingUtils();

        HashMap<String, String> variantChromosomeCodeMap = loincCodeMap.get("variantChromosomeCodeMap");
        HashMap<String, String> humanReferenceSequenceAssemblyVersionCodeMap = loincCodeMap.get("humanReferenceSequenceAssemblyVersionCodeMap");
        HashMap<String, String> genomicCoordinateSystemCodeMap = loincCodeMap.get("genomicCoordinateSystemCodeMap");
        HashMap<String, String> genomicSourceCodeMap = loincCodeMap.get("genomicSourceCodeMap");
        HashMap<String, String> variantZygosityCodeMap = loincCodeMap.get("variantZygosityCodeMap");
        HashMap<String, String> variantsCodeMap = loincCodeMap.get("variantsCodeMap");
        HashMap<String, String> variantInheritanceCodeMap = loincCodeMap.get("variantInheritanceCodeMap");

        HashMap<String, Observation> dxSNPINDELVariants = new HashMap<String, Observation>();

        Observation dxSNPINDELVariant = new Observation();

        dxSNPINDELVariant.setLanguage(hgscReport.getLanguage());

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
                    .setCode(variantsCodeMap.get("Present")).setDisplay("Present")));
        }else{
            dxSNPINDELVariant.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                    .setCode(variantsCodeMap.get("Absent")).setDisplay("Absent")));
        }

        //Component:ref-sequence-assembly
        if(hgscReport.getHumanReferenceSequenceAssemblyVersion() != null && !hgscReport.getHumanReferenceSequenceAssemblyVersion().equals("")) {
            dxSNPINDELVariant.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                    new Coding().setSystem("http://loinc.org").setCode("62374-4").setDisplay("Human reference sequence assembly version")))
                    .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                            .setCode(humanReferenceSequenceAssemblyVersionCodeMap.get(hgscReport.getHumanReferenceSequenceAssemblyVersion()))
                            .setDisplay(hgscReport.getHumanReferenceSequenceAssemblyVersion()))));
        }

        //Component:coordinate-system
        if(hgscReport.getGenomicCoordinateSystem() != null && !hgscReport.getGenomicCoordinateSystem().equals("")) {
            dxSNPINDELVariant.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                    new Coding().setSystem("http://loinc.org").setCode("92822-6").setDisplay("Genomic coordinate system [Type]")))
                    .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                            .setCode(genomicCoordinateSystemCodeMap.get(hgscReport.getGenomicCoordinateSystem()))
                            .setDisplay(hgscReport.getGenomicCoordinateSystem()))));
        }

        //Component:genomic-source-class
        if(hgscReport.getGenomicSource() != null && !hgscReport.getGenomicSource().equals("")) {
            dxSNPINDELVariant.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                    new Coding().setSystem("http://loinc.org").setCode("48002-0").setDisplay("Genomic source class [Type]")))
                    .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                            .setCode(genomicSourceCodeMap.get(hgscReport.getGenomicSource())).setDisplay(hgscReport.getGenomicSource()))));
        }

        //Create multiple dxSNPINDELVariants if there is multiple variants
        if(hgscReport.getVariants() != null && hgscReport.getVariants().size() > 0) {
            for(Variant v : hgscReport.getVariants()) {
                Observation temp = (Observation)(util.deepCopy(dxSNPINDELVariant));

                //Component:chromosome-identifier (extension)
                if(v.getChromosome() != null && !v.getChromosome().equals("")) {
                    temp.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                            new Coding().setSystem("http://loinc.org").setCode("48000-4").setDisplay("Chromosome, Blood or Tissue Specimen")))
                            .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                                    .setCode(variantChromosomeCodeMap.get(v.getChromosome())).setDisplay(v.getChromosome()))));
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
                    if(!"Mosaic".equals(v.getZygosity())){
                        temp.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                                new Coding().setSystem("http://loinc.org").setCode("53034-5").setDisplay("Allelic state")))
                                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                                        .setCode(variantZygosityCodeMap.get(v.getZygosity())).setDisplay(v.getZygosity()))));
                    }else{
                        temp.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                                new Coding().setSystem("http://loinc.org").setCode("53034-5").setDisplay("Allelic state")))
                                .setValue(new CodeableConcept().setText(v.getZygosity())));
                    }
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
                            .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://varnomen.hgvs.org/")
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
                            .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://varnomen.hgvs.org")
                                    .setCode(v.getCDNA()).setDisplay(v.getCDNA()))));
                }

                //Component:variation-inheritance
                if(v.getInheritance() != null && !v.getInheritance().equals("")) {
                    if(!"Autosomal dominant / Autosomal recessive".equals(v.getInheritance())){
                        temp.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                                new Coding().setSystem("http://loinc.org").setCode("79742-3").setDisplay("Inheritance pattern based on family history")))
                                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                                        .setCode(variantInheritanceCodeMap.get(v.getInheritance())).setDisplay(v.getInheritance()))));
                    }else{
                        temp.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                                new Coding().setSystem("http://loinc.org").setCode("79742-3").setDisplay("Inheritance pattern based on family history")))
                                .setValue(new CodeableConcept().setText(v.getInheritance())));
                    }
                }

                //Component:amino-acid-chg
                if(v.getProteinChange() != null && !v.getProteinChange().equals("")) {
                    temp.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                            new Coding().setSystem("http://loinc.org").setCode("48005-3").setDisplay("Amino acid change (pHGVS)")))
                            .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://varnomen.hgvs.org/")
                                    .setCode(v.getProteinChange()).setDisplay(v.getProteinChange()))));
                }

                //Component:dbSNP-id
                if(v.getDbSNPID() != null && !v.getDbSNPID().equals("")) {
                    temp.addComponent(new ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                            new Coding().setSystem("http://loinc.org").setCode("81255-2").setDisplay("dbSNP [ID]")))
                            .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://www.ncbi.nlm.nih.gov/snp/")
                                    .setCode(v.getDbSNPID()).setDisplay(v.getDbSNPID()))));
                }

                dxSNPINDELVariants.put(v.getExternalId(), temp);
            }
        }

        return dxSNPINDELVariants;
    }
}
