package edu.bcm.hgsc.fhir.utils;

import edu.bcm.hgsc.fhir.models.HgscEmergeReport;
import edu.bcm.hgsc.fhir.utils.mapper.*;
import org.apache.log4j.Logger;
import org.hl7.fhir.r4.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class FhirResourcesMappingUtils {

    private static Logger logger = Logger.getLogger(FhirResourcesMappingUtils.class);

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

        // create all available resources
        for (String resource : resources) {
            try {
                switch (resource) {
                    case "Patient":
                        Patient patient = new PatientValueMapper().patientValueMapping(mappingConfig, hgscEmergeReport, sdf);
                        results.put("Patient", patient);
                        break;
                    case "Specimen":
                        Specimen specimen = new SpecimenValueMapper().specimenValueMapping(mappingConfig, hgscEmergeReport, sdf);
                        results.put("Specimen", specimen);
                        break;
                    case "ServiceRequest":
                        ServiceRequest serviceRequest = new ServiceRequestValueMapper().serviceRequestValueMapping(mappingConfig, hgscEmergeReport, sdf);
                        results.put("ServiceRequest", serviceRequest);
                        break;
                    case "Organization":
                        Organization organization = new OrganizationValueMapper().organizationValueMapping(mappingConfig, hgscEmergeReport);
                        results.put("Organization", organization);
                        break;
                    case "OrganizationBCM":
                        Organization organizationBCM = new OrganizationBCMValueMapper().organizationBCMValueMapping(mappingConfig, hgscEmergeReport);
                        results.put("OrganizationBCM", organizationBCM);
                        break;
                    case "ObsOverall":
                        Observation obsOverall = new ObsOverallValueMapper().obsOverallValueMapping(mappingConfig, hgscEmergeReport, sdf);
                        results.put("ObsOverall", obsOverall);
                        break;
                    case "DxCNVVariants":
                        Observation dxCNVVariants = new DxCNVVariantsValueMapper().dxCNVVariantsValueMapping(mappingConfig, hgscEmergeReport);
                        results.put("DxCNVVariants", dxCNVVariants);
                        break;
                    case "DxSNPINDELVariants":
                        Observation dxSNPINDELVariants = new DxSNPINDELVariantsValueMapper().dxSNPINDELVariantsValueMapping(mappingConfig, hgscEmergeReport, sdf);
                        results.put("DxSNPINDELVariants", dxSNPINDELVariants);
                        break;
                    case "ObsReportComment":
                        Observation obsReportComment = new ObsReportCommentValueMapper().obsReportCommentValueMapping(mappingConfig, hgscEmergeReport, sdf);
                        results.put("ObsReportComment", obsReportComment);
                        break;
                    case "DxPanel":
                        Observation dxPanel = new DxPanelValueMapper().dxPanelValueMapping(mappingConfig, hgscEmergeReport, sdf);
                        results.put("DxPanel", dxPanel);
                        break;
                    case "PgxPanel":
                        Observation pgxPanel = new PgxPanelValueMapper().pgxPanelValueMapping(mappingConfig, hgscEmergeReport);
                        results.put("PgxPanel", pgxPanel);
                        break;
                    case "PgxResult_1001":
                        Observation pgxResult_1001 = new PgxMedImplicationsValueMapper().pgxResult_1001_ValueMapping(mappingConfig, hgscEmergeReport, sdf);
                        results.put("PgxResult_1001", pgxResult_1001);
                        break;
                    case "PgxResult_2001":
                        Observation pgxResult_2001 = new PgxMedImplicationsValueMapper().pgxResult_2001_ValueMapping(mappingConfig, hgscEmergeReport, sdf);
                        results.put("PgxResult_2001", pgxResult_2001);
                        break;
                    case "PgxResult_3001":
                        Observation pgxResult_3001 = new PgxMedImplicationsValueMapper().pgxResult_3001_ValueMapping(mappingConfig, hgscEmergeReport, sdf);
                        results.put("PgxResult_3001", pgxResult_3001);
                        break;
                    case "PgxResult_4001":
                        Observation pgxResult_4001 = new PgxMedImplicationsValueMapper().pgxResult_4001_ValueMapping(mappingConfig, hgscEmergeReport, sdf);
                        results.put("PgxResult_4001", pgxResult_4001);
                        break;
                    case "PgxResult_5001":
                        Observation pgxResult_5001 = new PgxMedImplicationsValueMapper().pgxResult_5001_ValueMapping(mappingConfig, hgscEmergeReport, sdf);
                        results.put("PgxResult_5001", pgxResult_5001);
                        break;
                    case "PgxResult_6001":
                        Observation pgxResult_6001 = new PgxMedImplicationsValueMapper().pgxResult_6001_ValueMapping(mappingConfig, hgscEmergeReport, sdf);
                        results.put("PgxResult_6001", pgxResult_6001);
                        break;
                    case "ObsInhDisPaths":
                        Observation obsInhDisPaths = new ObsInhDisPathsValueMapper().obsInhDisPathsValueMapping(mappingConfig, hgscEmergeReport, sdf);
                        results.put("ObsInhDisPaths", obsInhDisPaths);
                        break;
                    case "GeneticistOne":
                        Practitioner geneticistOne = new GeneticistValueMapper().geneticistOneValueMapping(mappingConfig, hgscEmergeReport);
                        results.put("GeneticistOne", geneticistOne);
                        break;
                    case "GeneticistTwo":
                        Practitioner geneticistTwo = new GeneticistValueMapper().geneticistTwoValueMapping(mappingConfig, hgscEmergeReport);
                        results.put("GeneticistTwo", geneticistTwo);
                        break;
                    case "PlanDefinition":
                        PlanDefinition planDefinition = new PlanDefinitionValueMapper().planDefinitionValueMapping(mappingConfig, hgscEmergeReport);
                        results.put("PlanDefinition", planDefinition);
                        break;
                    case "DiagnosticReport":
                        DiagnosticReport diagnosticReport = new DiagnosticReportValueMapper().diagnosticReportValueMapping(mappingConfig, hgscEmergeReport, fileUtils, sdf);
                        results.put("DiagnosticReport", diagnosticReport);
                        break;
                }
            } catch (Exception e) {
                logger.error("mapping Failed:", e);
            }
        }

        return results;
    }
}
