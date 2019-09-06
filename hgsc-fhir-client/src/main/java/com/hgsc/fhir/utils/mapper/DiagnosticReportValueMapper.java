package com.hgsc.fhir.utils.mapper;

import com.hgsc.fhir.models.HgscEmergeReport;
import com.hgsc.fhir.utils.FileUtils;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

import java.util.Date;
import java.util.HashMap;

public class DiagnosticReportValueMapper {

    public DiagnosticReport diagnosticReportValueMapping(DiagnosticReport diagnosticReport, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport, FileUtils fileUtils) {

        //Text
        diagnosticReport.setText(new Narrative().setStatus(Narrative.NarrativeStatus.EMPTY).setDiv(new XhtmlNode().setValue("fixed")));
        //Profile
        diagnosticReport.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/diagnosticreport");
        //Identifier
        if (mappingConfig.containsKey("HgscEmergeReport.reportIdentifier")) {
            diagnosticReport.addIdentifier(new Identifier().setSystem("https://emerge.hgsc.bcm.edu/").setValue(hgscEmergeReport.getReportIdentifier())
                    .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                            .setCode("FILL").setDisplay("Filler Identifier"))));
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

//        Extension ext5 = new Extension("http://hl7.org/fhir/StructureDefinition/workflow-instantiatesCanonicalPlanDefinition",
//                new StringType("PlanDefinition/emerge-chop-pnl"));

        diagnosticReport.addExtension(ext1);
        diagnosticReport.addExtension(ext2);
        diagnosticReport.addExtension(ext3);
        diagnosticReport.addExtension(ext4);
        //diagnosticReport.addExtension(ext5);

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
}
