package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import edu.bcm.hgsc.fhir.models.Variant;
import edu.bcm.hgsc.fhir.utils.FhirResourcesMappingUtils;
import org.hl7.fhir.r4.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class ObsInhDisPathsValueMapper {

    public HashMap<String, Observation> obsInhDisPathsValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        FhirResourcesMappingUtils util = new FhirResourcesMappingUtils();

        HashMap<String, Observation> obsInhDisPaths = new HashMap<String, Observation>();

        Observation obsInhDisPath = new Observation();

        //Observation-secondaryFinding
        if (mappingConfig.containsKey("HgscReport.secondaryFinding")) {
            Extension secondaryFinding = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/inherited-disease-pathogenicity",
                    new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/special-values").setCode("true").setDisplay("true")));
            obsInhDisPath.addExtension(secondaryFinding);
        }

        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            obsInhDisPath.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
        }
        //Category
        obsInhDisPath.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        obsInhDisPath.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("53037-8").setDisplay("Genetic variation clinical significance")));
        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            obsInhDisPath.setIssued(sdf.parse(hgscReport.getReportDate()));
        }

        //Component:associated-phenotype
        obsInhDisPath.addComponent(new Observation.ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("81259-4").setDisplay("Associated Phenotype")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://omim.org/")
                        .setCode("192500").setDisplay("Long QT syndrome 1"))));
        obsInhDisPath.addComponent(new Observation.ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                new Coding().setSystem("http://loinc.org").setCode("81259-4").setDisplay("Associated Phenotype")))
                .setValue(new CodeableConcept().addCoding(new Coding().setSystem("https://registry.identifiers.org/registry/mim")
                        .setCode("252900").setDisplay("Lange-Nielsen syndrome 1"))));

        //Create multiple obsInhDisPaths if there is multiple variants
        for(Variant v : hgscReport.getVariants()) {

            Observation temp = (Observation)(util.deepCopy(obsInhDisPath));

            //ValueCodeableConcept
            if (mappingConfig.containsKey("HgscReport.overallInterpretation")) {
                temp.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                        .setCode("LA6668-3").setDisplay(v.getInterpretation())));
            }

            //extensions
            Extension ext = new Extension("http:/xxx/fhir/StructureDefinition/interpretation-summary-text",
                    new StringType(v.getInterpretation()));
            temp.addExtension(ext);

            obsInhDisPaths.put(v.getGene(), temp);
        }

        return obsInhDisPaths;
    }
}
