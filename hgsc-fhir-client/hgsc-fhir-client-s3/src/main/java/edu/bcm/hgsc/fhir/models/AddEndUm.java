package edu.bcm.hgsc.fhir.models;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "date",
        "reportChangeSummary",
        "reportChangeDetail"
})
public class AddEndUm {

    @JsonProperty("date")
    private String date;
    @JsonProperty("reportChangeSummary")
    private String reportChangeSummary;
    @JsonProperty("reportChangeDetail")
    private String reportChangeDetail;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("reportChangeSummary")
    public String getReportChangeSummary() {
        return reportChangeSummary;
    }

    @JsonProperty("reportChangeSummary")
    public void setReportChangeSummary(String reportChangeSummary) {
        this.reportChangeSummary = reportChangeSummary;
    }

    @JsonProperty("reportChangeDetail")
    public String getReportChangeDetail() {
        return reportChangeDetail;
    }

    @JsonProperty("reportChangeDetail")
    public void setReportChangeDetail(String reportChangeDetail) {
        this.reportChangeDetail = reportChangeDetail;
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

