package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class PgxGenotypesValueMapper {

    public Observation pgxGeno_1001_ValueMapping(HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxGeno_1001 = new Observation();

        //Profile
        pgxGeno_1001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/genotype");
        //extensions
        Extension ext = new Extension("http://hl7.org/fhir/StructureDefinition/interpretation-summary-text",
                new StringType("This individual has a CYP2C19 *2/*2 genotype."));
        pgxGeno_1001.addExtension(ext);
        //Status
        if (mappingConfig.containsKey("HgscEmergeReport.reportStatus")) {
            pgxGeno_1001.setStatus(Observation.ObservationStatus.fromCode(hgscEmergeReport.getReportStatus().toLowerCase()));
        }
        //Category
        pgxGeno_1001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxGeno_1001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("84413-4").setDisplay("genotype display name")));
        //Issued
        if (mappingConfig.containsKey("HgscEmergeReport.reportDate")) {
            pgxGeno_1001.setIssued(sdf.parse(hgscEmergeReport.getReportDate()));
        }
        //ValueCodeableConcept
        pgxGeno_1001.setValue(new CodeableConcept().setText("CYP2C19 *2/*2"));

        return pgxGeno_1001;
    }

    public Observation pgxGeno_2001_ValueMapping(HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxGeno_2001 = new Observation();

        //Profile
        pgxGeno_2001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/genotype");
        //extensions
        Extension ext = new Extension("http://hl7.org/fhir/StructureDefinition/interpretation-summary-text",
                new StringType("This individual has a DPYD *1/*1 genotype."));
        pgxGeno_2001.addExtension(ext);
        //Status
        if (mappingConfig.containsKey("HgscEmergeReport.reportStatus")) {
            pgxGeno_2001.setStatus(Observation.ObservationStatus.fromCode(hgscEmergeReport.getReportStatus().toLowerCase()));
        }
        //Category
        pgxGeno_2001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxGeno_2001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("84413-4").setDisplay("genotype display name")));
        //Issued
        if (mappingConfig.containsKey("HgscEmergeReport.reportDate")) {
            pgxGeno_2001.setIssued(sdf.parse(hgscEmergeReport.getReportDate()));
        }
        //ValueCodeableConcept
        pgxGeno_2001.setValue(new CodeableConcept().setText("DPYD *1/*1"));

        return pgxGeno_2001;
    }

    public Observation pgxGeno_5001_ValueMapping(HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxGeno_5001 = new Observation();

        //Profile
        pgxGeno_5001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/genotype");
        //extensions
        Extension ext = new Extension("http://hl7.org/fhir/StructureDefinition/interpretation-summary-text",
                new StringType("This individual has a TPMT *1/*1 genotype."));
        pgxGeno_5001.addExtension(ext);
        //Status
        if (mappingConfig.containsKey("HgscEmergeReport.reportStatus")) {
            pgxGeno_5001.setStatus(Observation.ObservationStatus.fromCode(hgscEmergeReport.getReportStatus().toLowerCase()));
        }
        //Category
        pgxGeno_5001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxGeno_5001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("84413-4").setDisplay("genotype display name")));
        //Issued
        if (mappingConfig.containsKey("HgscEmergeReport.reportDate")) {
            pgxGeno_5001.setIssued(sdf.parse(hgscEmergeReport.getReportDate()));
        }
        //ValueCodeableConcept
        pgxGeno_5001.setValue(new CodeableConcept().setText("TPMT *1/*1"));

        return pgxGeno_5001;
    }

    public Observation pgxGeno_6001_ValueMapping(HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxGeno_6001 = new Observation();

        //Profile
        pgxGeno_6001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/genotype");
        //extensions
        Extension ext = new Extension("http://hl7.org/fhir/StructureDefinition/interpretation-summary-text",
                new StringType("This individual has a CYP2C9 *1/*1 genotype."));
        pgxGeno_6001.addExtension(ext);
        //Status
        if (mappingConfig.containsKey("HgscEmergeReport.reportStatus")) {
            pgxGeno_6001.setStatus(Observation.ObservationStatus.fromCode(hgscEmergeReport.getReportStatus().toLowerCase()));
        }
        //Category
        pgxGeno_6001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxGeno_6001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("84413-4").setDisplay("genotype display name")));
        //Issued
        if (mappingConfig.containsKey("HgscEmergeReport.reportDate")) {
            pgxGeno_6001.setIssued(sdf.parse(hgscEmergeReport.getReportDate()));
        }
        //ValueCodeableConcept
        pgxGeno_6001.setValue(new CodeableConcept().setText("CYP2C9 *1/*1"));

        return pgxGeno_6001;
    }
}
