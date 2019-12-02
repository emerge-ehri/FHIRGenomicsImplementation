package edu.bcm.hgsc.fhir.utils;

import edu.bcm.hgsc.fhir.models.HgscReport;
import edu.bcm.hgsc.fhir.utils.mapper.*;
import org.apache.log4j.Logger;
import org.hl7.fhir.r4.model.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class FhirResourcesMappingUtils {

    private static Logger logger = Logger.getLogger(FhirResourcesMappingUtils.class);

    FileUtils fileUtils = new FileUtils();

    public Map<String, Object> mapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) throws ParseException {

        Map<String, Object> results = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        // Get resource types from the mapping config file
        HashSet<String> resources = new HashSet<String>();
        for (String value : mappingConfig.values()) {
            resources.add(value);
        }

        // create all available resources
        for (String resource : resources) {
            try {
                switch (resource) {
                    case "Patient":
                        Patient patient = new PatientValueMapper().patientValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("Patient", patient);
                        break;
                    case "Specimen":
                        Specimen specimen = new SpecimenValueMapper().specimenValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("Specimen", specimen);
                        break;
                    case "ServiceRequest":
                        ServiceRequest serviceRequest = new ServiceRequestValueMapper().serviceRequestValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("ServiceRequest", serviceRequest);
                        break;
                    case "OrganizationHGSC":
                        Organization organizationHGSC = new OrganizationValueMapper().organizationHGSCValueMapping(mappingConfig, hgscReport);
                        results.put("OrganizationHGSC", organizationHGSC);
                        break;
                    case "OrganizationBCM":
                        Organization organizationBCM = new OrganizationValueMapper().organizationBCMValueMapping(mappingConfig, hgscReport);
                        results.put("OrganizationBCM", organizationBCM);
                        break;
                    case "OrganizationCHP":
                        Organization organizationCHP = new OrganizationValueMapper().organizationCHPValueMapping(mappingConfig, hgscReport);
                        results.put("OrganizationCHP", organizationCHP);
                        break;
                    case "ObsOverall":
                        Observation obsOverall = new ObsOverallValueMapper().obsOverallValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("ObsOverall", obsOverall);
                        break;
                    case "DxCNVVariants":
                        Observation dxCNVVariants = new DxCNVVariantsValueMapper().dxCNVVariantsValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("DxCNVVariants", dxCNVVariants);
                        break;
                    case "DxSNPINDELVariants":
                        HashMap<String, Observation> dxSNPINDELVariants = new DxSNPINDELVariantsValueMapper().dxSNPINDELVariantsValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("DxSNPINDELVariants", dxSNPINDELVariants);
                        break;
                    case "ObsReportComment":
                        Observation obsReportComment = new ObsReportCommentValueMapper().obsReportCommentValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("ObsReportComment", obsReportComment);
                        break;
                    case "DxPanel":
                        Observation dxPanel = new DxPanelValueMapper().dxPanelValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("DxPanel", dxPanel);
                        break;
                    case "PgxPanel":
                        Observation pgxPanel = new PgxPanelValueMapper().pgxPanelValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("PgxPanel", pgxPanel);
                        break;
                    case "PgxResult_1001":
                        Observation pgxResult_1001 = new PgxMedImplicationsValueMapper().pgxResult_1001_ValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("PgxResult_1001", pgxResult_1001);
                        break;
                    case "PgxResult_2001":
                        Observation pgxResult_2001 = new PgxMedImplicationsValueMapper().pgxResult_2001_ValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("PgxResult_2001", pgxResult_2001);
                        break;
                    case "PgxResult_3001":
                        Observation pgxResult_3001 = new PgxMedImplicationsValueMapper().pgxResult_3001_ValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("PgxResult_3001", pgxResult_3001);
                        break;
                    case "PgxResult_4001":
                        Observation pgxResult_4001 = new PgxMedImplicationsValueMapper().pgxResult_4001_ValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("PgxResult_4001", pgxResult_4001);
                        break;
                    case "PgxResult_5001":
                        Observation pgxResult_5001 = new PgxMedImplicationsValueMapper().pgxResult_5001_ValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("PgxResult_5001", pgxResult_5001);
                        break;
                    case "PgxResult_6001":
                        Observation pgxResult_6001 = new PgxMedImplicationsValueMapper().pgxResult_6001_ValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("PgxResult_6001", pgxResult_6001);
                        break;
                    case "ObsInhDisPaths":
                        HashMap<String, Observation> obsInhDisPaths = new ObsInhDisPathsValueMapper().obsInhDisPathsValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("ObsInhDisPaths", obsInhDisPaths);
                        break;
                    case "GeneticistOne":
                        Practitioner geneticistOne = new PractitionerValueMapper().geneticistOneValueMapping(mappingConfig, hgscReport);
                        results.put("GeneticistOne", geneticistOne);
                        break;
                    case "GeneticistTwo":
                        Practitioner geneticistTwo = new PractitionerValueMapper().geneticistTwoValueMapping(mappingConfig, hgscReport);
                        results.put("GeneticistTwo", geneticistTwo);
                        break;
                    case "PractitionerRole":
                        PractitionerRole practitionerRole = new PractitionerRoleValueMapper().practitionerRoleValueMapping(mappingConfig, hgscReport);
                        results.put("PractitionerRole", practitionerRole);
                        break;
                    case "PgxGeno_1001":
                        Observation pgxGeno_1001 = new PgxGenotypesValueMapper().pgxGeno_1001_ValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("PgxGeno_1001", pgxGeno_1001);
                        break;
                    case "PgxGeno_2001":
                        Observation pgxGeno_2001 = new PgxGenotypesValueMapper().pgxGeno_2001_ValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("PgxGeno_2001", pgxGeno_2001);
                        break;
                    case "PgxGeno_5001":
                        Observation pgxGeno_5001 = new PgxGenotypesValueMapper().pgxGeno_5001_ValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("PgxGeno_5001", pgxGeno_5001);
                        break;
                    case "PgxGeno_6001":
                        Observation pgxGeno_6001 = new PgxGenotypesValueMapper().pgxGeno_6001_ValueMapping(mappingConfig, hgscReport, sdf);
                        results.put("PgxGeno_6001", pgxGeno_6001);
                        break;
                    case "Task":
                        Task task = new TaskValueMapper().taskValueMapping(mappingConfig, hgscReport);
                        results.put("Task", task);
                        break;
                    case "PlanDefinition":
                        PlanDefinition planDefinition = new PlanDefinitionValueMapper().planDefinitionValueMapping(mappingConfig, hgscReport);
                        results.put("PlanDefinition", planDefinition);
                        break;
                    case "DiagnosticReport":
                        DiagnosticReport diagnosticReport = new DiagnosticReportValueMapper().diagnosticReportValueMapping(mappingConfig, hgscReport, fileUtils, sdf);
                        results.put("DiagnosticReport", diagnosticReport);
                        break;
                }
            } catch (Exception e) {
                logger.error("mapping Failed:", e);
            }
        }

        return results;
    }

    public Object deepCopy(Object orig) {

        Object obj = null;
        ByteArrayOutputStream baos = null;
        ObjectOutputStream out = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream in = null;

        try {
            baos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(baos);
            out.writeObject(orig);

            bais = new ByteArrayInputStream(baos.toByteArray());
            in = new ObjectInputStream(bais);
            obj = in.readObject();
        } catch(Exception e) {
            logger.error("Failed to deep copy the object:", e);
        } finally {
            try {
                if(in != null) in.close();
                if(bais != null) bais.close();
                if(out != null) out.close();
                if(baos != null) baos.close();
            } catch (Exception e2) {
                logger.error("Failed to close resources during deep copying the object:", e2);
            }
        }

        return obj;
    }
}
