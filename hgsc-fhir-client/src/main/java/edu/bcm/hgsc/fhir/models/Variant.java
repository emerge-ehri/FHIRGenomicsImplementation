package edu.bcm.hgsc.fhir.models;

import com.fasterxml.jackson.annotation.*;
import java.util.*;

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
}
