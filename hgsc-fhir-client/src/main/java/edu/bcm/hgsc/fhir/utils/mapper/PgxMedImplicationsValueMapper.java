package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import edu.bcm.hgsc.fhir.models.PgxDatum;
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

    public Observation pgxResult_1001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxResult_1001 = new Observation();

        PgxDatum pgxData = getPgxDataByGeneSymbol(hgscReport, "CYP2C19");

        //Profile
        pgxResult_1001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/medication-metabolism");
        //extensions
        if(pgxData != null && pgxData.getInterpretation() != null && !pgxData.getInterpretation().equals("")) {
            Extension ext = new Extension("http://hl7.org/fhir/StructureDefinition/interpretation-summary-text",
                    new StringType(pgxData.getInterpretation()));
            pgxResult_1001.addExtension(ext);
        }

        //map; pgxData.summary???




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
            pgxResult_1001.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                    .setCode("LA9657-3").setDisplay(pgxData.getPhenotype())));
        }
        
        //Component:medication-assessed (clopidogrel)
        ObservationComponentComponent component_clopidogrel = new ObservationComponentComponent();
        component_clopidogrel.setCode(new CodeableConcept().addCoding(
        		new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
        		.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
        		.setCode("C0070166").setDisplay("clopidogrel")));
        Extension ext1 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact", 
        		new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
        		.setUrl("https://cpicpgx.org/guidelines/guideline-for-clopidogrel-and-cyp2c19/"));
        component_clopidogrel.addExtension(ext1);
        pgxResult_1001.addComponent(component_clopidogrel);
        //Component:medication-assessed (voriconazole)
        ObservationComponentComponent component_voriconazole = new ObservationComponentComponent();
        component_voriconazole.setCode(new CodeableConcept().addCoding(
        		new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
        		.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
        		.setCode("C0393080").setDisplay("voriconazole")));
        Extension ext2 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact", 
        		new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
        		.setUrl("https://cpicpgx.org/guidelines/guideline-for-voriconazole-and-cyp2c19/"));
        component_voriconazole.addExtension(ext2);
        pgxResult_1001.addComponent(component_voriconazole);
        //Component:medication-assessed (citalopram)
        ObservationComponentComponent component_citalopram = new ObservationComponentComponent();
        component_citalopram.setCode(new CodeableConcept().addCoding(
        		new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
        		.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
        		.setCode("C0008845").setDisplay("citalopram")));
        Extension ext3 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact", 
        		new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
        		.setUrl("https://cpicpgx.org/guidelines/guideline-for-selective-serotonin-reuptake-inhibitors-and-cyp2d6-and-cyp2c19/"));
        component_citalopram.addExtension(ext3);
        pgxResult_1001.addComponent(component_citalopram);
        //Component:medication-assessed (escitalopram)				
        ObservationComponentComponent component_escitalopram = new ObservationComponentComponent();
        component_escitalopram.setCode(new CodeableConcept().addCoding(
        		new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
        		.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
        		.setCode("C1099456").setDisplay("escitalopram")));
        component_escitalopram.addExtension(ext3);
        pgxResult_1001.addComponent(component_escitalopram);
        //Component:medication-assessed (amitriptyline)
        ObservationComponentComponent component_amitriptyline = new ObservationComponentComponent();
        component_amitriptyline.setCode(new CodeableConcept().addCoding(
        		new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
        		.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
        		.setCode("C0002600").setDisplay("amitriptyline")));
        Extension ext4 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact", 
        		new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
        		.setUrl("https://cpicpgx.org/guidelines/guideline-for-tricyclic-antidepressants-and-cyp2d6-and-cyp2c19/"));
        component_amitriptyline.addExtension(ext4);
        pgxResult_1001.addComponent(component_amitriptyline);

        return pgxResult_1001;
    }
    
    public Observation pgxResult_2001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxResult_2001 = new Observation();

        PgxDatum pgxData = getPgxDataByGeneSymbol(hgscReport, "DPYD");

        //Profile
        pgxResult_2001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/medication-metabolism");
        //extensions
        if(pgxData != null && pgxData.getInterpretation() != null && !pgxData.getInterpretation().equals("")) {
            Extension ext = new Extension("http://hl7.org/fhir/StructureDefinition/interpretation-summary-text",
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
            pgxResult_2001.setValue(new CodeableConcept().setText(pgxData.getPhenotype()));
        }
        
        //Component:medication-assessed (capecitabine)
        ObservationComponentComponent component_capecitabine = new ObservationComponentComponent();
        component_capecitabine.setCode(new CodeableConcept().addCoding(
        		new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
        		.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
        		.setCode("C0671970").setDisplay("capecitabine")));
        Extension ext1 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact", 
        		new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
        		.setUrl("https://cpicpgx.org/guidelines/guideline-for-fluoropyrimidines-and-dpyd/"));
        component_capecitabine.addExtension(ext1);
        pgxResult_2001.addComponent(component_capecitabine);
        //Component:medication-assessed (fluorouracil)
        ObservationComponentComponent component_fluorouracil = new ObservationComponentComponent();
        component_fluorouracil.setCode(new CodeableConcept().addCoding(
        		new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
        		.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
        		.setCode("C0016360").setDisplay("fluorouracil")));
        component_fluorouracil.addExtension(ext1);
        pgxResult_2001.addComponent(component_fluorouracil);
        //Component:medication-assessed (tegafur)
        ObservationComponentComponent component_tegafur = new ObservationComponentComponent();
        component_tegafur.setCode(new CodeableConcept().addCoding(
        		new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
        		.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
        		.setCode("C0016778").setDisplay("tegafur")));
        component_tegafur.addExtension(ext1);
        pgxResult_2001.addComponent(component_tegafur);
        
        return pgxResult_2001;
    }
    
    public Observation pgxResult_3001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxResult_3001 = new Observation();

        PgxDatum pgxData = getPgxDataByGeneSymbol(hgscReport, "IFNL3");

        //Profile
        pgxResult_3001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/medication-efficacy");
        //extensions
        if(pgxData != null && pgxData.getInterpretation() != null && !pgxData.getInterpretation().equals("")) {
            Extension ext = new Extension("http://hl7.org/fhir/StructureDefinition/interpretation-summary-text",
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
            pgxResult_3001.setValue(new CodeableConcept().setText(pgxData.getPhenotype()));
        }
        
        //Component:medication-assessed (peginterferon alfa-2a)				
        ObservationComponentComponent component_peginterferon_2a = new ObservationComponentComponent();
        component_peginterferon_2a.setCode(new CodeableConcept().addCoding(
        		new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
        		.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
        		.setCode("C0391001").setDisplay("peginterferon alfa-2a")));
        Extension ext1 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact", 
        		new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
        		.setUrl("https://cpicpgx.org/guidelines/guideline-for-peg-interferon-alpha-based-regimens-and-ifnl3/"));
        component_peginterferon_2a.addExtension(ext1);
        pgxResult_3001.addComponent(component_peginterferon_2a);
        //Component:medication-assessed (fluorouracil)
        ObservationComponentComponent component_fluorouracil = new ObservationComponentComponent();
        component_fluorouracil.setCode(new CodeableConcept().addCoding(
        		new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
        		.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
        		.setCode("C0796545").setDisplay("peginterferon alfa-2b")));
        component_fluorouracil.addExtension(ext1);
        pgxResult_3001.addComponent(component_fluorouracil);
        //Component:medication-assessed (Ribavirin)
        ObservationComponentComponent component_ribavirin = new ObservationComponentComponent();
        component_ribavirin.setCode(new CodeableConcept().addCoding(
        		new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
        		.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
        		.setCode("C0035525").setDisplay("Ribavirin")));
        component_ribavirin.addExtension(ext1);
        pgxResult_3001.addComponent(component_ribavirin);
        
        return pgxResult_3001;
    }
    
    public Observation pgxResult_4001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxResult_4001 = new Observation();

        PgxDatum pgxData = getPgxDataByGeneSymbol(hgscReport, "SLCO1B1");

        //Profile
        pgxResult_4001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/medication-transporter");
        //extensions
        if(pgxData != null && pgxData.getInterpretation() != null && !pgxData.getInterpretation().equals("")) {
            Extension ext = new Extension("http://hl7.org/fhir/StructureDefinition/interpretation-summary-text",
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
            pgxResult_4001.setValue(new CodeableConcept().setText(pgxData.getPhenotype()));
        }
        
        //Component:medication-assessed (simvastatin)
        ObservationComponentComponent component_simvastatin = new ObservationComponentComponent();
        component_simvastatin.setCode(new CodeableConcept().addCoding(
        		new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
        		.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
        		.setCode("C0074554").setDisplay("simvastatin")));
        Extension ext1 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact", 
        		new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
        		.setUrl("https://cpicpgx.org/guidelines/guideline-for-simvastatin-and-slco1b1/"));
        component_simvastatin.addExtension(ext1);
        pgxResult_4001.addComponent(component_simvastatin);
        
        return pgxResult_4001;
    }
    
    public Observation pgxResult_5001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxResult_5001 = new Observation();

        PgxDatum pgxData = getPgxDataByGeneSymbol(hgscReport, "TPMT");

        //Profile
        pgxResult_5001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/medication-metabolism");
        //extensions
        if(pgxData != null && pgxData.getInterpretation() != null && !pgxData.getInterpretation().equals("")) {
            Extension ext = new Extension("http://hl7.org/fhir/StructureDefinition/interpretation-summary-text",
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
            pgxResult_5001.setValue(new CodeableConcept().setText(pgxData.getPhenotype()));
        }
        
        //Component:medication-assessed (azathioprine)
        ObservationComponentComponent component_azathioprine = new ObservationComponentComponent();
        component_azathioprine.setCode(new CodeableConcept().addCoding(
        		new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
        		.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
        		.setCode("C0004482").setDisplay("azathioprine")));
        Extension ext1 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact", 
        		new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
        		.setUrl("https://cpicpgx.org/guidelines/guideline-for-thiopurines-and-tpmt/thioguanine"));
        component_azathioprine.addExtension(ext1);
        pgxResult_5001.addComponent(component_azathioprine);
        //Component:medication-assessed (mercaptopurine)
        ObservationComponentComponent component_mercaptopurine = new ObservationComponentComponent();
        component_mercaptopurine.setCode(new CodeableConcept().addCoding(
        		new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
        		.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
        		.setCode("C0000618").setDisplay("mercaptopurine")));
        component_mercaptopurine.addExtension(ext1);
        pgxResult_5001.addComponent(component_mercaptopurine);
        //Component:medication-assessed (thioguanine)
        ObservationComponentComponent component_thioguanine = new ObservationComponentComponent();
        component_thioguanine.setCode(new CodeableConcept().addCoding(
        		new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
        		.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
        		.setCode("C0039902").setDisplay("thioguanine")));
        component_thioguanine.addExtension(ext1);
        pgxResult_5001.addComponent(component_thioguanine);
        
        return pgxResult_5001;
    }
    
    public Observation pgxResult_6001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxResult_6001 = new Observation();

        PgxDatum pgxData1 = getPgxDataByGeneSymbol(hgscReport, "CYP2C9");
        PgxDatum pgxData2 = getPgxDataByGeneSymbol(hgscReport, "VKORC1");

        //Profile
        pgxResult_6001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/medication-metabolism");
        //extensions
        if(pgxData1 != null && pgxData1.getInterpretation() != null && !pgxData1.getInterpretation().equals("")
                && pgxData2 != null && pgxData2.getInterpretation() != null && !pgxData2.getInterpretation().equals("")) {
            Extension ext = new Extension("http://hl7.org/fhir/StructureDefinition/interpretation-summary-text",
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
            pgxResult_6001.setValue(new CodeableConcept().setText(pgxData1.getPhenotype()));
        }
        
        //Component:medication-assessed (warfarin)
        ObservationComponentComponent component_warfarin = new ObservationComponentComponent();
        component_warfarin.setCode(new CodeableConcept().addCoding(
        		new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
        		.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
        		.setCode("C0043031").setDisplay("warfarin")));
        Extension ext1 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact", 
        		new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
        		.setUrl("https://cpicpgx.org/guidelines/guideline-for-warfarin-and-cyp2c9-and-vkorc1/"));
        component_warfarin.addExtension(ext1);
        pgxResult_6001.addComponent(component_warfarin);
        
        return pgxResult_6001;
    }
}
