package com.hgsc.fhir.models;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sl9 on 10/6/17.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "externalId",
        "transcript",
        "exonQCValue",
        "rpkm",
        "zygosity",
        "endPosition",
        "interrogatedButNotFound",
        "diseases",
        "medianRPKM",
        "confirmedBySanger",
        "geneDiseaseText",
        "variantCuration",
        "confirmedByMLPA",
        "notInterpreted",
        "inheritance",
        "categoryType",
        "exonQCPF",
        "log2R",
        "position",
        "gene",
        "type",
        "geneRegion",
        "chromosome",
        "var",
        "alt",
        "ref",
        "interpretation",
        "variantInterpretation",
        "cDNA",
        "dnaChange",
        "genomic",
        "proteinChange",
        "genotype",
        "notes",
        "alleleFraction"
        
})

public class CnvVariant {
    @JsonProperty("externalId")
    private String externalId;
    @JsonProperty("transcript")
    private String transcript;
    @JsonProperty("exonQCValue")
    private String exonQCValue;
    @JsonProperty("rpkm")
    private String rpkm;
    @JsonProperty("zygosity")
    private String zygosity;
    @JsonProperty("endPosition")
    private String endPosition;
    @JsonProperty("diseases")
    private List<String> diseases;
    @JsonProperty("interrogatedButNotFound")
    private String interrogatedButNotFound;
    @JsonProperty("medianRPKM")
    private String medianRPKM;
    @JsonProperty("confirmedBySanger")
    private String confirmedBySanger;
    @JsonProperty("geneDiseaseText")
    private String geneDiseaseText;
    @JsonProperty("variantCuration")
    private String variantCuration;
    @JsonProperty("confirmedByMLPA")
    private String confirmedByMLPA;
    @JsonProperty("notInterpreted")
    private String notInterpreted;
    @JsonProperty("inheritance")
    private String inheritance;
    @JsonProperty("categoryType")
    private String categoryType;
    @JsonProperty("exonQCPF")
    private String exonQCPF;
    @JsonProperty("log2R")
    private String log2R;
    @JsonProperty("position")
    private String position;
    @JsonProperty("gene")
    private String gene;
    @JsonProperty("type")
    private String type;
    @JsonProperty("geneRegion")
    private String geneRegion;
    @JsonProperty("chromosome")
    private String chromosome;
    @JsonProperty("var")
    private String var;
    @JsonProperty("alt")
    private String alt;
    @JsonProperty("ref")
    private String ref;
    @JsonProperty("interpretation")
    private String interpretation;
    @JsonProperty("variantInterpretation")
    private String variantInterpretation;
    @JsonProperty("cDNA")
    private String cDNA;
    @JsonProperty("genomic")
    private String genomic;
    @JsonProperty("dnaChange")
    private String dnaChange;
    @JsonProperty("proteinChange")
    private String proteinChange;
    @JsonProperty("genotype")
    private String genotype;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("alleleFraction")
    private String alleleFraction;
    

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("externalId")
    public String getExternalId() {
        return externalId;
    }

    @JsonProperty("externalId")
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @JsonProperty("transcript")
    public String getTranscript() {
        return transcript;
    }

    @JsonProperty("transcript")
    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    @JsonProperty("exonQCValue")
    public String getExonQCValue() {
        return exonQCValue;
    }

    @JsonProperty("exonQCValue")
    public void setExonQCValue(String exonQCValue) {
        this.exonQCValue = exonQCValue;
    }

    @JsonProperty("rpkm")
    public String getRpkm() {
        return rpkm;
    }

    @JsonProperty("rpkm")
    public void setRpkm(String rpkm) {
        this.rpkm = rpkm;
    }

    @JsonProperty("zygosity")
    public String getZygosity() {
        return zygosity;
    }

    @JsonProperty("zygosity")
    public void setZygosity(String zygosity) {
        this.zygosity = zygosity;
    }

    @JsonProperty("endPosition")
    public String getEndPosition() {
        return endPosition;
    }

    @JsonProperty("endPosition")
    public void setEndPosition(String endPosition) {
        this.endPosition = endPosition;
    }

    @JsonProperty("diseases")
    public List<String> getDiseases() {
        if (diseases == null)
            diseases = new ArrayList<String>();
        return diseases;
    }

    @JsonProperty("diseases")
    public void setDiseases(List<String> diseases) {
        this.diseases = diseases;
    }

    @JsonProperty("interrogatedButNotFound")
    public String getInterrogatedButNotFound() {
        return interrogatedButNotFound;
    }

    @JsonProperty("interrogatedButNotFound")
    public void setInterrogatedButNotFound(String interrogatedButNotFound) {
        this.interrogatedButNotFound = interrogatedButNotFound;
    }

    @JsonProperty("medianRPKM")
    public String getMedianRPKM() {
        return medianRPKM;
    }

    @JsonProperty("medianRPKM")
    public void setMedianRPKM(String medianRPKM) {
        this.medianRPKM = medianRPKM;
    }

    @JsonProperty("confirmedBySanger")
    public String getConfirmedBySanger() {
        return confirmedBySanger;
    }

    @JsonProperty("confirmedBySanger")
    public void setConfirmedBySanger(String confirmedBySanger) {
        this.confirmedBySanger = confirmedBySanger;
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

    @JsonProperty("confirmedByMLPA")
    public String getConfirmedByMLPA() {
        return confirmedByMLPA;
    }

    @JsonProperty("confirmedByMLPA")
    public void setConfirmedByMLPA(String confirmedByMLPA) {
        this.confirmedByMLPA = confirmedByMLPA;
    }

    @JsonProperty("notInterpreted")
    public String getNotInterpreted() {
        return notInterpreted;
    }

    @JsonProperty("notInterpreted")
    public void setNotInterpreted(String notInterpreted) {
        this.notInterpreted = notInterpreted;
    }

    @JsonProperty("inheritance")
    public String getInheritance() {
        return inheritance;
    }

    @JsonProperty("inheritance")
    public void setInheritance(String inheritance) {
        this.inheritance = inheritance;
    }

    @JsonProperty("categoryType")
    public String getCategoryType() {
        return categoryType;
    }

    @JsonProperty("categoryType")
    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    @JsonProperty("exonQCPF")
    public String getExonQCPF() {
        return exonQCPF;
    }

    @JsonProperty("exonQCPF")
    public void setExonQCPF(String exonQCPF) {
        this.exonQCPF = exonQCPF;
    }
    
    @JsonProperty("log2R")
    public String getLog2R() {
        return log2R;
    }

    @JsonProperty("log2R")
    public void setLog2R(String log2R) {
        this.log2R = log2R;
    }

    @JsonProperty("position")
    public String getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(String position) {
        this.position = position;
    }

    @JsonProperty("gene")
    public String getGene() {
        return gene;
    }

    @JsonProperty("gene")
    public void setGene(String gene) {
        this.gene = gene;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("geneRegion")
    public String getGeneRegion() {
        return geneRegion;
    }

    @JsonProperty("geneRegion")
    public void setGeneRegion(String geneRegion) {
        this.geneRegion = geneRegion;
    }

    @JsonProperty("chromosome")
    public String getChromosome() {
        return chromosome;
    }

    @JsonProperty("chromosome")
    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    @JsonProperty("var")
    public String getVar() {
        return var;
    }

    @JsonProperty("var")
    public void setVar(String var) {
        this.var = var;
    }

    @JsonProperty("alt")
    public String getAlt() {
        return alt;
    }

    @JsonProperty("alt")
    public void setAlt(String alt) {
        this.alt = alt;
    }

    @JsonProperty("ref")
    public String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(String ref) {
        this.ref = ref;
    }

    @JsonProperty("interpretation")
    public String getInterpretation() {
        return interpretation;
    }

    @JsonProperty("interpretation")
    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }

    @JsonProperty("variantInterpretation")
    public String getVariantInterpretation() {
        return variantInterpretation;
    }

    @JsonProperty("variantInterpretation")
    public void setVariantInterpretation(String variantInterpretation) {
        this.variantInterpretation = variantInterpretation;
    }

    @JsonProperty("cDNA")
    public String getCDNA() {
        return cDNA;
    }

    @JsonProperty("cDNA")
    public void setCDNA(String cDNA) {
        this.cDNA = cDNA;
    }

    @JsonProperty("genomic")
    public String getGenomic() {
        return genomic;
    }

    @JsonProperty("genomic")
    public void setGenomic(String genomic) {
        this.genomic = genomic;
    }

    @JsonProperty("dnaChange")
    public String getDnaChange() {
        return dnaChange;
    }

    @JsonProperty("dnaChange")
    public void setDnaChange(String dnaChange) {
        this.dnaChange = dnaChange;
    }

    @JsonProperty("proteinChange")
    public String getProteinChange() {
        return proteinChange;
    }

    @JsonProperty("proteinChange")
    public void setProteinChange(String proteinChange) {
        this.proteinChange = proteinChange;
    }

    @JsonProperty("genotype")
    public String getGenotype() {
        return genotype;
    }

    @JsonProperty("genotype")
    public void setGenotype(String genotype) {
        this.genotype = genotype;
    }

    @JsonProperty("notes")
    public String getNotes() {
        return notes;
    }

    @JsonProperty("notes")
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @JsonProperty("alleleFraction")
    public String getAlleleFraction() {
        return alleleFraction;
    }

    @JsonProperty("alleleFraction")
    public void setAlleleFraction(String alleleFraction) {
        this.alleleFraction = alleleFraction;
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
