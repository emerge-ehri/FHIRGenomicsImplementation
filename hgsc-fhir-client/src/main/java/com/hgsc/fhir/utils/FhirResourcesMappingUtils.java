package com.hgsc.fhir.utils;

import com.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.*;

import java.util.Date;
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
                    case "DiagnosticReport":
                        diagnosticReport = (DiagnosticReport) Class.forName("org.hl7.fhir.r4.model.DiagnosticReport").newInstance();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        HgscEmergeReport.sampleCollectedDate:Specimen.collection.collectedDateTime
//        ??HgscEmergeReport.labStatus:Specimen.status
//        HgscEmergeReport.sampleCollectionSource:Specimen.bodySite.text
//        HgscEmergeReport.specimenType:Specimen.type.coding.display
//        HgscEmergeReport.totalDNA:Specimen.collection.quantity
//        HgscEmergeReport.genomicSource:Specimen.bodySite.coding.display
//        HgscEmergeReport.barcode:Specimen.type.coding.code
//        HgscEmergeReport.sampleReceivedDate:Specimen.receivedTime
//        HgscEmergeReport.accessionNumber:Specimen.accessionIdentifier
        if (mappingConfig.containsKey("HgscEmergeReport.patientSampleID")) {
            specimen.addIdentifier(new Identifier().setValue(hgscEmergeReport.getPatientSampleID()));
        }
//        if (mappingConfig.containsKey("HgscEmergeReport.labStatus")) {
//            specimen.setStatus(Specimen.SpecimenStatus.fromCode(hgscEmergeReport.getLabStatus().toLowerCase()));
//        }

        // set resource values
        if (patient != null) {
            patient = patientValueMapping(patient, mappingConfig, hgscEmergeReport);
            results.put("Patient", patient);
        }
        if (specimen != null) {
            results.put("Specimen", specimen);
        }
        if (diagnosticReport != null) {
            diagnosticReport = diagnosticReportValueMapping(diagnosticReport, mappingConfig, hgscEmergeReport, fileUtils);
            results.put("DiagnosticReport", diagnosticReport);
        }

        return results;
    }

    public Patient patientValueMapping(Patient patient, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport) {

        if (mappingConfig.containsKey("HgscEmergeReport.patientID")) {
            patient.addIdentifier(new Identifier().setValue(hgscEmergeReport.getPatientID()));
        }
        if (mappingConfig.containsKey("HgscEmergeReport.patientLastName")) {
            patient.addName(new HumanName().setUse(HumanName.NameUse.USUAL).setFamily(hgscEmergeReport.getPatientLastName()));
        }
        if (mappingConfig.containsKey("HgscEmergeReport.patientFirstName")) {
            patient.getNameFirstRep().addGiven(hgscEmergeReport.getPatientFirstName());
        }
        if (mappingConfig.containsKey("HgscEmergeReport.patientMiddleInitial")) {
            patient.getNameFirstRep().addGiven(hgscEmergeReport.getPatientMiddleInitial());
        }
        if (mappingConfig.containsKey("HgscEmergeReport.sex")) {
            patient.setGender(Enumerations.AdministrativeGender.fromCode(hgscEmergeReport.getSex().toLowerCase()));
        }
        if (mappingConfig.containsKey("HgscEmergeReport.dateOfBirth")) {
            patient.setBirthDate(new Date(hgscEmergeReport.getDateOfBirth()));
        }

        return patient;
    }

    public DiagnosticReport diagnosticReportValueMapping(DiagnosticReport diagnosticReport, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport, FileUtils fileUtils) {

        if (mappingConfig.containsKey("HgscEmergeReport.reportIdentifier")) {
            diagnosticReport.addIdentifier(new Identifier().setValue(hgscEmergeReport.getReportIdentifier()));
        }
        if (mappingConfig.containsKey("HgscEmergeReport.reportDate")) {
            diagnosticReport.setIssued(new Date(hgscEmergeReport.getReportDate()));
        }
        if (mappingConfig.containsKey("HgscEmergeReport.reportStatus")) {
            diagnosticReport.setStatus(DiagnosticReport.DiagnosticReportStatus.fromCode(hgscEmergeReport.getReportStatus().toLowerCase()));
        }
        if (mappingConfig.containsKey("HgscEmergeReport.attachedReport")) {
            Attachment attachedReport = new Attachment();
            attachedReport.setData(fileUtils.readBytesFromFile(FileUtils.PROJECT_DIRECTORY + "Consent.pdf"));
            diagnosticReport.addPresentedForm(attachedReport);
        }

        return diagnosticReport;
    }
}
