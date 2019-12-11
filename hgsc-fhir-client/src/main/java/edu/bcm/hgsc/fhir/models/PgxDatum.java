package edu.bcm.hgsc.fhir.models;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sl9 on 10/6/17.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "interpretation",
        "drugs",
        "phenotype",
        "recommendation",
        "geneSymbol",
        "diplotype",
        "summary",
        "pgxDrugRecommendation"
})
public class PgxDatum {

    @JsonProperty("interpretation")
    private String interpretation;
    @JsonProperty("drugs")
    private List<String> drugs;
    @JsonProperty("phenotype")
    private String phenotype;
    @JsonProperty("recommendation")
    private String recommendation;
    @JsonProperty("geneSymbol")
    private String geneSymbol;
    @JsonProperty("diplotype")
    private String diplotype;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("pgxDrugRecommendation")
    private List<PgxDrugRec> pgxDrugRecommendation;

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

    @JsonProperty("drugs")
    public List<String> getDrugs() {
        return drugs;
    }

    @JsonProperty("drugs")
    public void setDrugs(List<String> drugs) {
        this.drugs = drugs;
    }

    @JsonProperty("phenotype")
    public String getPhenotype() {
        return phenotype;
    }

    @JsonProperty("phenotype")
    public void setPhenotype(String phenotype) {
        this.phenotype = phenotype;
    }

    @JsonProperty("recommendation")
    public String getRecommendation() {
        return recommendation;
    }

    @JsonProperty("recommendation")
    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    @JsonProperty("geneSymbol")
    public String getGeneSymbol() {
        return geneSymbol;
    }

    @JsonProperty("geneSymbol")
    public void setGeneSymbol(String geneSymbol) {
        this.geneSymbol = geneSymbol;
    }

    @JsonProperty("diplotype")
    public String getDiplotype() {
        return diplotype;
    }

    @JsonProperty("diplotype")
    public void setDiplotype(String diplotype) {
        this.diplotype = diplotype;
    }

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonProperty("pgxDrugRecommendation")
    public List<PgxDrugRec> getPgxDrugRecommendation() {
        return pgxDrugRecommendation;
    }

    @JsonProperty("pgxDrugRecommendation")
    public void setPgxDrugRecommendation(List<PgxDrugRec> pgxDrugRecommendation) {
        this.pgxDrugRecommendation = pgxDrugRecommendation;
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

