package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import edu.bcm.hgsc.fhir.utils.FileUtils;
import org.hl7.fhir.r4.model.*;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class DiagnosticReportValueMapper {

    public DiagnosticReport diagnosticReportValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, FileUtils fileUtils, File hgscReportFile, SimpleDateFormat sdf) throws ParseException {

        DiagnosticReport diagnosticReport = new DiagnosticReport();

        //Profile
        diagnosticReport.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/diagnosticreport");
        //Identifier
        if (mappingConfig.containsKey("HgscReport.reportIdentifier")) {
            if(hgscReport.getReportIdentifier() != null && !hgscReport.getReportIdentifier().equals("")) {
                diagnosticReport.addIdentifier(new Identifier().setSystem("https://emerge.hgsc.bcm.edu/").setValue(hgscReport.getReportIdentifier())
                        .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                                .setCode("FILL").setDisplay("Filler Identifier"))));
            }
        }

        //extensions
        if (mappingConfig.containsKey("HgscReport.geneCoverageText")) {
            if(hgscReport.getGeneCoverageText() != null && !hgscReport.getGeneCoverageText().equals("")) {
                Extension ext1 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/relatedArtifact",
                        new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.DOCUMENTATION).setLabel("Genetic Sequencing Coverage Information")
                                .setDisplay(hgscReport.getGeneCoverageText()).setDocument(new Attachment().setContentType("text/BED")
                                .setData(fileUtils.readBytesFromFile(hgscReportFile))));
                diagnosticReport.addExtension(ext1);
            }
        }

        Extension ext3 = new Extension("http://hl7.org/fhir/StructureDefinition/test-disclaimer",
                new StringType(hgscReport.getFooter()));
        diagnosticReport.addExtension(ext3);

        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if(hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                diagnosticReport.setStatus(DiagnosticReport.DiagnosticReportStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Category
        diagnosticReport.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0074")
                .setCode("GE").setDisplay("Genetics"))
                .addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0074")
                        .setCode("LAB").setDisplay("Laboratory")));
        //Code
        diagnosticReport.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("81247-9").setDisplay("Master HL7 genetic variant reporting panel")));
        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                diagnosticReport.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }
        //PresentedForm
//        if (mappingConfig.containsKey("HgscReport.attachedReport")) {
//        if(...!=null){}
//            Attachment attachedReport = new Attachment();
//            attachedReport.setContentType("application/pdf").setData(fileUtils.readBytesFromFile(hgscReportPDFFile from S3));
//            diagnosticReport.addPresentedForm(attachedReport);
//        }

        return diagnosticReport;
    }
}
