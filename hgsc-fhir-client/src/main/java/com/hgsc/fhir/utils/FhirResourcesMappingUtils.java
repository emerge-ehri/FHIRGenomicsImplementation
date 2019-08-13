package com.hgsc.fhir.utils;

import com.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

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
        ServiceRequest serviceRequest = null;
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
            patient = patientValueMapping(patient, mappingConfig, hgscEmergeReport);
            results.put("Patient", patient);
        }
        if (specimen != null) {
            specimen = specimenValueMapping(specimen, mappingConfig, hgscEmergeReport);
            results.put("Specimen", specimen);
        }
        if (serviceRequest != null) {
            serviceRequest = serviceRequestValueMapping(serviceRequest, mappingConfig, hgscEmergeReport);
            results.put("ServiceRequest", serviceRequest);
        }
        if (diagnosticReport != null) {
            diagnosticReport = diagnosticReportValueMapping(diagnosticReport, mappingConfig, hgscEmergeReport, fileUtils);
            results.put("DiagnosticReport", diagnosticReport);
        }

        return results;
    }

    public Patient patientValueMapping(Patient patient, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport) {

        //Identifier
        if (mappingConfig.containsKey("HgscEmergeReport.patientID")) {
            patient.addIdentifier(new Identifier().setSystem("https://emerge.mc.vanderbilt.edu/").setValue(hgscEmergeReport.getPatientID())
            .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                    .setCode("TAX").setDisplay("Patient external identifier"))));
        }
        //Name
        if (mappingConfig.containsKey("HgscEmergeReport.patientLastName")) {
            patient.addName(new HumanName().setUse(HumanName.NameUse.USUAL).setFamily(hgscEmergeReport.getPatientLastName()));
        }
        if (mappingConfig.containsKey("HgscEmergeReport.patientFirstName")) {
            patient.getNameFirstRep().addGiven(hgscEmergeReport.getPatientFirstName());
        }
        if (mappingConfig.containsKey("HgscEmergeReport.patientMiddleInitial")) {
            patient.getNameFirstRep().addGiven(hgscEmergeReport.getPatientMiddleInitial());
        }
        if (mappingConfig.containsKey("HgscEmergeReport.patientName")) {
            patient.getNameFirstRep().setText(hgscEmergeReport.getPatientFirstName()
                    + " " + hgscEmergeReport.getPatientMiddleInitial() + " " + hgscEmergeReport.getPatientLastName());
        }
        //Language
        patient.setLanguageElement(new CodeType("en-US"));
        //Gender
        if (mappingConfig.containsKey("HgscEmergeReport.sex")) {
            patient.setGender(Enumerations.AdministrativeGender.fromCode(hgscEmergeReport.getSex().toLowerCase()));
        }
        //DateOfBirth
        if (mappingConfig.containsKey("HgscEmergeReport.dateOfBirth")) {
            patient.setBirthDate(new Date(hgscEmergeReport.getDateOfBirth()));
        }

        //extensions
        Extension ext1 = new Extension("http://hl7.org/fhir/us/core/StructureDefinition/us-core-birthsex",
                new CodeType().setSystem("http://terminology.hl7.org/CodeSystem/v3-AdministrativeGender")
                        .setValue(hgscEmergeReport.getSex().substring(0,1)));

        Extension ext2 = new Extension("http://hl7.org/fhir/us/core/StructureDefinition/us-core-ethnicity");
        Extension ext2child1 = new Extension("ombCategory", new Coding().setSystem("urn:oid:2.16.840.1.113883.6.238")
                .setCode("2186-5").setDisplay(hgscEmergeReport.getEthnicity()));
        Extension ext2child2 = new Extension("text", new StringType(hgscEmergeReport.getEthnicity()));
        ext2.addExtension(ext2child1).addExtension(ext2child2);

        Extension ext3 = new Extension("http://hl7.org/fhir/us/core/StructureDefinition/us-core-race");
        Extension ext3child1 = new Extension("ombCategory", new Coding().setSystem("urn:oid:2.16.840.1.113883.6.238")
                .setCode("2054-5").setDisplay(hgscEmergeReport.getRace()));
        Extension ext3child2 = new Extension("text", new StringType(hgscEmergeReport.getRace()));
        ext3.addExtension(ext3child1).addExtension(ext3child2);

        Extension ext4 = new Extension("Age", new IntegerType(83));

        patient.addExtension(ext1);
        patient.addExtension(ext2);
        patient.addExtension(ext3);
        patient.addExtension(ext4);

        return patient;
    }

    public DiagnosticReport diagnosticReportValueMapping(DiagnosticReport diagnosticReport, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport, FileUtils fileUtils) {

        //Text
        diagnosticReport.setText(new Narrative().setStatus(Narrative.NarrativeStatus.EMPTY).setDiv(new XhtmlNode().setValue("fixed")));
        //Profile
        diagnosticReport.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/diagnosticreport");
        //Identifier
        if (mappingConfig.containsKey("HgscEmergeReport.reportIdentifier")) {
            diagnosticReport.addIdentifier(new Identifier().setSystem("https://emerge.hgsc.bcm.edu/").setValue(hgscEmergeReport.getReportIdentifier()));
        }

        //extensions
        Extension ext1 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/relatedArtifact",
                new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.DOCUMENTATION).setLabel("Genetic Sequencing Coverage Information")
                        .setDisplay("The BED file attached includes sequencing coverage information for the genetic regions studied for the specimen the test is performed on.")
                        .setDocument(new Attachment().setContentType("text/BED").setData(null)));

        Extension ext2 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/recommendedAction",
                new Reference(new Task()));

        Extension ext3 = new Extension("http://hl7.org/fhir/StructureDefinition/test-disclaimer",
                new StringType("This test was developed ..... (disclaimer text from report footer)"));

        Extension ext4 = new Extension("http://hl7.org/fhir/StructureDefinition/comments");
        if (mappingConfig.containsKey("HgscEmergeReport.reportComment")) {
            ext4.setValue(new StringType(hgscEmergeReport.getReportComment()));
        } else {
            ext4.setValue(new StringType(""));
        }

        Extension ext5 = new Extension("http://hl7.org/fhir/StructureDefinition/workflow-instantiatesCanonicalPlanDefinition",
                new StringType("PlanDefinition/emerge-chop-pnl"));

        diagnosticReport.addExtension(ext1);
        diagnosticReport.addExtension(ext2);
        diagnosticReport.addExtension(ext3);
        diagnosticReport.addExtension(ext4);
        diagnosticReport.addExtension(ext5);

        //Status
        if (mappingConfig.containsKey("HgscEmergeReport.reportStatus")) {
            diagnosticReport.setStatus(DiagnosticReport.DiagnosticReportStatus.fromCode(hgscEmergeReport.getReportStatus().toLowerCase()));
        }
        //Category
        diagnosticReport.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0074")
                .setCode("GE").setDisplay("Genetics"))
                .addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0074")
                .setCode("LAB").setDisplay("Laboratory")));
        //Code
        diagnosticReport.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("81247-9").setDisplay("Master HL7 genetic variant reporting panel")));
        //EffectiveDateTime
        if (mappingConfig.containsKey("HgscEmergeReport.sampleCollectedDate")) {
            diagnosticReport.setEffective(new DateTimeType(new Date(hgscEmergeReport.getSampleCollectedDate())));
        }
        //Issued
        if (mappingConfig.containsKey("HgscEmergeReport.reportDate")) {
            diagnosticReport.setIssued(new Date(hgscEmergeReport.getReportDate()));
        }
//        if (mappingConfig.containsKey("HgscEmergeReport.attachedReport")) {
//            Attachment attachedReport = new Attachment();
//            attachedReport.setData(fileUtils.readBytesFromFile(FileUtils.PROJECT_DIRECTORY + "Consent.pdf"));
//            diagnosticReport.addPresentedForm(attachedReport);
//        }

        return diagnosticReport;
    }

    public Specimen specimenValueMapping(Specimen specimen, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport) {

        //Text
        specimen.setText(new Narrative().setStatus(Narrative.NarrativeStatus.EMPTY).setDiv(new XhtmlNode().setValue("fixed")));
        //Profile
        specimen.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/specimen");
        if (mappingConfig.containsKey("HgscEmergeReport.patientSampleID")) {
            specimen.addIdentifier(new Identifier().setSystem("https://emerge.mc.vanderbilt.edu/").setValue(hgscEmergeReport.getPatientSampleID()));
        }
        //Identifier
        if (mappingConfig.containsKey("HgscEmergeReport.accessionNumber")) {
            specimen.addIdentifier(new Identifier().setSystem("https://emerge.hgsc.bcm.edu/").setValue(hgscEmergeReport.getAccessionNumber())
                    .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                            .setCode("ACSN").setDisplay("Accession ID"))));
        }
        //Type
        specimen.setType(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                        .setCode("258566005").setDisplay("Deoxyribonucleic acid sample")));
        //ReceivedTime
        if (mappingConfig.containsKey("HgscEmergeReport.sampleReceivedDate")) {
            specimen.setReceivedTime(new Date(hgscEmergeReport.getSampleReceivedDate()));
        }
        //Collection
        if (mappingConfig.containsKey("HgscEmergeReport.sampleCollectedDate")) {
            specimen.setCollection(new Specimen.SpecimenCollectionComponent().setCollected(new DateTimeType(new Date(hgscEmergeReport.getSampleCollectedDate())))
                    .setBodySite(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                            .setCode("119297000").setDisplay("Blood specimen (specimen)"))));
        }

//        HgscEmergeReport.genomicSource:Specimen.bodySite.coding.display
//        ??HgscEmergeReport.labStatus:Specimen.status
//        HgscEmergeReport.totalDNA:Specimen.collection.quantity
//        HgscEmergeReport.barcode:Specimen.type.coding.code

//        if (mappingConfig.containsKey("HgscEmergeReport.labStatus")) {
//            specimen.setStatus(Specimen.SpecimenStatus.fromCode(hgscEmergeReport.getLabStatus().toLowerCase()));
//        }

        return specimen;
    }

    public ServiceRequest serviceRequestValueMapping(ServiceRequest serviceRequest, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport) {

        //Text
        serviceRequest.setText(new Narrative().setStatus(Narrative.NarrativeStatus.EMPTY).setDiv(new XhtmlNode().setValue("fixed")));
        //Profile
        serviceRequest.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/servicerequest");
        //Identifier
//        if (mappingConfig.containsKey("HgscEmergeReport.reportIdentifier")) {
//            serviceRequest.addIdentifier(new Identifier().setSystem("").setValue(hgscEmergeReport.getReportIdentifier()));
//        }
        //InstantiatesCanonical
        serviceRequest.addInstantiatesCanonical("http://amc1.edu/lab-sc1/fhir/PlanDefinition/emerge-chop-pnl");
        //Status--???
        if (mappingConfig.containsKey("HgscEmergeReport.labStatus")) {
            serviceRequest.setStatus(ServiceRequest.ServiceRequestStatus.COMPLETED
                    //.fromCode(hgscEmergeReport.getLabStatus().toLowerCase())
            );
        }
        //Intent-???fixed
        serviceRequest.setIntent(ServiceRequest.ServiceRequestIntent.FILLERORDER);
        //Category
        serviceRequest.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                .setCode("108252007").setDisplay("Laboratory procedure")));
        //Code
        serviceRequest.setCode(new CodeableConcept().addCoding(new Coding().setSystem("")
                .setCode("").setDisplay("")).setText("eMERGE-Seq Panel - CHOP"));
        //AuthoredOn

        //PerformerType
        serviceRequest.setPerformerType(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                .setCode("410852461000087105").setDisplay("Clinical genetics service")));
        //ReasonCode
        serviceRequest.addReasonCode(new CodeableConcept().addCoding(new Coding().setSystem("https://emerge.geneinsight.com")
                .setCode("10093-7").setDisplay("Not selected for trait")));

        return serviceRequest;
    }

}
