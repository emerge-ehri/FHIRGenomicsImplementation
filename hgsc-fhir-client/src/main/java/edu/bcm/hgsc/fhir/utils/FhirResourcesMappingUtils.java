package edu.bcm.hgsc.fhir.utils;

import edu.bcm.hgsc.fhir.models.HgscEmergeReport;
import edu.bcm.hgsc.fhir.utils.mapper.*;
import org.hl7.fhir.r4.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class FhirResourcesMappingUtils {

    FileUtils fileUtils = new FileUtils();

    public Map<String, Object> mapping(HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport) throws ParseException {

        Map<String, Object> results = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

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

        //ObsReportComment
        Observation obsReportComment = null;
        //DxPanel
        Observation dxPanel = null;
        //PgxPanel
        Observation pgxPanel = null;
        //PgxResult
        Observation pgxResult_1001 = null;
        Observation pgxResult_2001 = null;
        Observation pgxResult_3001 = null;
        Observation pgxResult_4001 = null;
        Observation pgxResult_5001 = null;
        Observation pgxResult_6001 = null;
        //ObsInhDisPaths
        Observation obsInhDisPaths = null;

        // Geneticist
        Practitioner geneticistOne = null;
        Practitioner geneticistTwo = null;

        // PlanDefinition
        PlanDefinition planDefinition = null;

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
                    case "ObsReportComment":
                        obsReportComment = (Observation) Class.forName("org.hl7.fhir.r4.model.Observation").newInstance();
                        break;
                    case "DxPanel":
                        dxPanel = (Observation) Class.forName("org.hl7.fhir.r4.model.Observation").newInstance();
                        break;
                    case "PgxPanel":
                        pgxPanel = (Observation) Class.forName("org.hl7.fhir.r4.model.Observation").newInstance();
                        break;
                    case "PgxResult_1001":
                        pgxResult_1001 = (Observation) Class.forName("org.hl7.fhir.r4.model.Observation").newInstance();
                        break;
                    case "PgxResult_2001":
                        pgxResult_2001 = (Observation) Class.forName("org.hl7.fhir.r4.model.Observation").newInstance();
                        break;
                    case "PgxResult_3001":
                        pgxResult_3001 = (Observation) Class.forName("org.hl7.fhir.r4.model.Observation").newInstance();
                        break;
                    case "PgxResult_4001":
                        pgxResult_4001 = (Observation) Class.forName("org.hl7.fhir.r4.model.Observation").newInstance();
                        break;
                    case "PgxResult_5001":
                        pgxResult_5001 = (Observation) Class.forName("org.hl7.fhir.r4.model.Observation").newInstance();
                        break;
                    case "PgxResult_6001":
                        pgxResult_6001 = (Observation) Class.forName("org.hl7.fhir.r4.model.Observation").newInstance();
                        break;
                    case "ObsInhDisPaths":
                        obsInhDisPaths = (Observation) Class.forName("org.hl7.fhir.r4.model.Observation").newInstance();
                        break;
                    case "GeneticistOne":
                        geneticistOne = (Practitioner) Class.forName("org.hl7.fhir.r4.model.Practitioner").newInstance();
                        break;
                    case "GeneticistTwo":
                        geneticistTwo = (Practitioner) Class.forName("org.hl7.fhir.r4.model.Practitioner").newInstance();
                        break;
                    case "PlanDefinition":
                        planDefinition = (PlanDefinition) Class.forName("org.hl7.fhir.r4.model.PlanDefinition").newInstance();
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
            patient = new PatientValueMapper().patientValueMapping(patient, mappingConfig, hgscEmergeReport, sdf);
            results.put("Patient", patient);
        }
        if (specimen != null) {
            specimen = new SpecimenValueMapper().specimenValueMapping(specimen, mappingConfig, hgscEmergeReport, sdf);
            results.put("Specimen", specimen);
        }
        if (serviceRequest != null) {
            serviceRequest = new ServiceRequestValueMapper().serviceRequestValueMapping(serviceRequest, mappingConfig, hgscEmergeReport, sdf);
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
            obsOverall = new ObsOverallValueMapper().obsOverallValueMapping(obsOverall, mappingConfig, hgscEmergeReport, sdf);
            results.put("ObsOverall", obsOverall);
        }
//        if (dxCNVVariants != null) {
//            dxCNVVariants = new DxCNVVariantsValueMapper().dxCNVVariantsValueMapping(dxCNVVariants, mappingConfig, hgscEmergeReport);
//            results.put("DxCNVVariants", dxCNVVariants);
//        }
        if (dxSNPINDELVariants != null) {
            dxSNPINDELVariants = new DxSNPINDELVariantsValueMapper().dxSNPINDELVariantsValueMapping(dxSNPINDELVariants, mappingConfig, hgscEmergeReport, sdf);
            results.put("DxSNPINDELVariants", dxSNPINDELVariants);
        }
        if (obsReportComment != null) {
            obsReportComment = new ObsReportCommentValueMapper().obsReportCommentValueMapping(obsReportComment, mappingConfig, hgscEmergeReport, sdf);
            results.put("ObsReportComment", obsReportComment);
        }
        if (dxPanel != null) {
            dxPanel = new DxPanelValueMapper().dxPanelValueMapping(dxPanel, mappingConfig, hgscEmergeReport, sdf);
            results.put("DxPanel", dxPanel);
        }
        if (pgxPanel != null) {
            pgxPanel = new PgxPanelValueMapper().pgxPanelValueMapping(pgxPanel, mappingConfig, hgscEmergeReport);
            results.put("PgxPanel", pgxPanel);
        }
        if (pgxResult_1001 != null) {
            pgxResult_1001 = new PgxMedImplicationsValueMapper().pgxResult_1001_ValueMapping(pgxResult_1001, mappingConfig, hgscEmergeReport, sdf);
            results.put("PgxResult_1001", pgxResult_1001);
        }
        if (pgxResult_2001 != null) {
            pgxResult_2001 = new PgxMedImplicationsValueMapper().pgxResult_2001_ValueMapping(pgxResult_2001, mappingConfig, hgscEmergeReport, sdf);
            results.put("PgxResult_2001", pgxResult_2001);
        }
        if (pgxResult_3001 != null) {
            pgxResult_3001 = new PgxMedImplicationsValueMapper().pgxResult_3001_ValueMapping(pgxResult_3001, mappingConfig, hgscEmergeReport, sdf);
            results.put("PgxResult_3001", pgxResult_3001);
        }
        if (pgxResult_4001 != null) {
            pgxResult_4001 = new PgxMedImplicationsValueMapper().pgxResult_4001_ValueMapping(pgxResult_4001, mappingConfig, hgscEmergeReport, sdf);
            results.put("PgxResult_4001", pgxResult_4001);
        }
        if (pgxResult_5001 != null) {
            pgxResult_5001 = new PgxMedImplicationsValueMapper().pgxResult_5001_ValueMapping(pgxResult_5001, mappingConfig, hgscEmergeReport, sdf);
            results.put("PgxResult_5001", pgxResult_5001);
        }
        if (pgxResult_6001 != null) {
            pgxResult_6001 = new PgxMedImplicationsValueMapper().pgxResult_6001_ValueMapping(pgxResult_6001, mappingConfig, hgscEmergeReport, sdf);
            results.put("PgxResult_6001", pgxResult_6001);
        }
        if (obsInhDisPaths != null) {
            obsInhDisPaths = new ObsInhDisPathsValueMapper().obsInhDisPathsValueMapping(obsInhDisPaths, mappingConfig, hgscEmergeReport, sdf);
            results.put("ObsInhDisPaths", obsInhDisPaths);
        }
        if (geneticistOne != null) {
            geneticistOne = new GeneticistValueMapper().geneticistOneValueMapping(geneticistOne, mappingConfig, hgscEmergeReport);
            results.put("GeneticistOne", geneticistOne);
        }
        if (geneticistTwo != null) {
            geneticistTwo = new GeneticistValueMapper().geneticistTwoValueMapping(geneticistTwo, mappingConfig, hgscEmergeReport);
            results.put("GeneticistTwo", geneticistTwo);
        }
        if (planDefinition != null) {
            planDefinition = new PlanDefinitionValueMapper().planDefinitionValueMapping(planDefinition, mappingConfig, hgscEmergeReport);
            results.put("PlanDefinition", planDefinition);
        }
        if (diagnosticReport != null) {
            diagnosticReport = new DiagnosticReportValueMapper().diagnosticReportValueMapping(diagnosticReport, mappingConfig, hgscEmergeReport, fileUtils, sdf);
            results.put("DiagnosticReport", diagnosticReport);
        }

        return results;
    }
}
