package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.r4.model.Observation.ObservationComponentComponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class PgxMedImplicationsValueMapper {

    public Observation pgxResult_1001_ValueMapping(HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxResult_1001 = new Observation();

        //Profile
        pgxResult_1001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/medication-metabolism");
        //extensions
        Extension ext = new Extension("http://hl7.org/fhir/StructureDefinition/interpretation-summary-text",
                new StringType("This individual is homozygous for a non-functional allele of the CYP2C19 gene. Based on the genotype result, this patient is predicted to have a CYP2C19 poor metabolizer phenotype. This genotype information can be used by patients and clinicians as part of the shared decision-making process for several drugs metabolized by CYP2C19 including clopidogrel, voriconazole, amitriptyline, citalopram and escitalopram. For clopidogrel, individuals with this diplotype are expected to have significantly reduced platelet inhibition, increased residual platelet aggregation and increased risk for adverse cardiovascular events in response to clopidogrel. Alternative antiplatelet therapy (if no contraindication) is recommended. Refer to current guidelines for dosage and recommendations at https://cpicpgx.org/guidelines/guideline- for-clopidogrel-and-cyp2c19/. For voriconazole, higher dose-adjusted trough concentrations of voriconazole are expected in individuals with this genotype and may increase the probability of adverse events. An alternative agent that is not dependent on CYP2C19 metabolism such as isavuconazole, liposomal amphotericin B, or posaconazole is recommended as primary therapy in lieu of voriconazole. A lower than standard dosage of voriconazole with careful therapeutic drug monitoring is another alternative. Refer to current guidelines for dosage and recommendations at https://cpicpgx.org/guidelines/guideline-for-voriconazole-and-cyp2c19/. For citalopram and escitalopram, a 50% reduction in starting dose is recommended with therapeutic drug monitoring to guide dose adjustment or select an alternate drug not predominantly metabolized by CYP2C19. Refer to current guidelines for dosage and recommendations at https://cpicpgx.org/guidelines/guideline-for-selective-serotonin-reuptake-inhibitors-and-cyp2d6-and-cyp2c19/. For amitriptyline, a 50% reduction in starting dose is recommended with therapeutic drug monitoring to guide dose adjustment. Refer to current guidelines for dosage and recommendations at https://cpicpgx.org/guidelines/guideline-for- tricyclic-antidepressants-and-cyp2d6-and-cyp2c19/. For citalopram, escitalopram and amitriptyline, if CYP2D6 genotyping is available, refer to the current guidelines for dosing recommendations."));
        pgxResult_1001.addExtension(ext);
        //Status
        if (mappingConfig.containsKey("HgscEmergeReport.reportStatus")) {
            pgxResult_1001.setStatus(Observation.ObservationStatus.fromCode(hgscEmergeReport.getReportStatus().toLowerCase()));
        }
        //Category
        pgxResult_1001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxResult_1001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("53040-2").setDisplay("Genetic variation's effect on drug metabolism")));
        //EffectiveDateTime
        if (mappingConfig.containsKey("HgscEmergeReport.sampleCollectedDate")) {
            pgxResult_1001.setEffective(new DateTimeType(sdf.parse(hgscEmergeReport.getSampleCollectedDate())));
        }
        //Issued
        if (mappingConfig.containsKey("HgscEmergeReport.reportDate")) {
            pgxResult_1001.setIssued(sdf.parse(hgscEmergeReport.getReportDate()));
        }
        //ValueCodeableConcept
        pgxResult_1001.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
        		.setCode("LA9657-3").setDisplay("Poor metabolizer")));
        
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
    
    public Observation pgxResult_2001_ValueMapping(HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxResult_2001 = new Observation();

        //Profile
        pgxResult_2001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/medication-metabolism");
        //extensions
        Extension ext = new Extension("http://hl7.org/fhir/StructureDefinition/interpretation-summary-text",
                new StringType("This individual is homozygous for the functional allele of the DPYD gene. This genotype information can be used by patients and clinicians as part of the shared decision-making process for fluoropyrimidines (capecitabine, fluorouracil, tegafur). Based on the genotype result, this patient is predicted to have a normal DPD activity phenotype. Individuals with this diplotype are expected to have 'normal' risk for fluoropyrimidine toxicity. Recommendations include the use of label recommended dosage and administration. Refer to current guidelines for dosage and recommendations at https://cpicpgx.org/guidelines/guideline-for-fluoropyrimidines-and-dpyd/."));
        pgxResult_2001.addExtension(ext);
        //Status
        if (mappingConfig.containsKey("HgscEmergeReport.reportStatus")) {
            pgxResult_2001.setStatus(Observation.ObservationStatus.fromCode(hgscEmergeReport.getReportStatus().toLowerCase()));
        }
        //Category
        pgxResult_2001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxResult_2001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("53040-2").setDisplay("Genetic variation's effect on drug metabolism")));
        //EffectiveDateTime
        if (mappingConfig.containsKey("HgscEmergeReport.sampleCollectedDate")) {
            pgxResult_2001.setEffective(new DateTimeType(sdf.parse(hgscEmergeReport.getSampleCollectedDate())));
        }
        //Issued
        if (mappingConfig.containsKey("HgscEmergeReport.reportDate")) {
            pgxResult_2001.setIssued(sdf.parse(hgscEmergeReport.getReportDate()));
        }
        //ValueCodeableConcept
        pgxResult_2001.setValue(new CodeableConcept().setText("Normal DPD activity and 'normal' risk for fluoropyrimidine toxicity"));
        
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
        Extension ext2 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact", 
        		new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
        		.setUrl("https://cpicpgx.org/guidelines/guideline-for-fluoropyrimidines-and-dpyd/"));
        component_fluorouracil.addExtension(ext2);
        pgxResult_2001.addComponent(component_fluorouracil);
        //Component:medication-assessed (tegafur)
        ObservationComponentComponent component_tegafur = new ObservationComponentComponent();
        component_tegafur.setCode(new CodeableConcept().addCoding(
        		new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
        		.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
        		.setCode("C0016778").setDisplay("tegafur")));
        Extension ext3 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact", 
        		new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
        		.setUrl("https://cpicpgx.org/guidelines/guideline-for-fluoropyrimidines-and-dpyd/"));
        component_tegafur.addExtension(ext3);
        pgxResult_2001.addComponent(component_tegafur);
        
        return pgxResult_2001;
    }
    
    public Observation pgxResult_3001_ValueMapping(HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxResult_3001 = new Observation();

        //Profile
        pgxResult_3001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/medication-efficacy");
        //extensions
        Extension ext = new Extension("http://hl7.org/fhir/StructureDefinition/interpretation-summary-text", 
        		new StringType("This individual is homozygous for the rs12979860 C/C allele in the IFNL3 gene. This variant is the strongest baseline predictor of response to peginterferon alfa and ribavirin therapy in previously untreated patients and can be used by patients and clinicians as part of the shared decision-making process for initiating treatment for hepatitis C virus infection. Based on the genotype result, this patient is predicted to have an increased likelihood of response (higher sustained virologic response rate) to peginterferon alfa and ribavirin therapy as compared with patients with unfavorable response genotype. Refer to current guidelines for dosage and recommendations at https://cpicpgx.org/guidelines/guideline-for-peg-interferon-alpha-based-regimens-and-ifnl3/"));
        pgxResult_3001.addExtension(ext);
        //Status
        if (mappingConfig.containsKey("HgscEmergeReport.reportStatus")) {
            pgxResult_3001.setStatus(Observation.ObservationStatus.fromCode(hgscEmergeReport.getReportStatus().toLowerCase()));
        }
        //Category
        pgxResult_3001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxResult_3001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("51961-1").setDisplay("Genetic variation's effect on drug efficacy")));
        //EffectiveDateTime
        if (mappingConfig.containsKey("HgscEmergeReport.sampleCollectedDate")) {
            pgxResult_3001.setEffective(new DateTimeType(sdf.parse(hgscEmergeReport.getSampleCollectedDate())));
        }
        //Issued
        if (mappingConfig.containsKey("HgscEmergeReport.reportDate")) {
            pgxResult_3001.setIssued(sdf.parse(hgscEmergeReport.getReportDate()));
        }
        //ValueCodeableConcept
        pgxResult_3001.setValue(new CodeableConcept().setText("Favorable response genotype"));
        
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
        Extension ext2 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact", 
        		new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
        		.setUrl("https://cpicpgx.org/guidelines/guideline-for-peg-interferon-alpha-based-regimens-and-ifnl3/"));
        component_fluorouracil.addExtension(ext2);
        pgxResult_3001.addComponent(component_fluorouracil);
        //Component:medication-assessed (Ribavirin)
        ObservationComponentComponent component_ribavirin = new ObservationComponentComponent();
        component_ribavirin.setCode(new CodeableConcept().addCoding(
        		new Coding().setSystem("http://loinc.org").setCode("51963-7").setDisplay("Medication assessed")))
        		.setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://ncimeta.nci.nih.gov")
        		.setCode("C0035525").setDisplay("Ribavirin")));
        Extension ext3 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact", 
        		new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.JUSTIFICATION)
        		.setUrl("https://cpicpgx.org/guidelines/guideline-for-peg-interferon-alpha-based-regimens-and-ifnl3/"));
        component_ribavirin.addExtension(ext3);
        pgxResult_3001.addComponent(component_ribavirin);
        
        return pgxResult_3001;
    }
    
    public Observation pgxResult_4001_ValueMapping(HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxResult_4001 = new Observation();

        //Profile
        pgxResult_4001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/medication-transporter");
        //extensions
        Extension ext = new Extension("http://hl7.org/fhir/StructureDefinition/interpretation-summary-text", 
        		new StringType("This individual is homozygous for the rs4149056 T/T allele in the SLCO1B1 gene. This genotype information can be used by patients and clinicians as part of the shared decision-making process for simvastatin and other drugs affected by SLCO1B1. Based on the genotype result, this patient is predicted to have normal SLCO1B1 function. This means that there is no reason to adjust the dose of most medications that are affected by SLCO1B1 (including simvastatin) on the basis of SLCO1B1 genetic status. Refer to current guidelines for dosage and recommendations at https://cpicpgx.org/guidelines/guideline-for-simvastatin-and-slco1b1/."));
        pgxResult_4001.addExtension(ext);
        //Status
        if (mappingConfig.containsKey("HgscEmergeReport.reportStatus")) {
            pgxResult_4001.setStatus(Observation.ObservationStatus.fromCode(hgscEmergeReport.getReportStatus().toLowerCase()));
        }
        //Category
        pgxResult_4001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxResult_4001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("TBD???").setDisplay("Genetic variation's effect on drug transporter function")));
        //EffectiveDateTime
        if (mappingConfig.containsKey("HgscEmergeReport.sampleCollectedDate")) {
            pgxResult_4001.setEffective(new DateTimeType(sdf.parse(hgscEmergeReport.getSampleCollectedDate())));
        }
        //Issued
        if (mappingConfig.containsKey("HgscEmergeReport.reportDate")) {
            pgxResult_4001.setIssued(sdf.parse(hgscEmergeReport.getReportDate()));
        }
        //ValueCodeableConcept
        pgxResult_4001.setValue(new CodeableConcept().setText("Normal function, Normal simvastatin induced myopathy risk"));
        
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
    
    public Observation pgxResult_5001_ValueMapping(HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxResult_5001 = new Observation();

        //Profile
        pgxResult_5001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/medication-transporter");
        //extensions
        Extension ext = new Extension("http://hl7.org/fhir/StructureDefinition/interpretation-summary-text", 
        		new StringType("This individual is homozygous for the normal high activity allele of the TPMT gene. Decreased TPMT gene activity is associated with toxicity and myelosuppression in response to thiopurines, and this genotype information can be used by patients and clinicians as part of the shared decision-making process for initiating treatment. Based on the genotype result, this patient is predicted to have normal TPMT function. Individuals with this diplotype are expected to have a normal response to mercaptopurine, azathioprine and thioguanine. A normal dose of thiopurine and adjustment following the disease-specific guidelines is recommended. Refer to current guidelines for dosage and recommendations for each specific thiopurine drug at https://cpicpgx.org/guidelines/guideline-for-thiopurines-and-tpmt/."));
        pgxResult_5001.addExtension(ext);
        //Status
        if (mappingConfig.containsKey("HgscEmergeReport.reportStatus")) {
            pgxResult_5001.setStatus(Observation.ObservationStatus.fromCode(hgscEmergeReport.getReportStatus().toLowerCase()));
        }
        //Category
        pgxResult_5001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxResult_5001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("53040-2").setDisplay("Genetic variation's effect on drug metabolism")));
        //EffectiveDateTime
        if (mappingConfig.containsKey("HgscEmergeReport.sampleCollectedDate")) {
            pgxResult_5001.setEffective(new DateTimeType(sdf.parse(hgscEmergeReport.getSampleCollectedDate())));
        }
        //Issued
        if (mappingConfig.containsKey("HgscEmergeReport.reportDate")) {
            pgxResult_5001.setIssued(sdf.parse(hgscEmergeReport.getReportDate()));
        }
        //ValueCodeableConcept
        pgxResult_5001.setValue(new CodeableConcept().setText("High activity"));
        
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
    
    public Observation pgxResult_6001_ValueMapping(HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxResult_6001 = new Observation();

        //Profile
        pgxResult_6001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/medication-transporter");
        //extensions
        Extension ext = new Extension("http://hl7.org/fhir/StructureDefinition/interpretation-summary-text", 
        		new StringType("This individual is homozygous for the normal allele for the CYP2C9 gene. Based on the genotype result, this patient is predicted to have normal CYP2C9 function.This individual is also heterozygous for the variant allele for the VKORC1 gene. Expression level of the VKORC1 gene is associated with warfarin sensitivity. Based on the genotype result, this patient is predicted to have medium sensitivity to warfarin."));
        pgxResult_6001.addExtension(ext);
        //Status
        if (mappingConfig.containsKey("HgscEmergeReport.reportStatus")) {
            pgxResult_6001.setStatus(Observation.ObservationStatus.fromCode(hgscEmergeReport.getReportStatus().toLowerCase()));
        }
        //Category
        pgxResult_6001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxResult_6001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("53040-2").setDisplay("Genetic variation's effect on drug metabolism")));
        //EffectiveDateTime
        if (mappingConfig.containsKey("HgscEmergeReport.sampleCollectedDate")) {
            pgxResult_6001.setEffective(new DateTimeType(sdf.parse(hgscEmergeReport.getSampleCollectedDate())));
        }
        //Issued
        if (mappingConfig.containsKey("HgscEmergeReport.reportDate")) {
            pgxResult_6001.setIssued(sdf.parse(hgscEmergeReport.getReportDate()));
        }
        //ValueCodeableConcept
        pgxResult_6001.setValue(new CodeableConcept().setText("Extensive metabolizer"));
        
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
