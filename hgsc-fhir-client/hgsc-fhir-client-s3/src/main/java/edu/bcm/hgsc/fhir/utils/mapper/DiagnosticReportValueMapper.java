package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class DiagnosticReportValueMapper {

    public DiagnosticReport diagnosticReportValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf, byte[] pdfBytes, byte[] excidBytes) throws ParseException {

        DiagnosticReport diagnosticReport = new DiagnosticReport();

        diagnosticReport.setLanguage(hgscReport.getLanguage());

        //Profile
        diagnosticReport.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/diagnosticreport");
        //Identifier
        if (mappingConfig.containsKey("HgscReport.reportIdentifier")) {
            if(hgscReport.getReportIdentifier() != null && !hgscReport.getReportIdentifier().equals("")) {
                diagnosticReport.addIdentifier(new Identifier().setSystem("http://namingsystem.org/").setValue(hgscReport.getReportIdentifier())
                        .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                                .setCode("FILL").setDisplay("Filler Identifier"))));
            }
        }

        //extensions
        if (mappingConfig.containsKey("HgscReport.geneCoverageText")) {
            if(hgscReport.getGeneCoverageText() != null && !hgscReport.getGeneCoverageText().equals("")) {

                RelatedArtifact relatedArtifact = new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.DOCUMENTATION)
                        .setLabel("Genetic Sequencing Coverage Information").setDisplay(hgscReport.getGeneCoverageText());

                if (mappingConfig.containsKey("HgscReport.excidFile")) {
                    if(excidBytes != null && excidBytes.length > 0) {
                        relatedArtifact.setDocument(new Attachment().setContentType("text/BED").setData(excidBytes));
                    }
                }

                Extension ext1 = new Extension("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/relatedArtifact", relatedArtifact);

                diagnosticReport.addExtension(ext1);
            }
        }

        Extension ext3 = new Extension("http://namingsystem.org/fhir/StructureDefinition/test-disclaimer",
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
        if (mappingConfig.containsKey("HgscReport.pdfReport")) {
            if(pdfBytes != null && pdfBytes.length > 0) {
                Attachment attachedReport = new Attachment();
                attachedReport.setContentType("application/pdf").setData(pdfBytes);
                diagnosticReport.addPresentedForm(attachedReport);
            }
        }

        return diagnosticReport;
    }
}
