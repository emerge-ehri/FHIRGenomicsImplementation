package edu.bcm.hgsc.fhir.models;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "gene",
        "coverage"
})
public class SingleGeneCoverage {

    @JsonProperty("gene")
    private String gene;
    @JsonProperty("coverage")
    private String coverage;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("gene")
    public String getGene() {
        return gene;
    }

    @JsonProperty("gene")
    public void setGene(String gene) {
        this.gene = gene;
    }

    @JsonProperty("coverage")
    public String getCoverage() {
        return coverage;
    }

    @JsonProperty("coverage")
    public void setCoverage(String coverage) {
        this.coverage = coverage;
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

