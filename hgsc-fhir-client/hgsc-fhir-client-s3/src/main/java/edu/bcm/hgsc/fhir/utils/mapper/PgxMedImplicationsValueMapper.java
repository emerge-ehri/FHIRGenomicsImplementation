package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import edu.bcm.hgsc.fhir.models.PgxDatum;
import edu.bcm.hgsc.fhir.models.PgxDrugRec;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.r4.model.Observation.ObservationComponentComponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class PgxMedImplicationsValueMapper {

    public PgxDatum getPgxDataByGeneSymbol(HgscReport hgscReport, String geneSymbol) {

        if(hgscReport.getPgxData() != null && hgscReport.getPgxData().size() > 0) {
            for(PgxDatum pgxDatum : hgscReport.getPgxData()) {
                if(pgxDatum.getGeneSymbol().equals(geneSymbol)) {
                    return pgxDatum;
                }
            }
        }

        return null;
    }

    public Observation pgxResult_1001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf, HashMap<String, String> pgxDataPhenotypeCodeMap) throws ParseException {

        Observation pgxResult_1001 = new Observation();

        pgxResult_1001.setLanguage(hgscReport.getLanguage());

        PgxDatum pgxData = getPgxDataByGeneSymbol(hgscReport, "CYP2C19");

        //Profile
        pgxResult_1001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/medication-metabolism");
        //extensions
        if(pgxData != null && pgxData.getInterpretation() != null && !pgxData.getInterpretation().equals("")) {
            Extension ext = new Extension("http://namingsystem.org/fhir/StructureDefinition/interpretation-summary-text",
                    new StringType(pgxData.getInterpretation()));
            pgxResult_1001.addExtension(ext);
        }

        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if (hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                pgxResult_1001.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Category
        pgxResult_1001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxResult_1001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("53040-2").setDisplay("Genetic variation's effect on drug metabolism")));
        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                pgxResult_1001.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }
        //ValueCodeableConcept
        if(pgxData != null && pgxData.getPhenotype() != null && !pgxData.getPhenotype().equals("")) {
            if(pgxDataPhenotypeCodeMap.get(pgxData.getPhenotype()) != null){
                pgxResult_1001.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                        .setCode(pgxDataPhenotypeCodeMap.get(pgxData.getPhenotype())).setDisplay(pgxData.getPhenotype())));
            }else{
                pgxResult_1001.setValue(new CodeableConcept().setText(pgxData.getPhenotype()));
            }
        }

        if(pgxData.getPgxDrugRecommendation() != null && pgxData.getPgxDrugRecommendation().size() > 0) {
            for(PgxDrugRec pgxDrugRec : pgxData.getPgxDrugRecommendation()) {
                //Component:medication-assessed (clopidogrel)
                //Component:medication-assessed (voriconazole)
                //Component:medication-assessed (citalopram)
                //Component:medication-assessed (escitalopram)
                //Component:medication-assessed (amitriptyline)
                ObservationComponentComponent component = new ObservationComponentComponent();
                component.setCode(new CodeableConcept().addCoding(
                        new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
                        .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
                                .setCode(pgxDrugRec.getId()).setDisplay(pgxDrugRec.getDrug())));
                Extension ext = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact",
                        new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
                                .setUrl(pgxDrugRec.getRecommendation()));
                component.addExtension(ext);
                pgxResult_1001.addComponent(component);
            }
        }

        return pgxResult_1001;
    }
    
    public Observation pgxResult_2001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf, HashMap<String, String> pgxDataPhenotypeCodeMap) throws ParseException {

        Observation pgxResult_2001 = new Observation();

        pgxResult_2001.setLanguage(hgscReport.getLanguage());

        PgxDatum pgxData = getPgxDataByGeneSymbol(hgscReport, "DPYD");

        //Profile
        pgxResult_2001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/medication-metabolism");
        //extensions
        if(pgxData != null && pgxData.getInterpretation() != null && !pgxData.getInterpretation().equals("")) {
            Extension ext = new Extension("http://namingsystem.org/fhir/StructureDefinition/interpretation-summary-text",
                    new StringType(pgxData.getInterpretation()));
            pgxResult_2001.addExtension(ext);
        }
        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if (hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                pgxResult_2001.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Category
        pgxResult_2001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));

        //Code
        pgxResult_2001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("53040-2").setDisplay("Genetic variation's effect on drug metabolism")));
        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                pgxResult_2001.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }

        //ValueCodeableConcept
        if(pgxData != null && pgxData.getPhenotype() != null && !pgxData.getPhenotype().equals("")) {
            if(pgxDataPhenotypeCodeMap.get(pgxData.getPhenotype()) != null){
                pgxResult_2001.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                        .setCode(pgxDataPhenotypeCodeMap.get(pgxData.getPhenotype())).setDisplay(pgxData.getPhenotype())));
            }else{
                pgxResult_2001.setValue(new CodeableConcept().setText(pgxData.getPhenotype()));
            }
        }

        if(pgxData.getPgxDrugRecommendation() != null && pgxData.getPgxDrugRecommendation().size() > 0) {
            for(PgxDrugRec pgxDrugRec : pgxData.getPgxDrugRecommendation()) {
                //Component:medication-assessed (capecitabine)
                //Component:medication-assessed (fluorouracil)
                //Component:medication-assessed (tegafur)
                ObservationComponentComponent component = new ObservationComponentComponent();
                component.setCode(new CodeableConcept().addCoding(
                        new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
                        .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
                                .setCode(pgxDrugRec.getId()).setDisplay(pgxDrugRec.getDrug())));
                Extension ext = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact",
                        new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
                                .setUrl(pgxDrugRec.getRecommendation()));
                component.addExtension(ext);
                pgxResult_2001.addComponent(component);
            }
        }
        
        return pgxResult_2001;
    }
    
    public Observation pgxResult_3001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf, HashMap<String, String> pgxDataPhenotypeCodeMap) throws ParseException {

        Observation pgxResult_3001 = new Observation();

        pgxResult_3001.setLanguage(hgscReport.getLanguage());

        PgxDatum pgxData = getPgxDataByGeneSymbol(hgscReport, "IFNL3");

        //Profile
        pgxResult_3001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/medication-efficacy");
        //extensions
        if(pgxData != null && pgxData.getInterpretation() != null && !pgxData.getInterpretation().equals("")) {
            Extension ext = new Extension("http://namingsystem.org/fhir/StructureDefinition/interpretation-summary-text",
                    new StringType(pgxData.getInterpretation()));
            pgxResult_3001.addExtension(ext);
        }
        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if (hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                pgxResult_3001.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Category
        pgxResult_3001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxResult_3001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("51961-1").setDisplay("Genetic variation's effect on drug efficacy")));
        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                pgxResult_3001.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }

        //ValueCodeableConcept
        if(pgxData != null && pgxData.getPhenotype() != null && !pgxData.getPhenotype().equals("")) {
            if(pgxDataPhenotypeCodeMap.get(pgxData.getPhenotype()) != null){
                pgxResult_3001.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                        .setCode(pgxDataPhenotypeCodeMap.get(pgxData.getPhenotype())).setDisplay(pgxData.getPhenotype())));
            }else{
                pgxResult_3001.setValue(new CodeableConcept().setText(pgxData.getPhenotype()));
            }
        }

        if(pgxData.getPgxDrugRecommendation() != null && pgxData.getPgxDrugRecommendation().size() > 0) {
            for(PgxDrugRec pgxDrugRec : pgxData.getPgxDrugRecommendation()) {
                //Component:medication-assessed (peginterferon alfa-2a)
                //Component:medication-assessed (fluorouracil)
                //Component:medication-assessed (Ribavirin)
                ObservationComponentComponent component = new ObservationComponentComponent();
                component.setCode(new CodeableConcept().addCoding(
                        new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
                        .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
                                .setCode(pgxDrugRec.getId()).setDisplay(pgxDrugRec.getDrug())));
                Extension ext = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact",
                        new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
                                .setUrl(pgxDrugRec.getRecommendation()));
                component.addExtension(ext);
                pgxResult_3001.addComponent(component);
            }
        }
        
        return pgxResult_3001;
    }
    
    public Observation pgxResult_4001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf, HashMap<String, String> pgxDataPhenotypeCodeMap) throws ParseException {

        Observation pgxResult_4001 = new Observation();

        pgxResult_4001.setLanguage(hgscReport.getLanguage());

        PgxDatum pgxData = getPgxDataByGeneSymbol(hgscReport, "SLCO1B1");

        //Profile
        pgxResult_4001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/medication-transporter");
        //extensions
        if(pgxData != null && pgxData.getInterpretation() != null && !pgxData.getInterpretation().equals("")) {
            Extension ext = new Extension("http://namingsystem.org/fhir/StructureDefinition/interpretation-summary-text",
                    new StringType(pgxData.getInterpretation()));
            pgxResult_4001.addExtension(ext);
        }
        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if (hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                pgxResult_4001.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Category
        pgxResult_4001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxResult_4001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://hl7.org/fhir/uv/genomics-reporting/CodeSystem/tbd-codes")
                .setCode("effect-transporter-function").setDisplay("effect-transporter-function")));
        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                pgxResult_4001.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }

        //ValueCodeableConcept
        if(pgxData != null && pgxData.getPhenotype() != null && !pgxData.getPhenotype().equals("")) {
            if(pgxDataPhenotypeCodeMap.get(pgxData.getPhenotype()) != null){
                pgxResult_4001.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                        .setCode(pgxDataPhenotypeCodeMap.get(pgxData.getPhenotype())).setDisplay(pgxData.getPhenotype())));
            }else{
                pgxResult_4001.setValue(new CodeableConcept().setText(pgxData.getPhenotype()));
            }
        }

        if(pgxData.getPgxDrugRecommendation() != null && pgxData.getPgxDrugRecommendation().size() > 0) {
            for(PgxDrugRec pgxDrugRec : pgxData.getPgxDrugRecommendation()) {
                //Component:medication-assessed (simvastatin)
                ObservationComponentComponent component = new ObservationComponentComponent();
                component.setCode(new CodeableConcept().addCoding(
                        new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
                        .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
                                .setCode(pgxDrugRec.getId()).setDisplay(pgxDrugRec.getDrug())));
                Extension ext = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact",
                        new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
                                .setUrl(pgxDrugRec.getRecommendation()));
                component.addExtension(ext);
                pgxResult_4001.addComponent(component);
            }
        }
        
        return pgxResult_4001;
    }
    
    public Observation pgxResult_5001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf, HashMap<String, String> pgxDataPhenotypeCodeMap) throws ParseException {

        Observation pgxResult_5001 = new Observation();

        pgxResult_5001.setLanguage(hgscReport.getLanguage());

        PgxDatum pgxData = getPgxDataByGeneSymbol(hgscReport, "TPMT");

        //Profile
        pgxResult_5001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/medication-metabolism");
        //extensions
        if(pgxData != null && pgxData.getInterpretation() != null && !pgxData.getInterpretation().equals("")) {
            Extension ext = new Extension("http://namingsystem.org/fhir/StructureDefinition/interpretation-summary-text",
                    new StringType(pgxData.getInterpretation()));
            pgxResult_5001.addExtension(ext);
        }
        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if (hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                pgxResult_5001.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Category
        pgxResult_5001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));

        //Code
        pgxResult_5001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("53040-2").setDisplay("Genetic variation's effect on drug metabolism")));
        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                pgxResult_5001.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }

        //ValueCodeableConcept
        if(pgxData != null && pgxData.getPhenotype() != null && !pgxData.getPhenotype().equals("")) {
            if(pgxDataPhenotypeCodeMap.get(pgxData.getPhenotype()) != null){
                pgxResult_5001.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                        .setCode(pgxDataPhenotypeCodeMap.get(pgxData.getPhenotype())).setDisplay(pgxData.getPhenotype())));
            }else{
                pgxResult_5001.setValue(new CodeableConcept().setText(pgxData.getPhenotype()));
            }
        }

        if(pgxData.getPgxDrugRecommendation() != null && pgxData.getPgxDrugRecommendation().size() > 0) {
            for(PgxDrugRec pgxDrugRec : pgxData.getPgxDrugRecommendation()) {
                //Component:medication-assessed (azathioprine)
                //Component:medication-assessed (mercaptopurine)
                //Component:medication-assessed (thioguanine)
                ObservationComponentComponent component = new ObservationComponentComponent();
                component.setCode(new CodeableConcept().addCoding(
                        new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
                        .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
                                .setCode(pgxDrugRec.getId()).setDisplay(pgxDrugRec.getDrug())));
                Extension ext = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact",
                        new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
                                .setUrl(pgxDrugRec.getRecommendation()));
                component.addExtension(ext);
                pgxResult_5001.addComponent(component);
            }
        }
        
        return pgxResult_5001;
    }
    
    public Observation pgxResult_6001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf, HashMap<String, String> pgxDataPhenotypeCodeMap) throws ParseException {

        Observation pgxResult_6001 = new Observation();

        pgxResult_6001.setLanguage(hgscReport.getLanguage());

        PgxDatum pgxData1 = getPgxDataByGeneSymbol(hgscReport, "CYP2C9");
        PgxDatum pgxData2 = getPgxDataByGeneSymbol(hgscReport, "VKORC1");

        //Profile
        pgxResult_6001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/medication-metabolism");
        //extensions
        if(pgxData1 != null && pgxData1.getInterpretation() != null && !pgxData1.getInterpretation().equals("")
                && pgxData2 != null && pgxData2.getInterpretation() != null && !pgxData2.getInterpretation().equals("")) {
            Extension ext = new Extension("http://namingsystem.org/fhir/StructureDefinition/interpretation-summary-text",
                    new StringType(pgxData1.getInterpretation() + pgxData2.getInterpretation()));
            pgxResult_6001.addExtension(ext);
        }
        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if (hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                pgxResult_6001.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Category
        pgxResult_6001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxResult_6001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("53040-2").setDisplay("Genetic variation's effect on drug metabolism")));
        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                pgxResult_6001.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }
        //ValueCodeableConcept
        if(pgxData1 != null && pgxData1.getPhenotype() != null && !pgxData1.getPhenotype().equals("")) {
            if(pgxDataPhenotypeCodeMap.get(pgxData1.getPhenotype()) != null){
                pgxResult_6001.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                        .setCode(pgxDataPhenotypeCodeMap.get(pgxData1.getPhenotype())).setDisplay(pgxData1.getPhenotype())));
            }else{
                pgxResult_6001.setValue(new CodeableConcept().setText(pgxData1.getPhenotype()));
            }
        }

        if(pgxData1.getPgxDrugRecommendation() != null && pgxData1.getPgxDrugRecommendation().size() > 0) {
            for(PgxDrugRec pgxDrugRec : pgxData1.getPgxDrugRecommendation()) {
                //Component:medication-assessed (warfarin)
                ObservationComponentComponent component = new ObservationComponentComponent();
                component.setCode(new CodeableConcept().addCoding(
                        new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
                        .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
                                .setCode(pgxDrugRec.getId()).setDisplay(pgxDrugRec.getDrug())));
                Extension ext = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact",
                        new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
                                .setUrl(pgxDrugRec.getRecommendation()));
                component.addExtension(ext);
                pgxResult_6001.addComponent(component);
            }
        }

        return pgxResult_6001;
    }
}
