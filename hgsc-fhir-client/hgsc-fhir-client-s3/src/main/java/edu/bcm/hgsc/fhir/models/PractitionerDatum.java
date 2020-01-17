package edu.bcm.hgsc.fhir.models;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "NPI",
        "suffix",
        "firstName",
        "middleName",
        "lastName",
        "address",
        "city",
        "state",
        "role",
        "qualification",
        "postalCode",
        "country"
})
public class PractitionerDatum {

    @JsonProperty("NPI")
    private String NPI;
    @JsonProperty("suffix")
    private String suffix;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("middleName")
    private String middleName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("address")
    private String address;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("role")
    private String role;
    @JsonProperty("qualification")
    private List<String> qualification;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("country")
    private String country;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("NPI")
    public String getNPI() {
        return NPI;
    }

    @JsonProperty("NPI")
    public void setNPI(String NPI) {
        this.NPI = NPI;
    }

    @JsonProperty("suffix")
    public String getSuffix() {
        return suffix;
    }

    @JsonProperty("suffix")
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("middleName")
    public String getMiddleName() {
        return middleName;
    }

    @JsonProperty("middleName")
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("role")
    public String getRole() {
        return role;
    }

    @JsonProperty("role")
    public void setRole(String role) {
        this.role = role;
    }

    @JsonProperty("qualification")
    public List<String> getQualification() {
        return qualification;
    }

    @JsonProperty("qualification")
    public void setQualification(List<String> qualification) {
        this.qualification = qualification;
    }

    @JsonProperty("postalCode")
    public String getPostalCode() {
        return postalCode;
    }

    @JsonProperty("postalCode")
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
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

