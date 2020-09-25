package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.Disease;
import edu.bcm.hgsc.fhir.models.HgscReport;
import edu.bcm.hgsc.fhir.models.Variant;
import edu.bcm.hgsc.fhir.utils.FhirResourcesMappingUtils;
import org.hl7.fhir.r4.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class ObsInhDisPathsValueMapper {

    public HashMap<String, Observation> obsInhDisPathsValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf, HashMap<String, HashMap<String, String>> loincCodeMap) throws ParseException {

        FhirResourcesMappingUtils util = new FhirResourcesMappingUtils();

        HashMap<String, Observation> obsInhDisPaths = new HashMap<String, Observation>();

        HashMap<String, String> variantInterpretationCodeMap = loincCodeMap.get("variantInterpretationCodeMap");

        Observation obsInhDisPath = new Observation();

        //Profile
        obsInhDisPath.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/inherited-disease-pathogenicity");
        //Language
        obsInhDisPath.setLanguage(hgscReport.getLanguage());
        //Observation-secondaryFinding
        Extension observationSecondaryFinding = new Extension("http://hl7.org/fhir/StructureDefinition/observation-secondaryFinding",
                new CodeableConcept().setText("See methodology for details."));
        obsInhDisPath.addExtension(observationSecondaryFinding);
        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if (hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                obsInhDisPath.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Category
        obsInhDisPath.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        obsInhDisPath.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("53037-8").setDisplay("Genetic variation clinical significance")));
        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                obsInhDisPath.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }

        //Create multiple obsInhDisPaths if there is multiple variants
        if(hgscReport.getVariants() != null && hgscReport.getVariants().size() > 0) {
            for(Variant v : hgscReport.getVariants()) {

                Observation temp = (Observation)(util.deepCopy(obsInhDisPath));

                //ValueCodeableConcept
                if(v.getInterpretation() != null && !v.getInterpretation().equals("")) {
                    if(!"Risk factor".equals(v.getInterpretation())){
                        temp.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                                .setCode(variantInterpretationCodeMap.get(v.getInterpretation())).setDisplay(v.getInterpretation())));
                    }else{
                        temp.setValue(new CodeableConcept().setText(v.getInterpretation()));
                    }
                }

                //extensions
                if(v.getVariantInterpretation() != null && !v.getVariantInterpretation().equals("")) {
                    Extension ext = new Extension("http://namingsystem.org/fhir/StructureDefinition/interpretation-summary-text",
                            new StringType(v.getVariantInterpretation()));
                    temp.addExtension(ext);
                }

                //Component:associated-phenotype
                if(v.getDiseases() != null && v.getDiseases().size() > 0) {
                    for(Disease d : v.getDiseases()) {
                        if(d.getOntology() != null && !d.getOntology().equals("")
                                && d.getCode() != null && !d.getCode().equals("")
                                && d.getText() != null && !d.getText().equals("")) {
                            temp.addComponent(new Observation.ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                                    new Coding().setSystem("http://loinc.org").setCode("81259-4").setDisplay("Associated Phenotype")))
                                    .setValue(new CodeableConcept().addCoding(new Coding().setSystem(d.getOntology())
                                            .setCode(d.getCode()).setDisplay(d.getText()))));
                        }
                    }
                }

                obsInhDisPaths.put(v.getExternalId(), temp);
            }
        }

        return obsInhDisPaths;
    }
}
