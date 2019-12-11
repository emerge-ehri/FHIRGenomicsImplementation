package edu.bcm.hgsc.fhir.models;

import com.fasterxml.jackson.annotation.*;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by sl9 on 10/6/17.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "interpretation",
        "ref",
        "var",
        "notes",
        "proteinChange",
        "diseases",
        "geneDiseaseText",
        "variantCuration",
        "chromosome",
        "position",
        "genomic",
        "zygosity",
        "alt",
        "gene",
        "transcript",
        "confirmedBySanger",
        "cDNA",
        "inheritance",
        "dnaChange",
        "externalId",
        "categoryType",
        "genotype",
        "alleleFraction",
        "notInterpreted",
        "interrogatedButNotFound",
        "variantInterpretation",
        "endPosition",
        "dbSNPID",
        "hgncID",
        "type",
        "reportable",
        "isIncidentalFinding"
})
public class Variant {

    public static final List<String> requiredFields = Collections.unmodifiableList(
        new ArrayList<String>() {{
            add("transcript");
            add("dnaChange");
            add("proteinChange");
            add("chromosome");
            add("diseases");
            add("gene");
            add("notes");
            add("zygosity");
            add("interpretation");
            add("confirmedBySanger");
            add("cDNA");
            add("genomic");
            add("ref");
            add("alt");
            add("interpretation");
            add("inheritance");
            add("variantCuration");
            add("externalId");
            add("categoryType");
        }});

    public static final List<String> booleanFields = Collections.unmodifiableList(
        new ArrayList<String>() {{
            add("confirmedBySanger");
            add("notInterpreted");
            add("interrogatedButNotFound");
        }});

    private final static String INHERITANCE_FIELD_NAME = "inheritance";
    private final static String INHERITANCE_AUTO_DOMINAT = "Autosomal dominant";
    private final static String INHERITANCE_AUTO_RECESSIVE = "Autosomal recessive";
    private final static String INHERITANCE_MITO = "Mitochondrial";
    private final static String INHERITANCE_X_LINKED = "X-linked";
    private final static String INHERITANCE_SOMATIC = "Somatic";
    private final static String INHERITANCE_UNKNOWN = "Unknown";
    private final static String INHERITANCE_OTHER = "Other";

    // Value for interpretation (JSON), category (XML)
    private static final String INTERPRETATION_FILED_NAME = "interpretation";
    private static final String CATEGORY_BENIGN = "Benign";
    private static final String CATEGORY_LIKELY_BENIGN = "Likely Benign";
    private static final String CATEGORY_UNCERTAIN_SIGNIFICANCE = "Uncertain Significance";
    private static final String CATEGORY_PATHOGENIC = "Pathogenic";
    private static final String CATEGORY_LIKELY_PATHOGENIC = "Likely Pathogenic";
    private static final String CATEGORY_RISK_FACTOR = "Risk Factor";


    // Value for zygosity (JSON), allelic state (XML)
    private static final String ZYGOSITY_FILED_NAME = "zygosity";
    private static final String ALLELIC_STATE_HETEROPLASMIC = "Heteroplasmic";
    private static final String ALLELIC_STATE_HOMOPLASMIC = "Homoplasmic";
    private static final String ALLELIC_STATE_HOMOZYGOUS = "Homozygous";
    private static final String ALLELIC_STATE_HETEROZYGOUS = "Heterozygous";
    private static final String ALLELIC_STATE_HEMIZYGOUS = "Hemizygous";
    private static final String ALLELIC_STATE_MOSAIC = "Mosaic";

    // Value for diseases (JSON), diseaseCodes (XML)
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


    // Field : [Values]
    private static final Map<String, String[]> controlledVocabMap = Collections.unmodifiableMap(
            new HashMap<String, String[]>() {{
                put(INTERPRETATION_FILED_NAME, new String[]{CATEGORY_BENIGN
                        , CATEGORY_LIKELY_BENIGN
                        , CATEGORY_UNCERTAIN_SIGNIFICANCE
                        , CATEGORY_PATHOGENIC
                          , CATEGORY_LIKELY_PATHOGENIC
                        , CATEGORY_RISK_FACTOR});

                put(ZYGOSITY_FILED_NAME, new String[]{ALLELIC_STATE_HETEROPLASMIC
                        , ALLELIC_STATE_HOMOPLASMIC
                        , ALLELIC_STATE_HETEROZYGOUS
                        , ALLELIC_STATE_HOMOZYGOUS
                        , ALLELIC_STATE_MOSAIC
                        , ALLELIC_STATE_HEMIZYGOUS});

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
            }});

    @JsonProperty("interpretation")
    private String interpretation;
    @JsonProperty("ref")
    private String ref;
    @JsonProperty("var")
    private String var;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("diseases")
    private List<Disease> diseases;
    @JsonProperty("proteinChange")
    private String proteinChange;
    @JsonProperty("geneDiseaseText")
    private String geneDiseaseText;
    @JsonProperty("variantCuration")
    private String variantCuration;
    @JsonProperty("chromosome")
    private String chromosome;
    @JsonProperty("position")
    private String position;
    @JsonProperty("genomic")
    private String genomic;
    @JsonProperty("zygosity")
    private String zygosity;
    @JsonProperty("alt")
    private String alt;
    @JsonProperty("gene")
    private String gene;
    @JsonProperty("transcript")
    private String transcript;
    @JsonProperty("confirmedBySanger")
    private String confirmedBySanger;
    @JsonProperty("cDNA")
    private String cDNA;
    @JsonProperty("inheritance")
    private String inheritance;
    @JsonProperty("dnaChange")
    private String dnaChange;
    @JsonProperty("externalId")
    private String externalId;
    @JsonProperty("categoryType")
    private String genotype;
    @JsonProperty("genotype")
    private String categoryType;
    @JsonProperty("alleleFraction")
    private String alleleFraction;
    @JsonProperty("notInterpreted")
    private String notInterpreted;
    @JsonProperty("interrogatedButNotFound")
    private String interrogatedButNotFound;
    @JsonProperty("variantInterpretation")
    private String variantInterpretation;
    @JsonProperty("endPosition")
    private String endPosition;

    @JsonProperty("dbSNPID")
    private String dbSNPID;
    @JsonProperty("hgncID")
    private String hgncID;
    @JsonProperty("type")
    private String type;
    @JsonProperty("reportable")
    private String reportable;
    @JsonProperty("isIncidentalFinding")
    private String isIncidentalFinding;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("interpretation")
    public String getInterpretation() {
        return interpretation;
    }

    @JsonProperty("interpretation")
    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }

    @JsonProperty("ref")
    public String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(String ref) {
        this.ref = ref;
    }

    @JsonProperty("var")
    public String getVar() {
        return var;
    }

    @JsonProperty("var")
    public void setVar(String var) {
        this.var = var;
    }

    @JsonProperty("notes")
    public String getNotes() {
        return notes;
    }

    @JsonProperty("notes")
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @JsonProperty("proteinChange")
    public String getProteinChange() {
        return proteinChange;
    }

    @JsonProperty("proteinChange")
    public void setProteinChange(String proteinChange) {
        this.proteinChange = proteinChange;
    }

    @JsonProperty("diseases")
    public List<Disease> getDiseases() {
        if (diseases == null) {
            diseases = new ArrayList<Disease>();
        }
        return diseases;
    }

    @JsonProperty("diseases")
    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    @JsonProperty("geneDiseaseText")
    public String getGeneDiseaseText() {
        return geneDiseaseText;
    }

    @JsonProperty("geneDiseaseText")
    public void setGeneDiseaseText(String geneDiseaseText) {
        this.geneDiseaseText = geneDiseaseText;
    }

    @JsonProperty("variantCuration")
    public String getVariantCuration() {
        return variantCuration;
    }

    @JsonProperty("variantCuration")
    public void setVariantCuration(String variantCuration) {
        this.variantCuration = variantCuration;
    }

    @JsonProperty("chromosome")
    public String getChromosome() {
        return chromosome;
    }

    @JsonProperty("chromosome")
    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    @JsonProperty("position")
    public String getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(String position) {
        this.position = position;
    }

    @JsonProperty("genomic")
    public String getGenomic() {
        return genomic;
    }

    @JsonProperty("genomic")
    public void setGenomic(String genomic) {
        this.genomic = genomic;
    }

    @JsonProperty("zygosity")
    public String getZygosity() {
        return zygosity;
    }

    @JsonProperty("zygosity")
    public void setZygosity(String zygosity) {
        this.zygosity = zygosity;
    }

    @JsonProperty("alt")
    public String getAlt() {
        return alt;
    }

    @JsonProperty("alt")
    public void setAlt(String alt) {
        this.alt = alt;
    }

    @JsonProperty("gene")
    public String getGene() {
        return gene;
    }

    @JsonProperty("gene")
    public void setGene(String gene) {
        this.gene = gene;
    }

    @JsonProperty("transcript")
    public String getTranscript() {
        return transcript;
    }

    @JsonProperty("transcript")
    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    @JsonProperty("confirmedBySanger")
    public String getConfirmedBySanger() {
        return confirmedBySanger;
    }

    @JsonProperty("confirmedBySanger")
    public void setConfirmedBySanger(String confirmedBySanger) {
        this.confirmedBySanger = confirmedBySanger;
    }

    @JsonProperty("cDNA")
    public String getCDNA() {
        return cDNA;
    }

    @JsonProperty("cDNA")
    public void setCDNA(String cDNA) {
        this.cDNA = cDNA;
    }

    @JsonProperty("inheritance")
    public String getInheritance() { return inheritance; }

    @JsonProperty("inheiritance")
    public void setInheritance(String inheritance) {
        this.inheritance = inheritance;
    }

    @JsonProperty("dnaChange")
    public String getDnaChange() { return dnaChange; }

    @JsonProperty("dnaChange")
    public void setDnaChange(String dnaChange) { this.dnaChange = dnaChange; }

    @JsonProperty("externalId")
    public String getExternalId() { return externalId; }

    @JsonProperty("externalId")
    public void setExternalId(String externalId) { this.externalId = externalId; }

    @JsonProperty("categoryType")
    public String getCategoryType() { return categoryType; }

    @JsonProperty("categoryType")
    public void setCategoryType(String categoryType) { this.categoryType = categoryType; }

    @JsonProperty("genotype")
    public String getGenotype() { return genotype; }

    @JsonProperty("genotype")
    public void setGenotype(String genotype) { this.genotype = genotype; }

    @JsonProperty("alleleFraction")
    public String getAlleleFraction() { return alleleFraction; }

    @JsonProperty("alleleFraction")
    public void setAlleleFraction(String alleleFraction) { this.alleleFraction = alleleFraction; }

    @JsonProperty("notInterpreted")
    public String getNotInterpreted() { return notInterpreted; }

    @JsonProperty("notInterpreted")
    public void setNotInterpreted(String notInterpreted) { this.notInterpreted = notInterpreted; }

    @JsonProperty("interrogatedButNotFound")
    public String getInterrogatedButNotFound() { return interrogatedButNotFound; }

    @JsonProperty("interrogatedButNotFound")
    public void setInterrogatedButNotFound(String interrogatedButNotFound) { this.interrogatedButNotFound = interrogatedButNotFound; }

    @JsonProperty("variantInterpretation")
    public String getVariantInterpretation() { return variantInterpretation; }

    @JsonProperty("variantInterpretation")
    public void setVariantInterpretation(String variantInterpretation) { this.variantInterpretation = variantInterpretation; }

    @JsonProperty("endPosition")
    public String getEndPosition() { return endPosition; }

    @JsonProperty("endPosition")
    public void setEndPosition(String endPosition) { this.endPosition = endPosition; }

    @JsonProperty("dbSNPID")
    public String getDbSNPID() {
        return dbSNPID;
    }

    @JsonProperty("dbSNPID")
    public void setDbSNPID(String dbSNPID) {
        this.dbSNPID = dbSNPID;
    }

    @JsonProperty("hgncID")
    public String getHgncID() {
        return hgncID;
    }

    @JsonProperty("hgncID")
    public void setHgncID(String hgncID) {
        this.hgncID = hgncID;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("reportable")
    public String getReportable() {
        return reportable;
    }

    @JsonProperty("reportable")
    public void setReportable(String reportable) {
        this.reportable = reportable;
    }

    @JsonProperty("isIncidentalFinding")
    public String getIsIncidentalFinding() {
        return isIncidentalFinding;
    }

    @JsonProperty("isIncidentalFinding")
    public void setIsIncidentalFinding(String isIncidentalFinding) {
        this.isIncidentalFinding = isIncidentalFinding;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    /**
     * Validate Required Elements in Json file
     * @return Missing elements
     * @throws IllegalAccessException
     */
    public List<String> validateRequiredElements(){
        List<String> nullFields = new ArrayList<String>();
        try {
            for (Field eachField : getClass().getDeclaredFields()) {
                if (eachField.get(this) == null && requiredFields.contains(eachField.getName()))
                    nullFields.add("json.variant." + eachField.getName());
            }
        } catch(Exception ex) {
            System.err.println(getClass().getName());
            ex.printStackTrace();
        }
        return nullFields;
    }


    /**
     * Validate Boolean Elements in Json file
     * @return invalid boolean elements
     * @throws IllegalAccessException
     */
    public List<String> valiateBooleanField(){
        List<String> invalidBooleanFields = new ArrayList<String>();
        try {
            for (String fieldName : booleanFields) {
                String value = (getClass().getDeclaredField(fieldName)).get(this).toString();
                if (!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false")) {
                    invalidBooleanFields.add("json.variant." + fieldName + ": " + value);
                }
            }
        } catch(Exception ex) {
            System.err.println(getClass().getName());
            ex.printStackTrace();
        }
        return invalidBooleanFields;
    }

    /**
     * Validate Controlled Vocabulary fileds in Json file
     * Only Code Type is validated so far
     * @return List[Field:Value]
     */
    public List<String> validateControlledVocabulary() {
        List<String> invalidValueFields = new ArrayList<String>();
        try {
            Set<Map.Entry<String, String[]>> entrySet = controlledVocabMap.entrySet();
            for (Map.Entry entry : entrySet) {
                String fieldName = (String)entry.getKey();
                Object value = (getClass().getDeclaredField(fieldName)).get(this);
                if (value instanceof List) {
                    for(String eachValue : (List<String>)value) {
                        if(!Arrays.asList((String[])entry.getValue()).contains(eachValue))
                            invalidValueFields.add("json.variants." + fieldName + ":" + value);
                    }
                }
                else if (value instanceof String && !Arrays.asList((String[])entry.getValue()).contains((String)value)) {
                    invalidValueFields.add("json.variants." + fieldName + ":" + value);
                }
            }
        } catch (Exception ex) {
            System.err.println(getClass().getName());
            ex.printStackTrace();
        }
        return invalidValueFields;
    }
}
