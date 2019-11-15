package edu.bcm.hgsc.fhir.models;

import com.fasterxml.jackson.annotation.*;

import java.util.*;

/**
 * Created by sl9 on 10/6/17.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "reportDate",
        "byName",
        "onDate",
        "indicationForTesting",
        "genomicSource",
        "geneCoverage",
        "overallInterpretation",
        "reportIdentifier",
        "reportStatus",
        "caseType",
        "labStatus",
        "patientSampleID",
        "sampleCollectionSource",
        "localID",
        "vipFile",
        "clinicalSite",
        "testName",
        "addendums",
        "age",
        "sampleType",
        "patientID",
        "sex",
        "race",
        "ethnicity",
        "diseaseStatus",
        "deidentified",
        "specimenType",
        "diseases",
        "sampleCollectedDate",
        "sampleReceivedDate",
        "dateOfBirth",
        "isPreliminary",
        "accessionNumber",
        "variants",
        "cnvVariants",
        "sangerSiteNote",
        "pgxDatum",
        "orderingPhysicianName",
        "patientFirstName",
        "patientMiddleInitial",
        "patientLastName",
        "reportComment",
        "methodology",
        "interrogatedButNotFound",
        "notInterpreted",
        "background",
        "clinicalCorelation",
        "pgxDescription",
        "cnvsFailed"
        
})
public class HgscReport {

    // Requried Fields
    public static final List<String> requiredFields = Collections.unmodifiableList(
        new ArrayList<String>() {{
            add("reportDate");
            add("onDate");
            add("vipFile");
            add("geneCoverage");
            add("reportIdentifier");
            add("indicationForTesting");
            add("genomicSource");
            add("overallInterpretation");
            add("reportStatus");
            add("caseType");
            add("labStatus");
            add("patientSampleID");
            add("clinicalSite");
            add("patientID");
            add("accessionNumber");
            add("dateOfBirth");
            add("race");
            add("ethnicity");
            add("patientFirstName");
            add("patientLastName");
            add("sex");
            add("diseaseStatus");
            add("deidentified");
            add("testName");
            add("sampleCollectionSource");
            add("specimenType");
            add("diseases");
            add("sampleCollectedDate");
            add("sampleReceivedDate");
            add("methodology");
        }});

    // Fields with boolean value
    public static final List<String> booleanFields = Collections.unmodifiableList(
        new ArrayList<String>() {{
            add("deidentified");
            add("isPreliminary");
            add("cnvsFailed");
        }});

    // Fields with date value
    public static final List<String> dateFields = Collections.unmodifiableList(
            new ArrayList<String>() {{
                add("reportDate");
                add("onDate");
                add("sampleCollectedDate");
                add("sampleReceivedDate");
                add("dateOfBirth");
            }});

    // Value for caseType (JSON)
    private final static String CASE_TYPE_FIELD_NAME = "caseType";
    private final static String CASE_TYPE_CLINICAL = "CLINICAL";
    private final static String CASE_TYPE_CLINICAL_TRIAL = "CLINICAL_TRIAL";
    private final static String CASE_TYPE_RESEARCH = "RESEARCH";
    private final static String CASE_TYPE_PROFICIENCY = "PROFICIENCY";
    private final static String CASE_TYPE_TEST = "CLINICAL";

    // Value for labStatus (JSON)
    private final static String LAB_STATUS_FIELD_NAME = "labStatus";
    private final static String LAB_STATUS_UNKNOWN = "UNKNOWN";
    private final static String LAB_STATUS_IN_PROGRESS = "IN_PROGRESS";
    private final static String LAB_STATUS_COMPLETE = "COMPLETE";
    private final static String LAB_STATUS_FAILURE = "FAILURE";
    private final static String LAB_STATUS_CANCELED = "CANCELED";
    private final static String LAB_STATUS_CANCELED_INSUFFICIENT_SPECIMEN = "CANCELED_INSUFFICIENT_SPECIMEN";
    private final static String LAB_STATUS_CANCELED_INSUFFICIENT_DNA = "CANCELED_INSUFFICIENT_DNA";

    // Value for reportsStatus (JSON)
    private final static String STATUS_FIELD_NAME = "reportStatus";
    private final static String STATUS_PROVISIONAL = "PROVISIONAL";
    private final static String STATUS_DRAFT = "DRAFT";
    private final static String STATUS_FINAL_REVIEW = "FINAL_REVIEW";
    private final static String STATUS_FINAL = "FINAL";
    private final static String STATUS_AMENDMENT = "AMENDMENT";
    private final static String STATUS_AMENDMENT_FINAL_REVIEW = "AMENDMENT_FINAL_REVIEW";
    private final static String STATUS_AMENDMENT_FINAL = "AMENDMENT_FINAL";

    // Value for overalL interpretation (JSON)
    private static final String OVERALL_INTERP_FIELD_NAME = "overallInterpretation";
    private static final String OVERALL_INTERP_POSITIVE = "Positive";
    private static final String OVERALL_INTERP_INCONCLUSIVE = "Inconclusive";
    private static final String OVERALL_INTERP_NEGATIVE = "Negative";
    private static final String OVERALL_INTERP_FAILURE = "Failure";
    private static final String OVERALL_INTERP_CARRIER = "Carrier";

    // Value for genomic source (JSON)
    private static final String GENOMIC_SOURCE_FIELD_NAME = "genomicSource";
    private static final String GENOMIC_SOURCE_GERMLINE = "Germline";
    private static final String GENOMIC_SOURCE_SOMATIC = "Somatic";
    private static final String GENOMIC_SOURCE_PRENATAL = "Prenatal";

    // Value for race and ethnicity (JSON)
    private static final String RACE_FIELD_NAME = "race";
    private static final String ETHNICITY_FIELD_NAME = "ethnicity";
    private static final String RACE_ETHNICITY_WHITE = "White";
    private static final String RACE_ETHNICITY_AM_IND_ALA_NA = "American Indian or Alaska Native";
    private static final String RACE_ETHNICITY_ASIAN = "Asian";
    private static final String RACE_ETHNICITY_BLK_AFR_AM = "Black or African American";
    private static final String RACE_ETHNICITY_NA_HA_PAC_IS = "Native Hawaiian or Other Pacific Islander";
    private static final String RACE_ETHNICITY_HIS_LAT = "Hispanic or Latino";
    private static final String RACE_ETHNICITY_ASH_JEW = "Ashkenazi Jewish";
    private static final String RACE_ETHNICITY_MIXED = "Mixed";
    private static final String RACE_ETHNICITY_UNSPECIFIED = "Unspecified";
    private static final String RACE_ETHNICITY_NOT_HIS_LAT = "Not Hispanic or Latino";
    private static final String RACE_ETHNICITY_UNKNOWN = "Unknown";
    private static final String RACE_ETHNICITY_OTHER = "Other";

    // Value for diseases (JSON)
    private static final String DISEASES_FIELD_NAME = "diseases";
    private static final String FAMILIAL_THORACIC_AORTIC_ANEURYSM_6_MIM_611788 = "Familial thoracic aortic aneurysm 6 [MIM 611788]";
    private static final String FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_11_MIM_612098 = "Familial hypertrophic cardiomyopathy 11 [MIM 612098]";
    private static final String LONG_QT_SYNDROME_4_MIM_600919 = "Long QT syndrome 4 [MIM 600919]";
    private static final String FAMILIAL_ADENOMATOUS_POLYPOSIS_1_MIM_175100 = "Familial adenomatous polyposis 1 [MIM 175100]";
    private static final String FAMILIAL_HYPERCHOLESTEROLEMIA_MIM_144010 = "Familial hypercholesterolemia [MIM 144010]";
    private static final String SUCEPTIBILITY_TO_BREAST_CANCER_MIM_114480 = "Suceptibility to Breast cancer [MIM 114480]";
    private static final String ATAXIA_TELANGIECTASIA_MIM_208900 = "Ataxia-telangiectasia [MIM 208900]";
    private static final String ALTERNATING_HEMIPLEGIA_OF_CHILDHOOD_MIM_104290 = "Alternating hemiplegia of childhood [MIM 104290]";
    private static final String MIGRAINE_FAMILIAL_HEMIPLEGIC_2_MIM_602481 = "Migraine, familial hemiplegic, 2 [MIM 602481]";
    private static final String PULMONARY_HYPERTENSION_FAMILIAL_PRIMARY_1_MIM_178600 = "Pulmonary hypertension, familial primary, 1  [MIM 178600]";
    private static final String PULMONARY_VENOOCCLUSIVE_DISEASE_1_MIM_265450 = "Pulmonary venoocclusive disease 1 [MIM 265450]";
    private static final String FAMILIAL_BREAST_OVARIAN_CANCER_1_MIM_604370 = "Familial breast-ovarian cancer 1 [MIM 604370]";
    private static final String FAMILIAL_BREAST_OVARIAN_CANCER_2_MIM_612555 = "Familial breast-ovarian cancer 2 [MIM 612555]";
    private static final String EPISODIC_ATAXIA_2_MIM_108500 = "Episodic ataxia 2 [MIM 108500]";
    private static final String BRUGADA_SYNDROME_3_MIM_611875 = "Brugada syndrome 3 [MIM 611875]";
    private static final String THIMOTHY_SYNDROME_MIM_601005 = "Thimothy syndrome [MIM 601005]";
    private static final String MALIGNANT_HYPERTHERMIA_SUSCEPTIBILITY_5_MIM_601887 = "Malignant hyperthermia susceptibility 5 [MIM 601887]";
    private static final String ATYPICAL_HEMOLYTIC_UREMIC_SYNDROME_MIM_235400 = "Atypical hemolytic-uremic syndrome [MIM 235400]";
    private static final String DENSE_DEPOSIT_DISEASE_MEMBRANOPROLIFERATIVE_GLOMERULONEPHRITIS_TYPE_II_MIM_609814 = "Dense deposit disease/membranoproliferative glomerulonephritis type II [MIM 609814]";
    private static final String CYSTIC_FIBROSIS_MIM_219700 = "Cystic fibrosis [MIM 219700]";
    private static final String CONGENITAL_BILATERAL_ABSENCE_OF_THE_VAS_DEFERENS_MIM_277180 = "Congenital bilateral absence of the vas deferens [MIM 277180]";
    private static final String SUSCEPTIBILITY_TO_BREAST_CANCER_MIM_114480 = "Susceptibility to breast cancer [MIM 114480]";
    private static final String SUSCEPTIBILITY_TO_PROSTATE_CANCER_MIM_176807 = "Susceptibility to prostate cancer [MIM 176807]";
    private static final String EHLERS_DANLOS_SYNDROME_4_MIM_130050 = "Ehlers-Danlos syndrome 4 [MIM 130050]";
    private static final String EHLERS_DANLOS_SYNDROME_CLASSIC_TYPE_I_MIM_130000 = "Ehlers-Danlos syndrome, classic type I [MIM 130000]";
    private static final String ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_11_MIM_610476 = "Arrhythmogenic right ventricular dysplasia, type 11 [MIM 610476]";
    private static final String ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_10_MIM_610193 = "Arrhythmogenic right ventricular dysplasia, type 10 [MIM 610193]";
    private static final String ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_8_MIM_607450 = "Arrhythmogenic right ventricular dysplasia, type 8 [MIM 607450]";
    private static final String MARFAN_SYNDROME_MIM_154700 = "Marfan syndrome [MIM 154700]";
    private static final String ICHTHYOSIS_VULGARIS_MIM_146700 = "Ichthyosis vulgaris [MIM 146700]";
    private static final String ATOPIC_DERMATITIS_MIM_605804 = "Atopic dermatitis [MIM 605804]";
    private static final String FABRY_DISEASE_MIM_301500 = "Fabry disease [MIM 301500]";
    private static final String MATURITY_ONSET_DIABETES_OF_THE_YOUNG_TYPE_3_MIM_600496 = "Maturity onset diabetes of the young, type 3 [MIM 600496]";
    private static final String RENAL_CYSTS_AND_DIABETES_SYNDROME_MIM_137920 = "Renal cysts and diabetes syndrome [MIM 137920]";
    private static final String LONG_QT_SYNDROME_5_MIM_613695 = "Long QT syndrome 5 [MIM 613695]";
    private static final String JERVELL_AND_LANGE_NIELSEN_SYNDROME_2_MIM_612347 = "Jervell and Lange-Nielsen syndrome 2 [MIM 612347]";
    private static final String LONG_QT_SYNDROME_2_MIM_613688 = "Long QT syndrome 2 [MIM 613688]";
    private static final String ANDERSEN_TAWIL_SYNDROME_MIM_170390 = "Andersen-Tawil syndrome [MIM 170390]";
    private static final String LONG_QT_SYNDROME_1_MIM_192500 = "Long QT syndrome 1 [MIM 192500]";
    private static final String JERVELL_AND_LANGE_NIELSEN_SYNDROME_1_MIM_607542 = "Jervell and Lange-Nielsen syndrome 1 [MIM 607542]";
    private static final String FAMILIAL_HYPERCHOLESTEROLEMIA_MIM_143890 = "Familial hypercholesterolemia [MIM 143890]";
    private static final String DILATED_CARDIOMYOPATHY_1A_MIM_115200 = "Dilated cardiomyopathy 1A [MIM 115200]";
    private static final String OBESITY_AUTOSOMAL_DOMINANT_MIM_601665 = "Obesity, autosomal dominant [MIM 601665]";
    private static final String MULTIPLE_ENDOCRINE_NEOPLASIA_1_MIM_131100 = "Multiple endocrine neoplasia 1  [MIM 131100]";
    private static final String HEREDITARY_NON_POLYPOSIS_COLORECTAL_CANCER_TYPE_2_MIM_609310 = "Hereditary non-polyposis colorectal cancer, type 2  [MIM 609310]";
    private static final String HEREDITARY_NON_POLYPOSIS_COLORECTAL_CANCER_TYPE_1_MIM_120435 = "Hereditary non-polyposis colorectal cancer, type 1 [MIM 120435]";
    private static final String HEREDITARY_NON_POLYPOSIS_COLORECTAL_CANCER_TYPE_5_MIM_614350 = "Hereditary non-polyposis colorectal cancer, type 5 [MIM 614350]";
    private static final String HOMOCYSTINURIA_DUE_TO_MTHFR_DEFICIENCY_MIM_236250 = "Homocystinuria due to MTHFR deficiency [MIM 236250]";
    private static final String FAMILIAL_ADENOMATOUS_POLYPOSIS_2_MIM_608456 = "Familial adenomatous polyposis 2 [MIM 608456]";
    private static final String FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_4_MIM_115197 = "Familial hypertrophic cardiomyopathy 4 [MIM 115197]";
    private static final String FAMILIAL_THORACIC_AORTIC_ANEURYSM_4_MIM_132900 = "Familial thoracic aortic aneurysm 4 [MIM 132900]";
    private static final String FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_1_MIM_192600 = "Familial hypertrophic cardiomyopathy 1 [MIM 192600]";
    private static final String FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_10_MIM_608758 = "Familial hypertrophic cardiomyopathy 10 [MIM 608758]";
    private static final String FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_8_MIM_608751 = "Familial hypertrophic cardiomyopathy 8 [MIM 608751]";
    private static final String FAMILIAL_THORACIC_AORTIC_ANEURYSM_7_MIM_7613780 = "Familial thoracic aortic aneurysm 7 [MIM 7613780]";
    private static final String NEUROFIBROMATOSIS_TYPE_2_MIM_101000 = "Neurofibromatosis, type 2 [MIM 101000]";
    private static final String INSENSITIVITY_TO_PAIN_CONGENITAL_WITH_ANHIDROSIS_MIM_256800 = "Insensitivity to pain, congenital, with anhidrosis [MIM 256800]";
    private static final String ORNITHINE_TRANSCARBAMYLASE_DEFICIENCY_MIM_311250 = "Ornithine transcarbamylase deficiency [MIM 311250]";
    private static final String BREAST_CANCER_SUSCEPTIBILITY_MIM_114480 = "Breast cancer susceptibility [MIM 114480]";
    private static final String PANCREATIC_CANCER_SUSCEPTIBILITY_3_MIM_613348 = "Pancreatic cancer susceptibility 3 [MIM 613348]";
    private static final String HYPERCHOLESTEROLEMIA_AUTOSOMAL_DOMINANT_3_MIM_603776 = "Hypercholesterolemia, autosomal dominant, 3 [MIM 603776]";
    private static final String ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_9_MIM_609040 = "Arrhythmogenic right ventricular dysplasia, type 9 [MIM 609040]";
    private static final String HEREDITARY_NON_POLYPOSIS_COLORECTAL_CANCER_TYPE_4_MIM_614337 = "Hereditary non-polyposis colorectal cancer, type 4 [MIM 614337]";
    private static final String COLORECTAL_CANCER_SUSCEPTIBILITY_10_MIM_612591 = "Colorectal cancer susceptibility 10 [MIM 612591]";
    private static final String COLORECTAL_CANCER_SUSCEPTIBILITY_12_MIM_615083 = "Colorectal cancer susceptibility 12 [MIM 615083]";
    private static final String FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_6_MIM_600858 = "Familial hypertrophic cardiomyopathy 6  [MIM 600858]";
    private static final String PTEN_HAMARTOMA_TUMOR_SYNDROMES_MIM_158350 = "PTEN hamartoma tumor syndromes [MIM 158350]";
    private static final String RETINOBLASTOMA_MIM_180200 = "Retinoblastoma [MIM 180200]";
    private static final String MULTIPLE_ENDOCRINE_NEOPLASIA_2A_MIM_171400 = "Multiple endocrine neoplasia 2A [MIM 171400]";
    private static final String MULTIPLE_ENDOCRINE_NEOPLASIA_2B_MIM_162300 = "Multiple endocrine neoplasia 2B [MIM 162300]";
    private static final String MALIGNANT_HYPERTHERMIA_SUCEPTIBILITY_1_MIM_145600 = "Malignant hyperthermia suceptibility 1 [MIM 145600]";
    private static final String CATECHOLAMINERGIC_POLYMORPHIC_VENTRICULAR_TACHYCARDIA_1_MIM_604772 = "Catecholaminergic polymorphic ventricular tachycardia 1 [MIM 604772]";
    private static final String DRAVET_SYNDROME_MIM_607208 = "Dravet syndrome [MIM 607208]";
    private static final String FEBRILE_SEIZURES_FAMILIAL_3A_MIM_604403 = "Febrile seizures, familial, 3A [MIM 604403]";
    private static final String MIGRAINE_FAMILIAL_HEMIPLEGIC_3_MIM_609634 = "Migraine, familial hemiplegic, 3 [MIM 609634]";
    private static final String LONG_QT_SYNDROME_3_MIM_603830 = "Long QT syndrome 3 [MIM 603830]";
    private static final String PAROXYSMAL_EXTREME_PAIN_DISORDER_MIM_167400 = "Paroxysmal extreme pain disorder [MIM 167400]";
    private static final String PRIMARY_ERYTHERMALGIA_MIM_133020 = "Primary erythermalgia [MIM 133020]";
    private static final String CONGENITAL_INSENSITIVITY_TO_PAIN_MIM_243000 = "Congenital insensitivity to pain [MIM 243000]";
    private static final String PARAGANGLIOMAS_2_MIM_601650 = "Paragangliomas 2 [MIM 601650]";
    private static final String PARAGANGLIOMAS_4_MIM_115310 = "Paragangliomas 4 [MIM 115310]";
    private static final String PARAGANGLIOMAS_3_MIM_605376 = "Paragangliomas 3 [MIM 605376]";
    private static final String PARAGANGLIOMAS_1_MIM_168000 = "Paragangliomas 1 [MIM 168000]";
    private static final String ALPHA_1_ANTITRYPSIN_DEFICIENCY_MIM_613490 = "Alpha-1-antitrypsin deficiency [MIM 613490]";
    private static final String ARTERIAL_TORTUOSITY_SYNDROME_MIM_208050 = "Arterial tortuosity syndrome [MIM 208050]";
    private static final String LOEYS_DIETZ_SYNDROME_3_MIM_613785 = "Loeys-Dietz syndrome 3 [MIM 613785]";
    private static final String JUVENILE_POLYPOSIS_SYNDROME_MIM_174900 = "Juvenile polyposis syndrome [MIM 174900]";
    private static final String JUVENILE_POLYPOSIS_HEREDITARY_HEMORRHAGIC_TELANGIECTASIA_SYNDROME_MIM_175050 = "Juvenile polyposis/hereditary hemorrhagic telangiectasia syndrome [MIM 175050]";
    private static final String MYHRE_SYNDROME_MIM_139210 = "Myhre syndrome [MIM 139210]";
    private static final String PEUTZ_JEGHERS_SYNDROME_MIM_175200 = "Peutz-Jeghers syndrome [MIM 175200]";
    private static final String PITT_HOPKINS_SYNDROME_MIM_610954 = "Pitt-Hopkins syndrome [MIM 610954]";
    private static final String OSTEOPETROSIS_AUTOSOMAL_RECESSIVE_1_MIM_259700 = "Osteopetrosis, autosomal recessive 1 [MIM 259700]";
    private static final String LOEYS_DIETZ_SYNDROME_1_MIM_609192 = "Loeys-Dietz syndrome 1 [MIM  609192]";
    private static final String LOEYS_DIETZ_SYNDROME_2_MIM_610168 = "Loeys-Dietz syndrome 2 [MIM 610168]";
    private static final String ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_5_MIM_604400 = "Arrhythmogenic right ventricular dysplasia, type 5 [MIM 604400]";
    private static final String FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_7_MIM_613690 = "Familial hypertrophic cardiomyopathy 7 [MIM 613690]";
    private static final String DILATED_CARDIOMYOPATHY_1D_MIM_601494 = "Dilated cardiomyopathy 1D [MIM 601494]";
    private static final String FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_2_MIM_115195 = "Familial hypertrophic cardiomyopathy 2 [MIM 115195]";
    private static final String LI_FRAUMENI_SYNDROME_MIM_151623 = "Li-Fraumeni syndrome [MIM 151623]";
    private static final String FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_3_MIM_115196 = "Familial hypertrophic cardiomyopathy 3 [MIM 115196]";
    private static final String TUBEROUS_SCLEROSIS_TYPE_1_MIM_191100 = "Tuberous sclerosis type 1 [MIM 191100]";
    private static final String TUBEROUS_SCLEROSIS_TYPE_2_MIM_613254 = "Tuberous sclerosis type 2 [MIM 613254]";
    private static final String AMYLOIDOSIS_TRANSTHYRETIN_RELATED_MIM_105210 = "Amyloidosis, transthyretin-related [MIM 105210]";
    private static final String IMMUNODEFICIENCY_35_MIM_611521 = "Immunodeficiency 35 [MIM 611521]";
    private static final String FAMILIAL_JUVENILE_HYPERURICEMIC_NEPHROPATHY_1_MIM_162000 = "Familial juvenile hyperuricemic nephropathy 1 [MIM 162000]";
    private static final String RICKETS_VITAMIN_D_DEPENDENT_2A_VDDR2A_MIM_277440 = "Rickets vitamin D-dependent 2A (VDDR2A) [MIM 277440]";
    private static final String VON_HIPPEL_LINDAU_DISEASE_MIM_193300 = "Von Hippel-Lindau disease [MIM 193300]";
    private static final String WILMS_TUMOR_TYPE_1_MIM_194070 = "Wilms tumor, type 1 [MIM  194070]";
    private static final String ACYL_COA_DEHYDROGENASE_MEDIUM_CHAIN_DEFICIENCY_MIM_201450 = "Acyl-CoA dehydrogenase medium-chain deficiency [MIM 201450]";
    private static final String HEREDITARY_FRUCTOSE_INTOLERANCE_MIM_229600 = "Hereditary fructose intolerance [MIM 229600]";
    private static final String MAPLE_SYRUP_URINE_DISEASE_TYPE_IB_MIM_248600 = "Maple syrup urine disease, type IB [MIM 248600]";
    private static final String BLOOM_SYNDROME_MIM_210900 = "Bloom syndrome [MIM 210900]";
    private static final String CARNITINE_PALMITOYLTRANSFERASE_2_DEFICIENCY_MIM_255110 = "Carnitine palmitoyltransferase 2 deficiency  [MIM 255110]";
    private static final String ADRENAL_HYPERPLASIA_CONGENITAL_DUE_TO_21_HYDROXYLASE_DEFICIENCY_MIM_201910 = "Adrenal hyperplasia, congenital, due to 21-hydroxylase deficiency [MIM:201910]";
    private static final String FACTOR_V_DEFICIENCY_MIM_227400 = "Factor V deficiency [MIM 227400]";
    private static final String TYROSINEMIA_TYPE_I_MIM_276700 = "Tyrosinemia, type I [MIM 276700]";
    private static final String GLYCOGEN_STORAGE_DISEASE_TYPE_IA_MIM_232200 = "Glycogen storage disease, type IA [MIM 232200]";
    private static final String HEMOCHROMATOSIS_MIM_235200 = "Hemochromatosis [MIM 235200]";
    private static final String FAMILIAL_MEDITERRANEAN_FEVER_MIM_249100 = "Familial Mediterranean fever [MIM 249100]";
    private static final String HYPERINSULINEMIC_HYPOGLYCEMIA_FAMILIAL_1_MIM_256450 = "Hyperinsulinemic hypoglycemia, familial, 1 [MIM 256450]";
    private static final String WILSON_DISEASE_MIM_277900 = "Wilson disease [MIM 277900]";
    private static final String USHER_SYNDROME_TYPE_3A_MIM_276902 = "Usher syndrome type 3A [MIM 276902]";
    private static final String EHLERS_DANLOS_SYNDROME_CLASSIC_TYPE_MIM_130000 = "Ehlers-Danlos syndrome, classic type [MIM 130000]";
    private static final String RETINITIS_PIGMENTOSA_59_MIM_613861 = "Retinitis pigmentosa 59 [MIM 613861]";
    private static final String DIHYDROLIPOAMIDE_DEHYDROGENASE_DEFICIENCY_MIM_246900 = "Dihydrolipoamide dehydrogenase deficiency [MIM 246900]";
    private static final String FACTOR_XI_MIM_612416 = "Factor XI [MIM 612416]";
    private static final String FANCONI_ANEMIA_COMPLEMENTATION_GROUP_C_MIM_227645 = "Fanconi anemia, complementation group C [MIM 227645]";
    private static final String HERMANSKY_PUDLAK_SYNDROME_3_MIM_614072 = "Hermansky-Pudlak syndrome 3 [MIM 614072]";
    private static final String LONG_QT_SYNDROME_6_MIM_613693 = "Long QT syndrome 6 [MIM 613693]";
    private static final String SPASTIC_PARAPLEGIA_TYPE_7_AUTOSOMAL_RECESSIVE_MIM_607259 = "Spastic paraplegia type 7, autosomal recessive [MIM 607259]";
    private static final String DYSTONIA_1_TORSION_MIM_128100 = "Dystonia-1, torsion [MIM 128100]";
    private static final String MYELOPROLIFERATIVE_NEOPLASMS_SOMATIC_INCLUDING_POLYCYTHEMIA_VERA_MIM_263300 = "Myeloproliferative neoplasms, somatic, including polycythemia vera [MIM 263300]";
    private static final String SUSCEPTIBILITY_TO_NOISE_INDUCED_HEARING_LOSS_MIM_613035 = "Susceptibility to noise induced hearing loss [MIM 613035]";
    private static final String BRUGADA_SYNDROME_1_MIN_601144 = "Brugada syndrome 1 [MIM 601144]";
    private static final String THROMBOCYTHEMIA_3_MIN_614521 = "Thrombocythemia 3 [MIM 614521]";

    // Value for indicationForTesting (JSON)
    private static final String INDICATION_FOR_TESTING_FIELD_NAME = "indicationForTesting";
    private static final String INTELLECTUAL_DISABILITY = "Intellectual disability";
    private static final String ASTHMA = "Asthma";
    private static final String ATOPIC_DERMATITIS = "Atopic dermatitis";
    private static final String BIPOLAR_AFFECTIVE_DISORDER = "Bipolar affective disorder";
    private static final String CORONARY_ARTERY_DISEASE = "Coronary artery disease";
    private static final String BREAST_CARCINOMA = "Breast carcinoma";
    private static final String CIRRHOSIS = "Cirrhosis";
    private static final String SCHIZOPHRENIA = "Schizophrenia";
    private static final String CONGESTIVE_HEART_FAILURE = "Congestive heart failure";
    private static final String RHEUMATOID_ARTHRITIS = "Rheumatoid arthritis";
    private static final String OBESITY = "Obesity";
    private static final String NOT_SELECTED_FOR_TRAIT = "Not selected for trait";
    private static final String HEALTHY = "Healthy";
    private static final String ADULT_MIGRAINE = "Adult Migraine";
    private static final String OPIOID_DEPENDENCE_NEONATAL_ABSTINENCE = "Opioid dependence, neonatal abstinence";
    private static final String PEDIATRIC_MIGRAINE = "Pediatric Migraine";
    private static final String ABNORMAL_SEX_DETERMINATION = "Abnormal sex determination";
    private static final String ABNORMALITY_OF_PAIN_SENSATION = "Abnormality of pain sensation";
    private static final String ABNORMALITY_OF_THE_HEART_VALVES = "Abnormality of the heart valves";
    private static final String ASCENDING_AORTIC_DILATION_ANEURYSM = "Ascending aortic dilation / Aneurysm";
    private static final String AUTISTIC_BEHAVIOR = "Autistic behavior";
    private static final String AUTOIMMUNITY = "Autoimmunity";
    private static final String CHRONIC_SINUSITIS = "Chronic sinusitis";
    private static final String SEIZURES = "Seizures";
    private static final String COLORECTAL_CANCER_POLYPS = "Colorectal Cancer / Polyps";
    private static final String DEPRESSION = "Depression";
    private static final String ORNITHINE_TRANSCARBAMYLASE_DEFICIENCY_HYPERAMMONEMIA_DUE_TO = "Ornithine Transcarbamylase Deficiency, Hyperammonemia due to";
    private static final String ARRHYTHMIA = "Arrhythmia";
    private static final String HYPERLIPIDEMIA = "Hyperlipidemia";
    private static final String HYPERTRIGLYCERIDEMIA = "Hypertriglyceridemia";
    private static final String STROKE = "Stroke";
    private static final String CHRONIC_KIDNEY_DISEASE = "Chronic kidney disease";
    private static final String NEUROFIBROMATOSIS_TYPE_2 = "Neurofibromatosis, type 2";
    private static final String AMYLOIDOSIS_HEREDITARY_TRANSTHYRETIN_RELATED = "Amyloidosis, Hereditary, Transthyretin-Related";
    private static final String EPISODIC_ATAXIA_2 = "Episodic ataxia 2";
    private static final String BREAST_CANCER_SUCEPTIBILITY = "Breast cancer suceptibility";
    private static final String FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_2 = "Familial hypertrophic cardiomyopathy 2";
    private static final String FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_3 = "Familial hypertrophic cardiomyopathy 3";
    private static final String FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_4 = "Familial hypertrophic cardiomyopathy 4";
    private static final String DILATED_CARDIOMYOPATHY_1A = "Dilated cardiomyopathy 1A";
    private static final String PARAGANGLIOMAS_4 = "Paragangliomas 4";
    private static final String HEREDITARY_NON_POLYPOSIS_COLORECTAL_CANCER_TYPE_1 = "Hereditary non-polyposis colorectal cancer, type 1";
    private static final String EHLERS_DANLOS_SYNDROME_CLASSIC_TYPE_I = "Ehlers-Danlos syndrome, classic type I";
    private static final String EHLERS_DANLOS_SYNDROME_4 = "Ehlers-Danlos syndrome 4";
    private static final String MULTIPLE_ENDOCRINE_NEOPLASIA_1 = "Multiple endocrine neoplasia 1";
    private static final String FAMILIAL_THORACIC_AORTIC_ANEURYSM_4 = "Familial thoracic aortic aneurysm 4";
    private static final String RENAL_CYSTS_AND_DIABETES_SYNDROME = "Renal cysts and diabetes syndrome";
    private static final String MYHRE_SYNDROME = "Myhre syndrome";
    private static final String HYPERCHOLESTEROLEMIA_FAMILIAL = "Hypercholesterolemia, Familial";
    private static final String HYPERCHOLESTEROLEMIA_DUE_TO_LIGAND_DEFECTIVE_APO_B = "Hypercholesterolemia, due to ligand-defective apo B";
    private static final String MALIGNANT_HYPERTHERMIA_SUCEPTIBILITY_1 = "Malignant hyperthermia suceptibility 1";
    private static final String LI_FRAUMENI_SYNDROME = "Li-Fraumeni syndrome";
    private static final String MARFAN_SYNDROME = "Marfan syndrome";
    private static final String PTEN_HAMARTOMA_TUMOR_SYNDROMES = "PTEN hamartoma tumor syndromes";
    private static final String MULTIPLE_ENDOCRINE_NEOPLASIA_2B = "Multiple endocrine neoplasia 2B";
    private static final String PARAGANGLIOMAS_1 = "Paragangliomas 1";
    private static final String ANDERSEN_TAWIL_SYNDROME = "Andersen-Tawil syndrome";
    private static final String MULTIPLE_ENDOCRINE_NEOPLASIA_2A = "Multiple endocrine neoplasia 2A";
    private static final String JUVENILE_POLYPOSIS_SYNDROME = "Juvenile polyposis syndrome";
    private static final String JUVENILE_POLYPOSIS_HEREDITARY_HEMORRHAGIC_TELANGIECTASIA_SYNDROME = "Juvenile polyposis/hereditary hemorrhagic telangiectasia syndrome";
    private static final String FAMILIAL_ADENOMATOUS_POLYPOSIS_1 = "Familial adenomatous polyposis 1";
    private static final String PEUTZ_JEGHERS_SYNDROME = "Peutz-Jeghers syndrome";
    private static final String RETINOBLASTOMA = "Retinoblastoma";
    private static final String TUBEROUS_SCLEROSIS_1 = "Tuberous Sclerosis 1";
    private static final String LONG_QT_SYNDROME_1 = "Long QT syndrome 1";
    private static final String FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_1 = "Familial hypertrophic cardiomyopathy 1";
    private static final String VON_HIPPEL_LINDAU_DISEASE = "Von Hippel-Lindau disease";
    private static final String WILMS_TUMOR_TYPE_1 = "Wilms tumor, type 1";
    private static final String FABRY_DISEASE = "Fabry disease";
    private static final String ORNITHINE_TRANSCARBAMYLASE_DEFICIENCY = "Ornithine transcarbamylase deficiency";
    private static final String MATURITY_ONSET_DIABETES_OF_THE_YOUNG_TYPE_3 = "Maturity onset diabetes of the young, type 3";
    private static final String FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_6 = "Familial hypertrophic cardiomyopathy 6";
    private static final String DILATED_CARDIOMYOPATHY_1D = "Dilated cardiomyopathy 1D";
    private static final String PARAGANGLIOMAS_2 = "Paragangliomas 2";
    private static final String MALIGNANT_HYPERTHERMIA_SUCEPTIBILITY_5 = "Malignant hyperthermia suceptibility 5";
    private static final String HYPERCHOLESTEROLEMIA_AUTOSOMAL_DOMINANT_3 = "Hypercholesterolemia, autosomal dominant, 3";
    private static final String LONG_QT_SYNDROME_3 = "Long QT syndrome 3";
    private static final String FAMILIAL_BREAST_OVARIAN_CANCER_1 = "Familial breast-ovarian cancer 1";
    private static final String ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_5 = "Arrhythmogenic right ventricular dysplasia, type 5";
    private static final String CATECHOLAMINERGIC_POLYMORPHIC_VENTRICULAR_TACHYCARDIA_1 = "Catecholaminergic polymorphic ventricular tachycardia 1";
    private static final String PARAGANGLIOMAS_3 = "Paragangliomas 3";
    private static final String ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_8 = "Arrhythmogenic right ventricular dysplasia, type 8";
    private static final String JERVELL_AND_LANGE_NIELSEN_SYNDROME_1 = "Jervell and Lange-Nielsen syndrome 1";
    private static final String FAMILIAL_ADENOMATOUS_POLYPOSIS_2 = "Familial adenomatous polyposis 2";
    private static final String FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_8 = "Familial hypertrophic cardiomyopathy 8";
    private static final String FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_10 = "Familial hypertrophic cardiomyopathy 10";
    private static final String ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_9 = "Arrhythmogenic right ventricular dysplasia, type 9";
    private static final String LOEYS_DIETZ_SYNDROME_1 = "Loeys-Dietz syndrome 1";
    private static final String HEREDITARY_NON_POLYPOSIS_COLORECTAL_CANCER_TYPE_2 = "Hereditary non-polyposis colorectal cancer, type 2";
    private static final String LOEYS_DIETZ_SYNDROME_2 = "Loeys-Dietz syndrome 2";
    private static final String ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_10 = "Arrhythmogenic right ventricular dysplasia, type 10";
    private static final String ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_11 = "Arrhythmogenic right ventricular dysplasia, type 11";
    private static final String FAMILIAL_THORACIC_AORTIC_ANEURYSM_6 = "Familial thoracic aortic aneurysm 6";
    private static final String FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_11 = "Familial hypertrophic cardiomyopathy 11";
    private static final String JERVELL_AND_LANGE_NIELSEN_SYNDROME_2 = "Jervell and Lange-Nielsen syndrome 2";
    private static final String FAMILIAL_BREAST_OVARIAN_CANCER_2 = "Familial breast-ovarian cancer 2";
    private static final String COLORECTAL_CANCER_SUCEPTIBILITY_10 = "Colorectal cancer suceptibility 10";
    private static final String TUBEROUS_SCLEROSIS_TYPE_2 = "Tuberous sclerosis type 2";
    private static final String PANCREATIC_CANCER_SUCEPTIBILITY = "Pancreatic cancer suceptibility";
    private static final String LONG_QT_SYNDROME_2 = "Long QT syndrome 2";
    private static final String FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_7 = "Familial hypertrophic cardiomyopathy 7";
    private static final String LONG_QT_SYNDROME_5 = "Long QT syndrome 5";
    private static final String FAMILIAL_THORACIC_AORTIC_ANEURYSM_7 = "Familial thoracic aortic aneurysm 7";
    private static final String LOEYS_DIETZ_SYNDROME_3 = "Loeys-Dietz syndrome 3";
    private static final String HEREDITARY_NON_POLYPOSIS_COLORECTAL_CANCER_TYPE_4 = "Hereditary non-polyposis colorectal cancer, type 4";
    private static final String HEREDITARY_NON_POLYPOSIS_COLORECTAL_CANCER_TYPE_5 = "Hereditary non-polyposis colorectal cancer, type 5";
    private static final String COLORECTAL_CANCER_SUCEPTIBILITY_12 = "Colorectal cancer suceptibility 12";
    private static final String CARDIOMYOPATHY = "Cardiomyopathy";
    private static final String OVARIAN_CANCER_EPITHELIAL_INCLUDED = "Ovarian Cancer, Epithelial, Included";
    private static final String PULMONARY_HYPERTENSION = "Pulmonary Hypertension";
    private static final String EHLERS_DANLOS_SYNDROME = "Ehlers-Danlos Syndrome";

    // Field : [Values]
    private static final Map<String, String[]> controlledVocabMap = Collections.unmodifiableMap(
            new HashMap<String, String[]>() {{
                // add overalL interpretation
                put(OVERALL_INTERP_FIELD_NAME, new String[]{OVERALL_INTERP_POSITIVE
                        , OVERALL_INTERP_INCONCLUSIVE
                        , OVERALL_INTERP_NEGATIVE
                        , OVERALL_INTERP_FAILURE
                        , OVERALL_INTERP_CARRIER});

                // add genomic source
                put(GENOMIC_SOURCE_FIELD_NAME, new String[]{GENOMIC_SOURCE_GERMLINE
                        , GENOMIC_SOURCE_SOMATIC
                        , GENOMIC_SOURCE_PRENATAL});

                // add race
                put(RACE_FIELD_NAME, new String[]{RACE_ETHNICITY_WHITE
                        , RACE_ETHNICITY_AM_IND_ALA_NA
                        , RACE_ETHNICITY_ASIAN
                        , RACE_ETHNICITY_BLK_AFR_AM
                        , RACE_ETHNICITY_NA_HA_PAC_IS
                        , RACE_ETHNICITY_HIS_LAT
                        , RACE_ETHNICITY_ASH_JEW
                        , RACE_ETHNICITY_MIXED
                        , RACE_ETHNICITY_UNSPECIFIED
                        , RACE_ETHNICITY_NOT_HIS_LAT
                        , RACE_ETHNICITY_UNKNOWN
                        , RACE_ETHNICITY_OTHER});

                // add ethnicity
                put(ETHNICITY_FIELD_NAME, new String[]{RACE_ETHNICITY_WHITE
                        , RACE_ETHNICITY_AM_IND_ALA_NA
                        , RACE_ETHNICITY_ASIAN
                        , RACE_ETHNICITY_BLK_AFR_AM
                        , RACE_ETHNICITY_NA_HA_PAC_IS
                        , RACE_ETHNICITY_HIS_LAT
                        , RACE_ETHNICITY_ASH_JEW
                        , RACE_ETHNICITY_MIXED
                        , RACE_ETHNICITY_UNSPECIFIED
                        , RACE_ETHNICITY_NOT_HIS_LAT
                        , RACE_ETHNICITY_UNKNOWN
                        , RACE_ETHNICITY_OTHER});

                // add diseases
                put(DISEASES_FIELD_NAME, new String[]{FAMILIAL_THORACIC_AORTIC_ANEURYSM_6_MIM_611788
                         , FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_11_MIM_612098
                         , LONG_QT_SYNDROME_4_MIM_600919
                         , FAMILIAL_ADENOMATOUS_POLYPOSIS_1_MIM_175100
                         , FAMILIAL_HYPERCHOLESTEROLEMIA_MIM_144010
                         , SUCEPTIBILITY_TO_BREAST_CANCER_MIM_114480
                         , ATAXIA_TELANGIECTASIA_MIM_208900
                         , ALTERNATING_HEMIPLEGIA_OF_CHILDHOOD_MIM_104290
                         , MIGRAINE_FAMILIAL_HEMIPLEGIC_2_MIM_602481
                         , JUVENILE_POLYPOSIS_SYNDROME_MIM_174900
                         , PULMONARY_HYPERTENSION_FAMILIAL_PRIMARY_1_MIM_178600
                         , PULMONARY_VENOOCCLUSIVE_DISEASE_1_MIM_265450
                         , FAMILIAL_BREAST_OVARIAN_CANCER_1_MIM_604370
                         , FAMILIAL_BREAST_OVARIAN_CANCER_2_MIM_612555
                         , EPISODIC_ATAXIA_2_MIM_108500
                         , BRUGADA_SYNDROME_3_MIM_611875
                         , THIMOTHY_SYNDROME_MIM_601005
                         , MALIGNANT_HYPERTHERMIA_SUSCEPTIBILITY_5_MIM_601887
                         , ATYPICAL_HEMOLYTIC_UREMIC_SYNDROME_MIM_235400
                         , DENSE_DEPOSIT_DISEASE_MEMBRANOPROLIFERATIVE_GLOMERULONEPHRITIS_TYPE_II_MIM_609814
                         , CYSTIC_FIBROSIS_MIM_219700
                         , CONGENITAL_BILATERAL_ABSENCE_OF_THE_VAS_DEFERENS_MIM_277180
                         , SUSCEPTIBILITY_TO_BREAST_CANCER_MIM_114480
                         , SUSCEPTIBILITY_TO_PROSTATE_CANCER_MIM_176807
                         , EHLERS_DANLOS_SYNDROME_4_MIM_130050
                         , EHLERS_DANLOS_SYNDROME_CLASSIC_TYPE_I_MIM_130000
                         , ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_11_MIM_610476
                         , ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_10_MIM_610193
                         , ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_8_MIM_607450
                         , MARFAN_SYNDROME_MIM_154700
                         , ICHTHYOSIS_VULGARIS_MIM_146700
                         , ATOPIC_DERMATITIS_MIM_605804
                         , FABRY_DISEASE_MIM_301500
                         , MATURITY_ONSET_DIABETES_OF_THE_YOUNG_TYPE_3_MIM_600496
                         , RENAL_CYSTS_AND_DIABETES_SYNDROME_MIM_137920
                         , LONG_QT_SYNDROME_5_MIM_613695
                         , JERVELL_AND_LANGE_NIELSEN_SYNDROME_2_MIM_612347
                         , LONG_QT_SYNDROME_2_MIM_613688
                         , ANDERSEN_TAWIL_SYNDROME_MIM_170390
                         , LONG_QT_SYNDROME_1_MIM_192500
                         , JERVELL_AND_LANGE_NIELSEN_SYNDROME_1_MIM_607542
                         , FAMILIAL_HYPERCHOLESTEROLEMIA_MIM_143890
                         , DILATED_CARDIOMYOPATHY_1A_MIM_115200
                         , OBESITY_AUTOSOMAL_DOMINANT_MIM_601665
                         , MULTIPLE_ENDOCRINE_NEOPLASIA_1_MIM_131100
                         , HEREDITARY_NON_POLYPOSIS_COLORECTAL_CANCER_TYPE_2_MIM_609310
                         , HEREDITARY_NON_POLYPOSIS_COLORECTAL_CANCER_TYPE_1_MIM_120435
                         , HEREDITARY_NON_POLYPOSIS_COLORECTAL_CANCER_TYPE_5_MIM_614350
                         , HOMOCYSTINURIA_DUE_TO_MTHFR_DEFICIENCY_MIM_236250
                         , FAMILIAL_ADENOMATOUS_POLYPOSIS_2_MIM_608456
                         , FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_4_MIM_115197
                         , FAMILIAL_THORACIC_AORTIC_ANEURYSM_4_MIM_132900
                         , FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_1_MIM_192600
                         , FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_10_MIM_608758
                         , FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_8_MIM_608751
                         , FAMILIAL_THORACIC_AORTIC_ANEURYSM_7_MIM_7613780
                         , NEUROFIBROMATOSIS_TYPE_2_MIM_101000
                         , INSENSITIVITY_TO_PAIN_CONGENITAL_WITH_ANHIDROSIS_MIM_256800
                         , ORNITHINE_TRANSCARBAMYLASE_DEFICIENCY_MIM_311250
                         , BREAST_CANCER_SUSCEPTIBILITY_MIM_114480
                         , PANCREATIC_CANCER_SUSCEPTIBILITY_3_MIM_613348
                         , HYPERCHOLESTEROLEMIA_AUTOSOMAL_DOMINANT_3_MIM_603776
                         , ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_9_MIM_609040
                         , HEREDITARY_NON_POLYPOSIS_COLORECTAL_CANCER_TYPE_4_MIM_614337
                         , COLORECTAL_CANCER_SUSCEPTIBILITY_10_MIM_612591
                         , COLORECTAL_CANCER_SUSCEPTIBILITY_12_MIM_615083
                         , FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_6_MIM_600858
                         , PTEN_HAMARTOMA_TUMOR_SYNDROMES_MIM_158350
                         , RETINOBLASTOMA_MIM_180200
                         , MULTIPLE_ENDOCRINE_NEOPLASIA_2A_MIM_171400
                         , MULTIPLE_ENDOCRINE_NEOPLASIA_2B_MIM_162300
                         , MALIGNANT_HYPERTHERMIA_SUCEPTIBILITY_1_MIM_145600
                         , CATECHOLAMINERGIC_POLYMORPHIC_VENTRICULAR_TACHYCARDIA_1_MIM_604772
                         , DRAVET_SYNDROME_MIM_607208
                         , FEBRILE_SEIZURES_FAMILIAL_3A_MIM_604403
                         , MIGRAINE_FAMILIAL_HEMIPLEGIC_3_MIM_609634
                         , LONG_QT_SYNDROME_3_MIM_603830
                         , PAROXYSMAL_EXTREME_PAIN_DISORDER_MIM_167400
                         , PRIMARY_ERYTHERMALGIA_MIM_133020
                         , CONGENITAL_INSENSITIVITY_TO_PAIN_MIM_243000
                         , PARAGANGLIOMAS_2_MIM_601650
                         , PARAGANGLIOMAS_4_MIM_115310
                         , PARAGANGLIOMAS_3_MIM_605376
                         , PARAGANGLIOMAS_1_MIM_168000
                         , ALPHA_1_ANTITRYPSIN_DEFICIENCY_MIM_613490
                         , ARTERIAL_TORTUOSITY_SYNDROME_MIM_208050
                         , LOEYS_DIETZ_SYNDROME_3_MIM_613785
                         , JUVENILE_POLYPOSIS_SYNDROME_MIM_174900
                         , JUVENILE_POLYPOSIS_HEREDITARY_HEMORRHAGIC_TELANGIECTASIA_SYNDROME_MIM_175050
                         , MYHRE_SYNDROME_MIM_139210
                         , PEUTZ_JEGHERS_SYNDROME_MIM_175200
                         , PITT_HOPKINS_SYNDROME_MIM_610954
                         , OSTEOPETROSIS_AUTOSOMAL_RECESSIVE_1_MIM_259700
                         , LOEYS_DIETZ_SYNDROME_1_MIM_609192
                         , LOEYS_DIETZ_SYNDROME_2_MIM_610168
                         , ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_5_MIM_604400
                         , FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_7_MIM_613690
                         , DILATED_CARDIOMYOPATHY_1D_MIM_601494
                         , FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_2_MIM_115195
                         , LI_FRAUMENI_SYNDROME_MIM_151623
                         , FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_3_MIM_115196
                         , TUBEROUS_SCLEROSIS_TYPE_1_MIM_191100
                         , TUBEROUS_SCLEROSIS_TYPE_2_MIM_613254
                         , AMYLOIDOSIS_TRANSTHYRETIN_RELATED_MIM_105210
                         , IMMUNODEFICIENCY_35_MIM_611521
                         , FAMILIAL_JUVENILE_HYPERURICEMIC_NEPHROPATHY_1_MIM_162000
                         , RICKETS_VITAMIN_D_DEPENDENT_2A_VDDR2A_MIM_277440
                         , VON_HIPPEL_LINDAU_DISEASE_MIM_193300
                         , WILMS_TUMOR_TYPE_1_MIM_194070
                         , ACYL_COA_DEHYDROGENASE_MEDIUM_CHAIN_DEFICIENCY_MIM_201450
                         , HEREDITARY_FRUCTOSE_INTOLERANCE_MIM_229600
                         , MAPLE_SYRUP_URINE_DISEASE_TYPE_IB_MIM_248600
                         , BLOOM_SYNDROME_MIM_210900
                         , CARNITINE_PALMITOYLTRANSFERASE_2_DEFICIENCY_MIM_255110
                         , ADRENAL_HYPERPLASIA_CONGENITAL_DUE_TO_21_HYDROXYLASE_DEFICIENCY_MIM_201910
                         , FACTOR_V_DEFICIENCY_MIM_227400
                         , TYROSINEMIA_TYPE_I_MIM_276700
                         , GLYCOGEN_STORAGE_DISEASE_TYPE_IA_MIM_232200
                         , HEMOCHROMATOSIS_MIM_235200
                         , FAMILIAL_MEDITERRANEAN_FEVER_MIM_249100
                         , HEREDITARY_NON_POLYPOSIS_COLORECTAL_CANCER_TYPE_1_MIM_120435
                         , HYPERINSULINEMIC_HYPOGLYCEMIA_FAMILIAL_1_MIM_256450
                         , WILSON_DISEASE_MIM_277900
                         , USHER_SYNDROME_TYPE_3A_MIM_276902
                         , EHLERS_DANLOS_SYNDROME_CLASSIC_TYPE_MIM_130000
                         , RETINITIS_PIGMENTOSA_59_MIM_613861
                         , DIHYDROLIPOAMIDE_DEHYDROGENASE_DEFICIENCY_MIM_246900
                         , FACTOR_XI_MIM_612416
                         , FANCONI_ANEMIA_COMPLEMENTATION_GROUP_C_MIM_227645
                         , HERMANSKY_PUDLAK_SYNDROME_3_MIM_614072
                         , LONG_QT_SYNDROME_6_MIM_613693
                         , SPASTIC_PARAPLEGIA_TYPE_7_AUTOSOMAL_RECESSIVE_MIM_607259
                         , DYSTONIA_1_TORSION_MIM_128100
                         , MYELOPROLIFERATIVE_NEOPLASMS_SOMATIC_INCLUDING_POLYCYTHEMIA_VERA_MIM_263300
                         , SUSCEPTIBILITY_TO_NOISE_INDUCED_HEARING_LOSS_MIM_613035
                         , BRUGADA_SYNDROME_1_MIN_601144
                         , THROMBOCYTHEMIA_3_MIN_614521});

                // add indicationForTesting
                put(INDICATION_FOR_TESTING_FIELD_NAME, new String[]{INTELLECTUAL_DISABILITY
                         , ASTHMA
                         , ATOPIC_DERMATITIS
                         , BIPOLAR_AFFECTIVE_DISORDER
                         , CORONARY_ARTERY_DISEASE
                         , BREAST_CARCINOMA
                         , CIRRHOSIS
                         , SCHIZOPHRENIA
                         , CONGESTIVE_HEART_FAILURE
                         , RHEUMATOID_ARTHRITIS
                         , OBESITY
                         , NOT_SELECTED_FOR_TRAIT
                         , HEALTHY
                         , ADULT_MIGRAINE
                         , OPIOID_DEPENDENCE_NEONATAL_ABSTINENCE
                         , PEDIATRIC_MIGRAINE
                         , ABNORMAL_SEX_DETERMINATION
                         , ABNORMALITY_OF_PAIN_SENSATION
                         , ABNORMALITY_OF_THE_HEART_VALVES
                         , ASCENDING_AORTIC_DILATION_ANEURYSM
                         , AUTISTIC_BEHAVIOR
                         , AUTOIMMUNITY
                         , CHRONIC_SINUSITIS
                         , SEIZURES
                         , COLORECTAL_CANCER_POLYPS
                         , DEPRESSION
                         , ORNITHINE_TRANSCARBAMYLASE_DEFICIENCY_HYPERAMMONEMIA_DUE_TO
                         , ARRHYTHMIA
                         , HYPERLIPIDEMIA
                         , HYPERTRIGLYCERIDEMIA
                         , STROKE
                         , CHRONIC_KIDNEY_DISEASE
                         , NEUROFIBROMATOSIS_TYPE_2
                         , AMYLOIDOSIS_HEREDITARY_TRANSTHYRETIN_RELATED
                         , EPISODIC_ATAXIA_2
                         , BREAST_CANCER_SUCEPTIBILITY
                         , FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_2
                         , FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_3
                         , FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_4
                         , DILATED_CARDIOMYOPATHY_1A
                         , PARAGANGLIOMAS_4
                         , HEREDITARY_NON_POLYPOSIS_COLORECTAL_CANCER_TYPE_1
                         , EHLERS_DANLOS_SYNDROME_CLASSIC_TYPE_I
                         , EHLERS_DANLOS_SYNDROME_4
                         , MULTIPLE_ENDOCRINE_NEOPLASIA_1
                         , FAMILIAL_THORACIC_AORTIC_ANEURYSM_4
                         , RENAL_CYSTS_AND_DIABETES_SYNDROME
                         , MYHRE_SYNDROME
                         , HYPERCHOLESTEROLEMIA_FAMILIAL
                         , HYPERCHOLESTEROLEMIA_DUE_TO_LIGAND_DEFECTIVE_APO_B
                         , MALIGNANT_HYPERTHERMIA_SUCEPTIBILITY_1
                         , LI_FRAUMENI_SYNDROME
                         , MARFAN_SYNDROME
                         , PTEN_HAMARTOMA_TUMOR_SYNDROMES
                         , MULTIPLE_ENDOCRINE_NEOPLASIA_2B
                         , PARAGANGLIOMAS_1
                         , ANDERSEN_TAWIL_SYNDROME
                         , MULTIPLE_ENDOCRINE_NEOPLASIA_2A
                         , JUVENILE_POLYPOSIS_SYNDROME
                         , JUVENILE_POLYPOSIS_HEREDITARY_HEMORRHAGIC_TELANGIECTASIA_SYNDROME
                         , FAMILIAL_ADENOMATOUS_POLYPOSIS_1
                         , PEUTZ_JEGHERS_SYNDROME
                         , RETINOBLASTOMA
                         , TUBEROUS_SCLEROSIS_1
                         , LONG_QT_SYNDROME_1
                         , FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_1
                         , VON_HIPPEL_LINDAU_DISEASE
                         , WILMS_TUMOR_TYPE_1
                         , FABRY_DISEASE
                         , ORNITHINE_TRANSCARBAMYLASE_DEFICIENCY
                         , MATURITY_ONSET_DIABETES_OF_THE_YOUNG_TYPE_3
                         , FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_6
                         , DILATED_CARDIOMYOPATHY_1D
                         , PARAGANGLIOMAS_2
                         , MALIGNANT_HYPERTHERMIA_SUCEPTIBILITY_5
                         , HYPERCHOLESTEROLEMIA_AUTOSOMAL_DOMINANT_3
                         , LONG_QT_SYNDROME_3
                         , FAMILIAL_BREAST_OVARIAN_CANCER_1
                         , ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_5
                         , CATECHOLAMINERGIC_POLYMORPHIC_VENTRICULAR_TACHYCARDIA_1
                         , PARAGANGLIOMAS_3
                         , ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_8
                         , JERVELL_AND_LANGE_NIELSEN_SYNDROME_1
                         , FAMILIAL_ADENOMATOUS_POLYPOSIS_2
                         , FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_8
                         , FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_10
                         , ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_9
                         , LOEYS_DIETZ_SYNDROME_1
                         , HEREDITARY_NON_POLYPOSIS_COLORECTAL_CANCER_TYPE_2
                         , LOEYS_DIETZ_SYNDROME_2
                         , ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_10
                         , ARRHYTHMOGENIC_RIGHT_VENTRICULAR_DYSPLASIA_TYPE_11
                         , FAMILIAL_THORACIC_AORTIC_ANEURYSM_6
                         , FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_11
                         , JERVELL_AND_LANGE_NIELSEN_SYNDROME_2
                         , FAMILIAL_BREAST_OVARIAN_CANCER_2
                         , COLORECTAL_CANCER_SUCEPTIBILITY_10
                         , TUBEROUS_SCLEROSIS_TYPE_2
                         , PANCREATIC_CANCER_SUCEPTIBILITY
                         , LONG_QT_SYNDROME_2
                         , FAMILIAL_HYPERTROPHIC_CARDIOMYOPATHY_7
                         , LONG_QT_SYNDROME_5
                         , FAMILIAL_THORACIC_AORTIC_ANEURYSM_7
                         , LOEYS_DIETZ_SYNDROME_3
                         , HEREDITARY_NON_POLYPOSIS_COLORECTAL_CANCER_TYPE_4
                         , HEREDITARY_NON_POLYPOSIS_COLORECTAL_CANCER_TYPE_5
                         , COLORECTAL_CANCER_SUCEPTIBILITY_12
                         , CARDIOMYOPATHY
                         , OVARIAN_CANCER_EPITHELIAL_INCLUDED
                         , PULMONARY_HYPERTENSION
                         , EHLERS_DANLOS_SYNDROME});
            }});

    @JsonProperty("reportDate")
    private String reportDate;
    @JsonProperty("indicationForTesting")
    private String indicationForTesting;
    @JsonProperty("genomicSource")
    private String genomicSource;
    @JsonProperty("geneCoverage")
    private List<List<String>> geneCoverage = null;
    @JsonProperty("overallInterpretation")
    private String overallInterpretation;
    @JsonProperty("reportIdentifier")
    private String reportIdentifier;
    @JsonProperty("byName")
    private String byName;
    @JsonProperty("onDate")
    private String onDate;
    @JsonProperty("reportStatus")
    private String reportStatus;
    @JsonProperty("caseType")
    private String caseType;
    @JsonProperty("labStatus")
    private String labStatus;
    @JsonProperty("patientSampleID")
    private String patientSampleID;
    @JsonProperty("sampleCollectionSource")
    private String sampleCollectionSource;
    @JsonProperty("localID")
    private String localID;
    @JsonProperty("vipFile")
    private String vipFile;
    @JsonProperty("clinicalSite")
    private String clinicalSite;
    @JsonProperty("testName")
    private String testName;
    @JsonProperty("interrogatedButNotFound")
    private String interrogatedButNotFound;
    @JsonProperty("notInterpreted")
    private String notInterpreted;
    @JsonProperty("addendums")
    private List<List<String>> addendums = null;
    @JsonProperty("age")
    private String age;
    @JsonProperty("sampleType")
    private String sampleType;
    @JsonProperty("patientID")
    private String patientID;
    @JsonProperty("sex")
    private String sex;
    @JsonProperty("race")
    private String race;
    @JsonProperty("ethnicity")
    private String ethnicity;
    @JsonProperty("specimenType")
    private String specimenType;
    @JsonProperty("sampleCollectedDate")
    private String sampleCollectedDate;
    @JsonProperty("sampleReceivedDate")
    private String sampleReceivedDate;
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;
    @JsonProperty("diseaseStatus")
    private String diseaseStatus;
    @JsonProperty("diseases")
    private List<String> diseases;
    @JsonProperty("deidentified")
    private String deidentified;
    @JsonProperty("isPreliminary")
    private String isPreliminary;
    @JsonProperty("accessionNumber")
    private String accessionNumber;
    @JsonProperty("variants")
    private List<Variant> variants;
    @JsonProperty("cnvVariants")
    private List<CnvVariant> cnvvariants = null;
    @JsonProperty("sangerSiteNote")
    private String sangerSiteNote;
    @JsonProperty("pgxData")
    private List<PgxDatum> pgxData;
    @JsonProperty("orderingPhysicianName")
    private String orderingPhysicianName;
    @JsonProperty("patientFirstName")
    private String patientFirstName;
    @JsonProperty("patientMiddleInitial")
    private String patientMiddleInitial;
    @JsonProperty("patientLastName")
    private String patientLastName;
    @JsonProperty("reportComment")
    private String reportComment;
    @JsonProperty("methodology")
    private String methodology;
    @JsonProperty("background")
    private String background;
    @JsonProperty("clinicalCorrelation")
    private String clinicalCorrelation;
    @JsonProperty("pgxDescription")
    private String pgxDescription;
    @JsonProperty("cnvsFailed")
    private String cnvsFailed;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("reportDate")
    public String getReportDate() {
        return reportDate;
    }

    @JsonProperty("reportDate")
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    @JsonProperty("indicationForTesting")
    public String getIndicationForTesting() {
        return indicationForTesting;
    }

    @JsonProperty("indicationForTesting")
    public void setIndicationForTesting(String indicationForTesting) {
        this.indicationForTesting = indicationForTesting;
    }

    @JsonProperty("genomicSource")
    public String getGenomicSource() {
        return genomicSource;
    }

    @JsonProperty("genomicSource")
    public void setGenomicSource(String genomicSource) {
        this.genomicSource = genomicSource;
    }
    @JsonProperty("geneCoverage")
    public List<List<String>> getGeneCoverage() {
        if (geneCoverage == null) {
            geneCoverage = new ArrayList<List<String>>();
        }
        return geneCoverage;
    }

    @JsonProperty("geneCoverage")
    public void setGeneCoverage(List<List<String>> geneCoverage) {
        this.geneCoverage = geneCoverage;
    }

    @JsonProperty("overallInterpretation")
    public String getOverallInterpretation() {
        return overallInterpretation;
    }

    @JsonProperty("overallInterpretation")
    public void setOverallInterpretation(String overallInterpretation) {
        this.overallInterpretation = overallInterpretation;
    }

    @JsonProperty("reportIdentifier")
    public String getReportIdentifier() {
        return reportIdentifier;
    }

    @JsonProperty("reportIdentifier")
    public void setReportIdentifier(String reportIdentifier) {
        this.reportIdentifier = reportIdentifier;
    }

    @JsonProperty("byName")
    public String getByName() {
        return byName;
    }

    @JsonProperty("byName")
    public void setByName(String byName) {
        this.byName = byName;
    }

    @JsonProperty("onDate")
    public String getOnDate() {
        return onDate;
    }

    @JsonProperty("onDate")
    public void setOnDate(String onDate) {
        this.onDate = onDate;
    }

    @JsonProperty("reportStatus")
    public String getReportStatus() {
        return reportStatus;
    }

    @JsonProperty("reportStatus")
    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    @JsonProperty("caseType")
    public String getCaseType() { return caseType; }

    @JsonProperty("caseType")
    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    @JsonProperty("labStatus")
    public String getLabStatus() {
        return labStatus;
    }

    @JsonProperty("labStatus")
    public void setLabStatus(String labStatus) {
        this.labStatus = labStatus;
    }

    @JsonProperty("patientSampleID")
    public String getPatientSampleID() {
        return patientSampleID;
    }

    @JsonProperty("patientSampleID")
    public void setPatientSampleID(String patientSampleID) {
        this.patientSampleID = patientSampleID;
    }

    @JsonProperty("sampleCollectionSource")
    public String getSampleCollectionSource() {
        return sampleCollectionSource;
    }

    @JsonProperty("sampleCollectionSource")
    public void setSampleCollectionSource(String sampleCollectionSource) {
        this.sampleCollectionSource = sampleCollectionSource;
    }

    @JsonProperty("localID")
    public String getLocalID() {
        return localID;
    }

    @JsonProperty("localID")
    public void setLocalID(String localID) {
        this.localID = localID;
    }

    @JsonProperty("vipFile")
    public String getVipFile() {
        return vipFile;
    }

    @JsonProperty("vipFile")
    public void setVipFile(String vipFile) {
        this.vipFile = vipFile;
    }

    @JsonProperty("clinicalSite")
    public String getClinicalSite() {
        return clinicalSite;
    }

    @JsonProperty("clinicalSite")
    public void setClinicalSite(String clinicalSite) {
        this.clinicalSite = clinicalSite;
    }

    @JsonProperty("testName")
    public String getTestName() {
        return testName;
    }

    @JsonProperty("testName")
    public void setTestName(String testName) {
        this.testName = testName;
    }

    @JsonProperty("interrogatedButNotFound")
    public String getInterrogatedButNotFound() {
        return interrogatedButNotFound;
    }

    @JsonProperty("interrogatedButNotFound")
    public void setInterrogatedButNotFound(String interrogatedButNotFound) {
        this.interrogatedButNotFound = interrogatedButNotFound;
    }

    @JsonProperty("notInterpreted")
    public String getNotInterpreted() {
        return notInterpreted;
    }

    @JsonProperty("notInterpreted")
    public void setNotInterpreted(String notInterpreted) {
        this.notInterpreted = notInterpreted;
    }

    @JsonProperty("addendums")
    public List<List<String>> getAddendums() {
        if (addendums == null)
            addendums = new ArrayList<List<String>>();
        return addendums;
    }

    @JsonProperty("addendums")
    public void setAddendums(List<List<String>> addendums) {
        this.addendums = addendums;
    }

    @JsonProperty("age")
    public String getAge() {
        return age;
    }

    @JsonProperty("age")
    public void setAge(String age) {
        this.age = age;
    }

    @JsonProperty("race")
    public String getRace() {
        return race;
    }

    @JsonProperty("race")
    public void setRace(String race) {
        this.race = race;
    }

    @JsonProperty("ethnicity")
    public String getEthnicity() {
        return ethnicity;
    }

    @JsonProperty("ethnicity")
    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    @JsonProperty("sampleType")
    public String getSampleType() {
        return sampleType;
    }

    @JsonProperty("sampleType")
    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    @JsonProperty("patientID")
    public String getPatientID() {
        return patientID;
    }

    @JsonProperty("patientID")
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    @JsonProperty("sex")
    public String getSex() {
        return sex;
    }

    @JsonProperty("sex")
    public void setSex(String sex) {
        this.sex = sex;
    }

    @JsonProperty("diseaseStatus")
    public String getDiseaseStatus() {
        return diseaseStatus;
    }

    @JsonProperty("diseaseStatus")
    public void setDiseaseStatus(String diseaseStatus) {
        this.diseaseStatus = diseaseStatus;
    }

    @JsonProperty("diseases")
    public List<String> getDiseases() {
        if (diseases == null) {
            diseases = new ArrayList<String>();
        }
        return diseases;
    }

    @JsonProperty("diseases")
    public void setDiseases(List<String> diseases) {
        this.diseases = diseases;
    }

    @JsonProperty("deidentified")
    public String getDeidentified() {
        return deidentified;
    }

    @JsonProperty("deidentified")
    public void setDeidentified(String deidentified) {
        this.deidentified = deidentified;
    }

    @JsonProperty("specimenType")
    public String getSpecimenType() {
        return specimenType;
    }

    @JsonProperty("specimenType")
    public void setSpecimenType(String specimenType) {
        this.specimenType = specimenType;
    }

    @JsonProperty("sampleCollectedDate")
    public String getSampleCollectedDate() {
        return sampleCollectedDate;
    }

    @JsonProperty("sampleCollectedDate")
    public void setSampleCollectedDate(String sampleCollectedDate) {
        this.sampleCollectedDate = sampleCollectedDate;
    }

    @JsonProperty("sampleReceivedDate")
    public String getSampleReceivedDate() {
        return sampleReceivedDate;
    }

    @JsonProperty("sampleReceivedDate")
    public void setSampleReceivedDate(String sampleReceivedDate) {
        this.sampleReceivedDate = sampleReceivedDate;
    }

    @JsonProperty("dateOfBirth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonProperty("dateOfBirth")
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @JsonProperty("isPreliminary")
    public String getIsPreliminary() {
        return isPreliminary;
    }

    @JsonProperty("isPreliminary")
    public void setIsPreliminary(String isPreliminary) {
        this.isPreliminary = isPreliminary;
    }

    @JsonProperty("accessionNumber")
    public String getAccessionNumber() {
        return accessionNumber;
    }

    @JsonProperty("accessionNumber")
    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    @JsonProperty("variants")
    public List<Variant> getVariants() {
        if (variants == null)
            variants = new ArrayList<Variant>();
        return variants;
    }

    @JsonProperty("variants")
    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    @JsonProperty("cnvVariants")
    public List<CnvVariant> getCnvvariants() {
        if (cnvvariants == null)
            cnvvariants = new ArrayList<CnvVariant>();
        return cnvvariants;
    }

    @JsonProperty("cnvVariants")
    public void setCnvvariants(List<CnvVariant> cnvvariants) {
        this.cnvvariants = cnvvariants;
    }

    @JsonProperty("sangerSiteNote")
    public String getSangerSiteNote() {
        return sangerSiteNote;
    }

    @JsonProperty("sangerSiteNote")
    public void setSangerSiteNote(String sangerSiteNote) {
        this.sangerSiteNote = sangerSiteNote;
    }

    @JsonProperty("pgxData")
    public List<PgxDatum> getPgxData() {
        if (pgxData == null)
            pgxData = new ArrayList<PgxDatum>();
        return pgxData;
    }

    @JsonProperty("pgxData")
    public void setPgxData(List<PgxDatum> pgxData) {
    this.pgxData = pgxData;
    }


    @JsonProperty("orderingPhysicianName")
    public String getOrderingPhysicianName() {
        return orderingPhysicianName;
    }

    @JsonProperty("orderingPhysicianName")
    public void setOrderingPhysicianName(String orderingPhysicianName) {
        this.orderingPhysicianName = orderingPhysicianName;
    }

    @JsonProperty("patientFirstName")
    public String getPatientFirstName() {
        return patientFirstName;
    }

    @JsonProperty("patientFirstName")
    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }


    @JsonProperty("patientMiddleInitial")
    public String getPatientMiddleInitial() {
        return patientMiddleInitial;
    }

    @JsonProperty("patientMiddleInitial")
    public void setPatientMiddleInitial(String patientMiddleInitial) {
        this.patientMiddleInitial = patientMiddleInitial;
    }

    @JsonProperty("patientLastName")
    public String getPatientLastName() {
        return patientLastName;
    }

    @JsonProperty("patientLastName")
    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    @JsonProperty("reportComment")
    public String getReportComment() {
        return reportComment;
    }

    @JsonProperty("reportComment")
    public void setReportComment(String reportComment) {
        this.reportComment = reportComment;
    }

    @JsonProperty("methodology")
    public String getMethodology() { return methodology; }

    @JsonProperty("methodology")
    public void setMethodology(String methodology) { this.methodology = methodology; }

    @JsonProperty("background")
    public String getBackground() { return background; }

    @JsonProperty("background")
    public void setBackground(String background) { this.background = background; }
    
    @JsonProperty("clinicalCorrelation")
    public String getClinicalCorrelation() { return clinicalCorrelation; }

    @JsonProperty("clinicalCorrelation")
    public void setClinicalCorrelation(String clinicalCorrelation) { this.clinicalCorrelation = clinicalCorrelation; }

    @JsonProperty("pgxDescription")
    public String getPgxDescription() { return pgxDescription; }

    @JsonProperty("pgxDescription")
    public void setPgxDescription(String pgxDescription) { this.pgxDescription = pgxDescription; }

    @JsonProperty("cnvsFailed")
    public String getCnvsFailed() { return cnvsFailed; }

    @JsonProperty("cnvsFailed")
    public void setCnvsFailed(String cnvsFailed) { this.cnvsFailed = cnvsFailed; }
    
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
