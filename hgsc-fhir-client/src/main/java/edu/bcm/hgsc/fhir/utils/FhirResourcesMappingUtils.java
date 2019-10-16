package edu.bcm.hgsc.fhir.utils;

import edu.bcm.hgsc.fhir.models.HgscEmergeReport;
import edu.bcm.hgsc.fhir.utils.mapper.*;
import org.hl7.fhir.r4.model.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class FhirResourcesMappingUtils {

    FileUtils fileUtils = new FileUtils();

    public Map<String, Object> mapping(HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport) {

        Map<String, Object> results = new HashMap<String, Object>();

        // Get resource types from the mapping config file
        HashSet<String> resources = new HashSet<String>();
        for (String value : mappingConfig.values()) {
            String[] strArr = value.split("\\.");
            resources.add(strArr[0]);
        }

        // init all possible resource types
        Patient patient = null;
        Specimen specimen = null;
        ServiceRequest serviceRequest = null;
        Organization organization = null;
        Organization organizationBCM = null;
        // ObsOverall
        Observation obsOverall = null;
        //DxCNVVariants
        Observation dxCNVVariants = null;
        //DxSNPINDELVariants
        Observation dxSNPINDELVariants = null;
        // Geneticist
        Practitioner geneticistOne = null;
        Practitioner geneticistTwo = null;
        DiagnosticReport diagnosticReport = null;

        // create all available resources
        for (String resource : resources) {
            try {
                switch (resource) {
                    case "Patient":
                        patient = (Patient) Class.forName("org.hl7.fhir.r4.model.Patient").newInstance();
                        break;
                    case "Specimen":
                        specimen = (Specimen) Class.forName("org.hl7.fhir.r4.model.Specimen").newInstance();
                        break;
                    case "ServiceRequest":
                        serviceRequest = (ServiceRequest) Class.forName("org.hl7.fhir.r4.model.ServiceRequest").newInstance();
                        break;
                    case "Organization":
                        organization = (Organization) Class.forName("org.hl7.fhir.r4.model.Organization").newInstance();
                        break;
                    case "OrganizationBCM":
                        organizationBCM = (Organization) Class.forName("org.hl7.fhir.r4.model.Organization").newInstance();
                        break;
                    case "ObsOverall":
                        obsOverall = (Observation) Class.forName("org.hl7.fhir.r4.model.Observation").newInstance();
                        break;
                    case "DxCNVVariants":
                        dxCNVVariants = (Observation) Class.forName("org.hl7.fhir.r4.model.Observation").newInstance();
                        break;
                    case "DxSNPINDELVariants":
                        dxSNPINDELVariants = (Observation) Class.forName("org.hl7.fhir.r4.model.Observation").newInstance();
                        break;
                    case "GeneticistOne":
                        geneticistOne = (Practitioner) Class.forName("org.hl7.fhir.r4.model.Practitioner").newInstance();
                        break;
                    case "GeneticistTwo":
                        geneticistTwo = (Practitioner) Class.forName("org.hl7.fhir.r4.model.Practitioner").newInstance();
                        break;
                    case "DiagnosticReport":
                        diagnosticReport = (DiagnosticReport) Class.forName("org.hl7.fhir.r4.model.DiagnosticReport").newInstance();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // set resource values
        if (patient != null) {
            patient = new PatientValueMapper().patientValueMapping(patient, mappingConfig, hgscEmergeReport);
            results.put("Patient", patient);
        }
        if (specimen != null) {
            specimen = new SpecimenValueMapper().specimenValueMapping(specimen, mappingConfig, hgscEmergeReport);
            results.put("Specimen", specimen);
        }
        if (serviceRequest != null) {
            serviceRequest = new ServiceRequestValueMapper().serviceRequestValueMapping(serviceRequest, mappingConfig, hgscEmergeReport);
            results.put("ServiceRequest", serviceRequest);
        }
        if (organization != null) {
            organization = new OrganizationValueMapper().organizationValueMapping(organization, mappingConfig, hgscEmergeReport);
            results.put("Organization", organization);
        }
        if (organizationBCM != null) {
            organizationBCM = new OrganizationBCMValueMapper().organizationBCMValueMapping(organizationBCM, mappingConfig, hgscEmergeReport);
            results.put("OrganizationBCM", organizationBCM);
        }
        if (obsOverall != null) {
            obsOverall = new ObsOverallValueMapper().obsOverallValueMapping(obsOverall, mappingConfig, hgscEmergeReport);
            results.put("ObsOverall", obsOverall);
        }
        if (dxCNVVariants != null) {
            dxCNVVariants = new DxCNVVariantsValueMapper().dxCNVVariantsValueMapping(dxCNVVariants, mappingConfig, hgscEmergeReport);
            results.put("DxCNVVariants", dxCNVVariants);
        }
        if (dxSNPINDELVariants != null) {
            dxSNPINDELVariants = new DxSNPINDELVariantsValueMapper().dxSNPINDELVariantsValueMapping(dxSNPINDELVariants, mappingConfig, hgscEmergeReport);
            results.put("DxSNPINDELVariants", dxSNPINDELVariants);
        }
        if (geneticistOne != null) {
            geneticistOne = new GeneticistValueMapper().geneticistOneValueMapping(geneticistOne, mappingConfig, hgscEmergeReport);
            results.put("GeneticistOne", geneticistOne);
        }
        if (geneticistTwo != null) {
            geneticistTwo = new GeneticistValueMapper().geneticistTwoValueMapping(geneticistTwo, mappingConfig, hgscEmergeReport);
            results.put("GeneticistTwo", geneticistTwo);
        }
        if (diagnosticReport != null) {
            diagnosticReport = new DiagnosticReportValueMapper().diagnosticReportValueMapping(diagnosticReport, mappingConfig, hgscEmergeReport, fileUtils);
            results.put("DiagnosticReport", diagnosticReport);
        }

        return results;
    }
}
