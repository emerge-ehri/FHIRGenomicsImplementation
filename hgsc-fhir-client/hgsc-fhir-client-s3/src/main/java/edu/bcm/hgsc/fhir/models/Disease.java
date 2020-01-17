package edu.bcm.hgsc.fhir.models;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "text",
        "code",
        "ontology"
})
public class Disease {

    @JsonProperty("text")
    private String text;
    @JsonProperty("code")
    private String code;
    @JsonProperty("ontology")
    private String ontology;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("ontology")
    public String getOntology() {
        return ontology;
    }

    @JsonProperty("ontology")
    public void setOntology(String ontology) {
        this.ontology = ontology;
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

