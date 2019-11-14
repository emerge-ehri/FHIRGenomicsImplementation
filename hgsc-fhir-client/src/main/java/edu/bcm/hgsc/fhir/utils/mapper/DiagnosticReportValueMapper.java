package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscEmergeReport;
import edu.bcm.hgsc.fhir.utils.FileUtils;
import org.hl7.fhir.r4.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class DiagnosticReportValueMapper {

    public DiagnosticReport diagnosticReportValueMapping(HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport, FileUtils fileUtils, SimpleDateFormat sdf) throws ParseException {

        DiagnosticReport diagnosticReport = new DiagnosticReport();

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
                        .setDisplay(hgscEmergeReport.getGeneCoverage().toString()
                                //"The BED file attached includes sequencing coverage information for the genetic regions studied for the specimen the test is performed on."
                        )
                        .setDocument(new Attachment().setContentType("text/BED").setData(null)));
        Extension ext3 = new Extension("http://hl7.org/fhir/StructureDefinition/test-disclaimer",
                new StringType("This test was developed ..... (disclaimer text from report footer)"));
        diagnosticReport.addExtension(ext1);
        diagnosticReport.addExtension(ext3);

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
        //Issued
        if (mappingConfig.containsKey("HgscEmergeReport.reportDate")) {
            diagnosticReport.setIssued(sdf.parse(hgscEmergeReport.getReportDate()));
        }
//        if (mappingConfig.containsKey("HgscEmergeReport.attachedReport")) {
//            Attachment attachedReport = new Attachment();
//            attachedReport.setData(fileUtils.readBytesFromFile(FileUtils.PROJECT_DIRECTORY + "Consent.pdf"));
//            diagnosticReport.addPresentedForm(attachedReport);
//        }

        return diagnosticReport;
    }
}
