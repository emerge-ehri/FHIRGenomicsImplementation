package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class ObsInhDisPathsValueMapper {

    public Observation obsInhDisPathsValueMapping(Observation obsInhDisPaths, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport, SimpleDateFormat sdf) throws ParseException {

        //Observation-secondaryFinding
        //if (mappingConfig.containsKey("HgscEmergeReport.secondaryFinding"))
        Extension secondaryFinding = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/inherited-disease-pathogenicity",
                new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/special-values").setCode("true").setDisplay("true"))
                .addCoding(new Coding().setSystem("http://hl7.org/fhir/StructureDefinition/observation-secondaryFinding").setCode("acmg-version2").setDisplay("ACMG Version 2"))
                .setText("map;variant.secondaryFindingText???"));
        obsInhDisPaths.addExtension(secondaryFinding);

        //Status
        if (mappingConfig.containsKey("HgscEmergeReport.reportStatus")) {
            obsInhDisPaths.setStatus(Observation.ObservationStatus.fromCode(hgscEmergeReport.getReportStatus().toLowerCase()));
        }
        //Category
        obsInhDisPaths.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        obsInhDisPaths.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("53037-8").setDisplay("Genetic variation clinical significance")));
        //EffectiveDateTime
        if (mappingConfig.containsKey("HgscEmergeReport.sampleCollectedDate")) {
            obsInhDisPaths.setEffective(new DateTimeType(sdf.parse(hgscEmergeReport.getSampleCollectedDate())));
        }
        //Issued
        if (mappingConfig.containsKey("HgscEmergeReport.reportDate")) {
            obsInhDisPaths.setIssued(sdf.parse(hgscEmergeReport.getReportDate()));
        }

        //ValueCodeableConcept
        if (mappingConfig.containsKey("HgscEmergeReport.overallInterpretation")) {
            obsInhDisPaths.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org/LL4034-6")
                    .setCode("LA6668-3").setDisplay(hgscEmergeReport.getVariants().get(0).getInterpretation())));
        }
        //BodySite
        if (mappingConfig.containsKey("HgscEmergeReport.sampleCollectionSource")) {
            obsInhDisPaths.setBodySite(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                    .setCode("119297000").setDisplay(hgscEmergeReport.getSampleCollectionSource())));
        }

        //extensions
        Extension ext = new Extension("http:/xxx/fhir/StructureDefinition/interpretation-summary-text",
                new StringType(hgscEmergeReport.getVariants().get(0).getInterpretation()));
        obsInhDisPaths.addExtension(ext);

        //Component:associated-phenotype
        obsInhDisPaths.addComponent(new Observation.ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("81259-4").setDisplay("Associated Phenotype")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://omim.org/")
                        .setCode("192500").setDisplay("Long QT syndrome 1"))));
        obsInhDisPaths.addComponent(new Observation.ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("81259-4").setDisplay("Associated Phenotype")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://omim.org/")
                        .setCode("map; variant.diseases[].code?").setDisplay("map; variant.diseases[].text?"))));
        //Component:mode-of-inheritance
        obsInhDisPaths.addComponent(new Observation.ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("TBD-mode-of-inheritance").setDisplay("Mode of Inheritance")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org/LL3731-8")
                        .setCode("LA24640-7").setDisplay(hgscEmergeReport.getVariants().get(0).getInheritance()))));

        return obsInhDisPaths;
    }
}
