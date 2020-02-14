package edu.bcm.hgsc.fhir.models;

import com.fasterxml.jackson.annotation.*;

import java.util.*;

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
        "cnvsFailed",
        "prsRecommendation",
        "comments",
        "siteMethodology",
        "panelSummary",
        "lpaFinding",
        "dnaVariationCode",
        "familyScreening",
        "references",
        "polygenicRiskScore",
        "lpaDisclaimer",
        "lpaRecommendationDetail",
        "cnvsFailedText",
        "genomicCoordinateSystem",
        "lpaRecommendation",
        "testInfoSummary",
        "testInfoDetail",
        "panelRecommendation",
        "jsonCreatedDate",
        "humanReferenceSequenceAssemblyVersion",
        "testCode",
        "prsDisclaimer",
        "pgxDisclaimer",
        "overallInterpretationText",
        "geneCoverageText",
        "testParams",
        "lpaSummary",
        "prsFinding",
        "panelManagementRecommendation",
        "language",
        "pgxSummaryRecommendation",
        "footer",
        "prsSummary",
        "prsRecommendationDetail",
        "pgxFinding",
        "vusDisclaimer",
        "panelFinding",
        "panelRecommendationDetail",
        "practitionerData",
        "patientName",
        "familyID",
        "noReportableVariantsText",
        "phenotypeTerms",
        "concentation",
        "familyRelationship",
        "totalDNA",
        "barcode",
        "clinicalNotes",
        "rackLocation",
        "orderingPhysicianAddress",
        "neptuneVersion",
        "initialVolume",
        "orderingPhysicianNPI"
})
public class HgscReport {

    @JsonProperty("reportDate")
    private String reportDate;
    @JsonProperty("indicationForTesting")
    private String indicationForTesting;
    @JsonProperty("genomicSource")
    private String genomicSource;
    @JsonProperty("geneCoverage")
    private List<SingleGeneCoverage> geneCoverage;
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
    private List<AddEndUm> addendums = null;
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
    private List<String> methodology;
    @JsonProperty("background")
    private String background;
    @JsonProperty("clinicalCorrelation")
    private String clinicalCorrelation;
    @JsonProperty("pgxDescription")
    private String pgxDescription;
    @JsonProperty("cnvsFailed")
    private String cnvsFailed;
    @JsonProperty("prsRecommendation")
    private String prsRecommendation;
    @JsonProperty("comments")
    private String comments;
    @JsonProperty("siteMethodology")
    private List<String> siteMethodology;
    @JsonProperty("panelSummary")
    private String panelSummary;
    @JsonProperty("lpaFinding")
    private String lpaFinding;
    @JsonProperty("dnaVariationCode")
    private String dnaVariationCode;
    @JsonProperty("familyScreening")
    private String familyScreening;
    @JsonProperty("references")
    private List<String> references;
    @JsonProperty("polygenicRiskScore")
    private String polygenicRiskScore;
    @JsonProperty("lpaDisclaimer")
    private String lpaDisclaimer;
    @JsonProperty("lpaRecommendationDetail")
    private String lpaRecommendationDetail;
    @JsonProperty("cnvsFailedText")
    private String cnvsFailedText;
    @JsonProperty("genomicCoordinateSystem")
    private String genomicCoordinateSystem;
    @JsonProperty("lpaRecommendation")
    private String lpaRecommendation;
    @JsonProperty("testInfoSummary")
    private String testInfoSummary;
    @JsonProperty("testInfoDetail")
    private String testInfoDetail;
    @JsonProperty("panelRecommendation")
    private String panelRecommendation;
    @JsonProperty("jsonCreatedDate")
    private String jsonCreatedDate;
    @JsonProperty("humanReferenceSequenceAssemblyVersion")
    private String humanReferenceSequenceAssemblyVersion;
    @JsonProperty("testCode")
    private String testCode;
    @JsonProperty("prsDisclaimer")
    private String prsDisclaimer;
    @JsonProperty("pgxDisclaimer")
    private String pgxDisclaimer;
    @JsonProperty("overallInterpretationText")
    private String overallInterpretationText;
    @JsonProperty("geneCoverageText")
    private String geneCoverageText;
    @JsonProperty("testParams")
    private String testParams;
    @JsonProperty("lpaSummary")
    private String lpaSummary;
    @JsonProperty("prsFinding")
    private String prsFinding;
    @JsonProperty("panelManagementRecommendation")
    private String panelManagementRecommendation;
    @JsonProperty("language")
    private String language;
    @JsonProperty("pgxSummaryRecommendation")
    private List<String> pgxSummaryRecommendation;
    @JsonProperty("footer")
    private String footer;
    @JsonProperty("prsSummary")
    private String prsSummary;
    @JsonProperty("prsRecommendationDetail")
    private String prsRecommendationDetail;
    @JsonProperty("pgxFinding")
    private String pgxFinding;
    @JsonProperty("vusDisclaimer")
    private String vusDisclaimer;
    @JsonProperty("panelFinding")
    private String panelFinding;
    @JsonProperty("panelRecommendationDetail")
    private String panelRecommendationDetail;
    @JsonProperty("practitionerData")
    private List<PractitionerDatum> practitionerData;
    @JsonProperty("patientName")
    private String patientName;
    @JsonProperty("familyID")
    private String familyID;
    @JsonProperty("noReportableVariantsText")
    private String noReportableVariantsText;
    @JsonProperty("phenotypeTerms")
    private String phenotypeTerms;
    @JsonProperty("concentation")
    private String concentation;
    @JsonProperty("familyRelationship")
    private String familyRelationship;
    @JsonProperty("totalDNA")
    private String totalDNA;
    @JsonProperty("barcode")
    private String barcode;
    @JsonProperty("clinicalNotes")
    private String clinicalNotes;
    @JsonProperty("rackLocation")
    private String rackLocation;
    @JsonProperty("orderingPhysicianAddress")
    private String orderingPhysicianAddress;
    @JsonProperty("neptuneVersion")
    private String neptuneVersion;
    @JsonProperty("initialVolume")
    private String initialVolume;
    @JsonProperty("orderingPhysicianNPI")
    private String orderingPhysicianNPI;

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
    public List<SingleGeneCoverage> getGeneCoverage() {
        if (geneCoverage == null) {
            geneCoverage = new ArrayList<SingleGeneCoverage>();
        }
        return geneCoverage;
    }

    @JsonProperty("geneCoverage")
    public void setGeneCoverage(List<SingleGeneCoverage> geneCoverage) {
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
    public List<AddEndUm> getAddendums() {
        if (addendums == null)
            addendums = new ArrayList<AddEndUm>();
        return addendums;
    }

    @JsonProperty("addendums")
    public void setAddendums(List<AddEndUm> addendums) {
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
    public List<String> getMethodology() {
        return methodology;
    }

    @JsonProperty("methodology")
    public void setMethodology(List<String> methodology) {
        this.methodology = methodology;
    }

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

    @JsonProperty("prsRecommendation")
    public String getPrsRecommendation() {
        return prsRecommendation;
    }

    @JsonProperty("prsRecommendation")
    public void setPrsRecommendation(String prsRecommendation) {
        this.prsRecommendation = prsRecommendation;
    }

    @JsonProperty("comments")
    public String getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(String comments) {
        this.comments = comments;
    }

    @JsonProperty("siteMethodology")
    public List<String> getSiteMethodology() {
        return siteMethodology;
    }

    @JsonProperty("siteMethodology")
    public void setSiteMethodology(List<String> siteMethodology) {
        this.siteMethodology = siteMethodology;
    }

    @JsonProperty("panelSummary")
    public String getPanelSummary() {
        return panelSummary;
    }

    @JsonProperty("panelSummary")
    public void setPanelSummary(String panelSummary) {
        this.panelSummary = panelSummary;
    }

    @JsonProperty("lpaFinding")
    public String getLpaFinding() {
        return lpaFinding;
    }

    @JsonProperty("lpaFinding")
    public void setLpaFinding(String lpaFinding) {
        this.lpaFinding = lpaFinding;
    }

    @JsonProperty("dnaVariationCode")
    public String getDnaVariationCode() {
        return dnaVariationCode;
    }

    @JsonProperty("dnaVariationCode")
    public void setDnaVariationCode(String dnaVariationCode) {
        this.dnaVariationCode = dnaVariationCode;
    }

    @JsonProperty("familyScreening")
    public String getFamilyScreening() {
        return familyScreening;
    }

    @JsonProperty("familyScreening")
    public void setFamilyScreening(String familyScreening) {
        this.familyScreening = familyScreening;
    }

    @JsonProperty("references")
    public List<String> getReferences() {
        return references;
    }

    @JsonProperty("references")
    public void setReferences(List<String> references) {
        this.references = references;
    }

    @JsonProperty("polygenicRiskScore")
    public String getPolygenicRiskScore() {
        return polygenicRiskScore;
    }

    @JsonProperty("polygenicRiskScore")
    public void setPolygenicRiskScore(String polygenicRiskScore) {
        this.polygenicRiskScore = polygenicRiskScore;
    }

    @JsonProperty("lpaDisclaimer")
    public String getLpaDisclaimer() {
        return lpaDisclaimer;
    }

    @JsonProperty("lpaDisclaimer")
    public void setLpaDisclaimer(String lpaDisclaimer) {
        this.lpaDisclaimer = lpaDisclaimer;
    }

    @JsonProperty("lpaRecommendationDetail")
    public String getLpaRecommendationDetail() {
        return lpaRecommendationDetail;
    }

    @JsonProperty("lpaRecommendationDetail")
    public void setLpaRecommendationDetail(String lpaRecommendationDetail) {
        this.lpaRecommendationDetail = lpaRecommendationDetail;
    }

    @JsonProperty("cnvsFailedText")
    public String getCnvsFailedText() {
        return cnvsFailedText;
    }

    @JsonProperty("cnvsFailedText")
    public void setCnvsFailedText(String cnvsFailedText) {
        this.cnvsFailedText = cnvsFailedText;
    }

    @JsonProperty("genomicCoordinateSystem")
    public String getGenomicCoordinateSystem() {
        return genomicCoordinateSystem;
    }

    @JsonProperty("genomicCoordinateSystem")
    public void setGenomicCoordinateSystem(String genomicCoordinateSystem) {
        this.genomicCoordinateSystem = genomicCoordinateSystem;
    }

    @JsonProperty("lpaRecommendation")
    public String getLpaRecommendation() {
        return lpaRecommendation;
    }

    @JsonProperty("lpaRecommendation")
    public void setLpaRecommendation(String lpaRecommendation) {
        this.lpaRecommendation = lpaRecommendation;
    }

    @JsonProperty("testInfoSummary")
    public String getTestInfoSummary() {
        return testInfoSummary;
    }

    @JsonProperty("testInfoSummary")
    public void setTestInfoSummary(String testInfoSummary) {
        this.testInfoSummary = testInfoSummary;
    }

    @JsonProperty("testInfoDetail")
    public String getTestInfoDetail() {
        return testInfoDetail;
    }

    @JsonProperty("testInfoDetail")
    public void setTestInfoDetail(String testInfoDetail) {
        this.testInfoDetail = testInfoDetail;
    }

    @JsonProperty("panelRecommendation")
    public String getPanelRecommendation() {
        return panelRecommendation;
    }

    @JsonProperty("panelRecommendation")
    public void setPanelRecommendation(String panelRecommendation) {
        this.panelRecommendation = panelRecommendation;
    }

    @JsonProperty("jsonCreatedDate")
    public String getJsonCreatedDate() {
        return jsonCreatedDate;
    }

    @JsonProperty("jsonCreatedDate")
    public void setJsonCreatedDate(String jsonCreatedDate) {
        this.jsonCreatedDate = jsonCreatedDate;
    }

    @JsonProperty("humanReferenceSequenceAssemblyVersion")
    public String getHumanReferenceSequenceAssemblyVersion() {
        return humanReferenceSequenceAssemblyVersion;
    }

    @JsonProperty("humanReferenceSequenceAssemblyVersion")
    public void setHumanReferenceSequenceAssemblyVersion(String humanReferenceSequenceAssemblyVersion) {
        this.humanReferenceSequenceAssemblyVersion = humanReferenceSequenceAssemblyVersion;
    }

    @JsonProperty("testCode")
    public String getTestCode() {
        return testCode;
    }

    @JsonProperty("testCode")
    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    @JsonProperty("prsDisclaimer")
    public String getPrsDisclaimer() {
        return prsDisclaimer;
    }

    @JsonProperty("prsDisclaimer")
    public void setPrsDisclaimer(String prsDisclaimer) {
        this.prsDisclaimer = prsDisclaimer;
    }

    @JsonProperty("pgxDisclaimer")
    public String getPgxDisclaimer() {
        return pgxDisclaimer;
    }

    @JsonProperty("pgxDisclaimer")
    public void setPgxDisclaimer(String pgxDisclaimer) {
        this.pgxDisclaimer = pgxDisclaimer;
    }

    @JsonProperty("overallInterpretationText")
    public String getOverallInterpretationText() {
        return overallInterpretationText;
    }

    @JsonProperty("overallInterpretationText")
    public void setOverallInterpretationText(String overallInterpretationText) {
        this.overallInterpretationText = overallInterpretationText;
    }

    @JsonProperty("geneCoverageText")
    public String getGeneCoverageText() {
        return geneCoverageText;
    }

    @JsonProperty("geneCoverageText")
    public void setGeneCoverageText(String geneCoverageText) {
        this.geneCoverageText = geneCoverageText;
    }

    @JsonProperty("testParams")
    public String getTestParams() {
        return testParams;
    }

    @JsonProperty("testParams")
    public void setTestParams(String testParams) {
        this.testParams = testParams;
    }

    @JsonProperty("lpaSummary")
    public String getLpaSummary() {
        return lpaSummary;
    }

    @JsonProperty("lpaSummary")
    public void setLpaSummary(String lpaSummary) {
        this.lpaSummary = lpaSummary;
    }

    @JsonProperty("prsFinding")
    public String getPrsFinding() {
        return prsFinding;
    }

    @JsonProperty("prsFinding")
    public void setPrsFinding(String prsFinding) {
        this.prsFinding = prsFinding;
    }

    @JsonProperty("panelManagementRecommendation")
    public String getPanelManagementRecommendation() {
        return panelManagementRecommendation;
    }

    @JsonProperty("panelManagementRecommendation")
    public void setPanelManagementRecommendation(String panelManagementRecommendation) {
        this.panelManagementRecommendation = panelManagementRecommendation;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("pgxSummaryRecommendation")
    public List<String> getPgxSummaryRecommendation() {
        return pgxSummaryRecommendation;
    }

    @JsonProperty("pgxSummaryRecommendation")
    public void setPgxSummaryRecommendation(List<String> pgxSummaryRecommendation) {
        this.pgxSummaryRecommendation = pgxSummaryRecommendation;
    }

    @JsonProperty("footer")
    public String getFooter() {
        return footer;
    }

    @JsonProperty("footer")
    public void setFooter(String footer) {
        this.footer = footer;
    }

    @JsonProperty("prsSummary")
    public String getPrsSummary() {
        return prsSummary;
    }

    @JsonProperty("prsSummary")
    public void setPrsSummary(String prsSummary) {
        this.prsSummary = prsSummary;
    }

    @JsonProperty("prsRecommendationDetail")
    public String getPrsRecommendationDetail() {
        return prsRecommendationDetail;
    }

    @JsonProperty("prsRecommendationDetail")
    public void setPrsRecommendationDetail(String prsRecommendationDetail) {
        this.prsRecommendationDetail = prsRecommendationDetail;
    }

    @JsonProperty("pgxFinding")
    public String getPgxFinding() {
        return pgxFinding;
    }

    @JsonProperty("pgxFinding")
    public void setPgxFinding(String pgxFinding) {
        this.pgxFinding = pgxFinding;
    }

    @JsonProperty("vusDisclaimer")
    public String getVusDisclaimer() {
        return vusDisclaimer;
    }

    @JsonProperty("vusDisclaimer")
    public void setVusDisclaimer(String vusDisclaimer) {
        this.vusDisclaimer = vusDisclaimer;
    }

    @JsonProperty("panelFinding")
    public String getPanelFinding() {
        return panelFinding;
    }

    @JsonProperty("panelFinding")
    public void setPanelFinding(String panelFinding) {
        this.panelFinding = panelFinding;
    }

    @JsonProperty("panelRecommendationDetail")
    public String getPanelRecommendationDetail() {
        return panelRecommendationDetail;
    }

    @JsonProperty("panelRecommendationDetail")
    public void setPanelRecommendationDetail(String panelRecommendationDetail) {
        this.panelRecommendationDetail = panelRecommendationDetail;
    }

    @JsonProperty("practitionerData")
    public List<PractitionerDatum> getPractitionerData() {
        return practitionerData;
    }

    @JsonProperty("practitionerData")
    public void setPractitionerData(List<PractitionerDatum> practitionerData) {
        this.practitionerData = practitionerData;
    }

    @JsonProperty("patientName")
    public String getPatientName() {
        return patientName;
    }

    @JsonProperty("patientName")
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @JsonProperty("familyID")
    public String getFamilyID() {
        return familyID;
    }

    @JsonProperty("familyID")
    public void setFamilyID(String familyID) {
        this.familyID = familyID;
    }

    @JsonProperty("noReportableVariantsText")
    public String getNoReportableVariantsText() {
        return noReportableVariantsText;
    }

    @JsonProperty("noReportableVariantsText")
    public void setNoReportableVariantsText(String noReportableVariantsText) {
        this.noReportableVariantsText = noReportableVariantsText;
    }

    @JsonProperty("phenotypeTerms")
    public String getPhenotypeTerms() {
        return phenotypeTerms;
    }

    @JsonProperty("phenotypeTerms")
    public void setPhenotypeTerms(String phenotypeTerms) {
        this.phenotypeTerms = phenotypeTerms;
    }

    @JsonProperty("concentation")
    public String getConcentation() {
        return concentation;
    }

    @JsonProperty("concentation")
    public void setConcentation(String concentation) {
        this.concentation = concentation;
    }

    @JsonProperty("familyRelationship")
    public String getFamilyRelationship() {
        return familyRelationship;
    }

    @JsonProperty("familyRelationship")
    public void setFamilyRelationship(String familyRelationship) {
        this.familyRelationship = familyRelationship;
    }

    @JsonProperty("totalDNA")
    public String getTotalDNA() {
        return totalDNA;
    }

    @JsonProperty("totalDNA")
    public void setTotalDNA(String totalDNA) {
        this.totalDNA = totalDNA;
    }

    @JsonProperty("barcode")
    public String getBarcode() {
        return barcode;
    }

    @JsonProperty("barcode")
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @JsonProperty("clinicalNotes")
    public String getClinicalNotes() {
        return clinicalNotes;
    }

    @JsonProperty("clinicalNotes")
    public void setClinicalNotes(String clinicalNotes) {
        this.clinicalNotes = clinicalNotes;
    }

    @JsonProperty("rackLocation")
    public String getRackLocation() {
        return rackLocation;
    }

    @JsonProperty("rackLocation")
    public void setRackLocation(String rackLocation) {
        this.rackLocation = rackLocation;
    }

    @JsonProperty("orderingPhysicianAddress")
    public String getOrderingPhysicianAddress() {
        return orderingPhysicianAddress;
    }

    @JsonProperty("orderingPhysicianAddress")
    public void setOrderingPhysicianAddress(String orderingPhysicianAddress) {
        this.orderingPhysicianAddress = orderingPhysicianAddress;
    }

    @JsonProperty("neptuneVersion")
    public String getNeptuneVersion() {
        return neptuneVersion;
    }

    @JsonProperty("neptuneVersion")
    public void setNeptuneVersion(String neptuneVersion) {
        this.neptuneVersion = neptuneVersion;
    }

    @JsonProperty("initialVolume")
    public String getInitialVolume() {
        return initialVolume;
    }

    @JsonProperty("initialVolume")
    public void setInitialVolume(String initialVolume) {
        this.initialVolume = initialVolume;
    }

    @JsonProperty("orderingPhysicianNPI")
    public String getOrderingPhysicianNPI() {
        return orderingPhysicianNPI;
    }

    @JsonProperty("orderingPhysicianNPI")
    public void setOrderingPhysicianNPI(String orderingPhysicianNPI) {
        this.orderingPhysicianNPI = orderingPhysicianNPI;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
