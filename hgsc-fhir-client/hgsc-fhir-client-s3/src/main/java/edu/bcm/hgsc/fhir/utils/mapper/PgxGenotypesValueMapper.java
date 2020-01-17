package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import edu.bcm.hgsc.fhir.models.PgxDatum;
import org.hl7.fhir.r4.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class PgxGenotypesValueMapper {

    PgxMedImplicationsValueMapper pgxMedImplicationsValueMapper = new PgxMedImplicationsValueMapper();

    public Observation pgxGeno_1001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxGeno_1001 = new Observation();

        pgxGeno_1001.setLanguage(hgscReport.getLanguage());

        PgxDatum pgxData = pgxMedImplicationsValueMapper.getPgxDataByGeneSymbol(hgscReport, "CYP2C19");

        //Profile
        pgxGeno_1001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/genotype");
        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if (hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                pgxGeno_1001.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Category
        pgxGeno_1001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxGeno_1001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("84413-4").setDisplay("genotype display name")));

        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                pgxGeno_1001.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }
        //ValueCodeableConcept
        if(pgxData != null && pgxData.getDiplotype() != null && !pgxData.getDiplotype().equals("")) {
            pgxGeno_1001.setValue(new CodeableConcept().setText(pgxData.getDiplotype()));
        }

        //Component:gene-studied
        if(pgxData != null && pgxData.getGeneSymbol() != null && !pgxData.getGeneSymbol().equals("")) {
            pgxGeno_1001.addComponent(new Observation.ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                    new Coding().setSystem("http://loinc.org").setCode("48018-6").setDisplay("Gene studied [ID]")))
                    .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://www.genenames.org/geneId")
                            .setCode(pgxData.getHgncID()).setDisplay(pgxData.getGeneSymbol()))));
        }

        return pgxGeno_1001;
    }

    public Observation pgxGeno_2001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxGeno_2001 = new Observation();

        pgxGeno_2001.setLanguage(hgscReport.getLanguage());

        PgxDatum pgxData = pgxMedImplicationsValueMapper.getPgxDataByGeneSymbol(hgscReport, "DPYD");

        //Profile
        pgxGeno_2001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/genotype");
        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if (hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                pgxGeno_2001.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Category
        pgxGeno_2001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxGeno_2001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("84413-4").setDisplay("genotype display name")));

        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                pgxGeno_2001.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }
        //ValueCodeableConcept
        if(pgxData != null && pgxData.getDiplotype() != null && !pgxData.getDiplotype().equals("")) {
            pgxGeno_2001.setValue(new CodeableConcept().setText(pgxData.getDiplotype()));
        }

        //Component:gene-studied
        if(pgxData != null && pgxData.getGeneSymbol() != null && !pgxData.getGeneSymbol().equals("")) {
            pgxGeno_2001.addComponent(new Observation.ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                    new Coding().setSystem("http://loinc.org").setCode("48018-6").setDisplay("Gene studied [ID]")))
                    .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://www.genenames.org/geneId")
                            .setCode(pgxData.getHgncID()).setDisplay(pgxData.getGeneSymbol()))));
        }

        return pgxGeno_2001;
    }

    public Observation pgxGeno_3001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxGeno_3001 = new Observation();

        pgxGeno_3001.setLanguage(hgscReport.getLanguage());

        PgxDatum pgxData = pgxMedImplicationsValueMapper.getPgxDataByGeneSymbol(hgscReport, "IFNL3");

        //Profile
        pgxGeno_3001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/genotype");
        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if (hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                pgxGeno_3001.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Category
        pgxGeno_3001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxGeno_3001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("84413-4").setDisplay("genotype display name")));

        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                pgxGeno_3001.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }
        //ValueCodeableConcept
        if(pgxData != null && pgxData.getDiplotype() != null && !pgxData.getDiplotype().equals("")) {
            pgxGeno_3001.setValue(new CodeableConcept().setText(pgxData.getDiplotype()));
        }

        //Component:gene-studied
        if(pgxData != null && pgxData.getGeneSymbol() != null && !pgxData.getGeneSymbol().equals("")) {
            pgxGeno_3001.addComponent(new Observation.ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                    new Coding().setSystem("http://loinc.org").setCode("48018-6").setDisplay("Gene studied [ID]")))
                    .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://www.genenames.org/geneId")
                            .setCode(pgxData.getHgncID()).setDisplay(pgxData.getGeneSymbol()))));
        }

        return pgxGeno_3001;
    }

    public Observation pgxGeno_4001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxGeno_4001 = new Observation();

        pgxGeno_4001.setLanguage(hgscReport.getLanguage());

        PgxDatum pgxData = pgxMedImplicationsValueMapper.getPgxDataByGeneSymbol(hgscReport, "SLCO1B1");

        //Profile
        pgxGeno_4001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/genotype");
        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if (hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                pgxGeno_4001.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Category
        pgxGeno_4001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxGeno_4001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("84413-4").setDisplay("genotype display name")));

        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                pgxGeno_4001.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }
        //ValueCodeableConcept
        if(pgxData != null && pgxData.getDiplotype() != null && !pgxData.getDiplotype().equals("")) {
            pgxGeno_4001.setValue(new CodeableConcept().setText(pgxData.getDiplotype()));
        }

        //Component:gene-studied
        if(pgxData != null && pgxData.getGeneSymbol() != null && !pgxData.getGeneSymbol().equals("")) {
            pgxGeno_4001.addComponent(new Observation.ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                    new Coding().setSystem("http://loinc.org").setCode("48018-6").setDisplay("Gene studied [ID]")))
                    .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://www.genenames.org/geneId")
                            .setCode(pgxData.getHgncID()).setDisplay(pgxData.getGeneSymbol()))));
        }

        return pgxGeno_4001;
    }

    public Observation pgxGeno_5001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxGeno_5001 = new Observation();

        pgxGeno_5001.setLanguage(hgscReport.getLanguage());

        PgxDatum pgxData = pgxMedImplicationsValueMapper.getPgxDataByGeneSymbol(hgscReport, "TPMT");

        //Profile
        pgxGeno_5001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/genotype");
        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if (hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                pgxGeno_5001.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Category
        pgxGeno_5001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxGeno_5001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("84413-4").setDisplay("genotype display name")));

        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                pgxGeno_5001.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }
        //ValueCodeableConcept
        if(pgxData != null && pgxData.getDiplotype() != null && !pgxData.getDiplotype().equals("")) {
            pgxGeno_5001.setValue(new CodeableConcept().setText(pgxData.getDiplotype()));
        }

        //Component:gene-studied
        if(pgxData != null && pgxData.getGeneSymbol() != null && !pgxData.getGeneSymbol().equals("")) {
            pgxGeno_5001.addComponent(new Observation.ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                    new Coding().setSystem("http://loinc.org").setCode("48018-6").setDisplay("Gene studied [ID]")))
                    .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://www.genenames.org/geneId")
                            .setCode(pgxData.getHgncID()).setDisplay(pgxData.getGeneSymbol()))));
        }

        return pgxGeno_5001;
    }

    public Observation pgxGeno_6001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxGeno_6001 = new Observation();

        pgxGeno_6001.setLanguage(hgscReport.getLanguage());

        PgxDatum pgxData = pgxMedImplicationsValueMapper.getPgxDataByGeneSymbol(hgscReport, "CYP2C9");

        //Profile
        pgxGeno_6001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/genotype");
        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if (hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                pgxGeno_6001.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Category
        pgxGeno_6001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxGeno_6001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("84413-4").setDisplay("genotype display name")));

        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                pgxGeno_6001.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }
        //ValueCodeableConcept
        if(pgxData != null && pgxData.getDiplotype() != null && !pgxData.getDiplotype().equals("")) {
            pgxGeno_6001.setValue(new CodeableConcept().setText(pgxData.getDiplotype()));
        }

        //Component:gene-studied
        if(pgxData != null && pgxData.getGeneSymbol() != null && !pgxData.getGeneSymbol().equals("")) {
            pgxGeno_6001.addComponent(new Observation.ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                    new Coding().setSystem("http://loinc.org").setCode("48018-6").setDisplay("Gene studied [ID]")))
                    .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://www.genenames.org/geneId")
                            .setCode(pgxData.getHgncID()).setDisplay(pgxData.getGeneSymbol()))));
        }

        return pgxGeno_6001;
    }

    public Observation pgxGeno_7001_ValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Observation pgxGeno_7001 = new Observation();

        pgxGeno_7001.setLanguage(hgscReport.getLanguage());

        PgxDatum pgxData = pgxMedImplicationsValueMapper.getPgxDataByGeneSymbol(hgscReport, "VKORC1");

        //Profile
        pgxGeno_7001.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/genotype");
        //Status
        if (mappingConfig.containsKey("HgscReport.reportStatus")) {
            if (hgscReport.getReportStatus() != null && !hgscReport.getReportStatus().equals("")) {
                pgxGeno_7001.setStatus(Observation.ObservationStatus.fromCode(hgscReport.getReportStatus().toLowerCase()));
            }
        }
        //Category
        pgxGeno_7001.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
                .setCode("laboratory").setDisplay("Laboratory")));
        //Code
        pgxGeno_7001.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("84413-4").setDisplay("genotype display name")));
        //Issued
        if (mappingConfig.containsKey("HgscReport.reportDate")) {
            if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
                pgxGeno_7001.setIssued(sdf.parse(hgscReport.getReportDate()));
            }
        }
        //ValueCodeableConcept
        if(pgxData != null && pgxData.getDiplotype() != null && !pgxData.getDiplotype().equals("")) {
            pgxGeno_7001.setValue(new CodeableConcept().setText(pgxData.getDiplotype()));
        }

        //Component:gene-studied
        if(pgxData != null && pgxData.getGeneSymbol() != null && !pgxData.getGeneSymbol().equals("")) {
            pgxGeno_7001.addComponent(new Observation.ObservationComponentComponent().setCode(new CodeableConcept().addCoding(
                    new Coding().setSystem("http://loinc.org").setCode("48018-6").setDisplay("Gene studied [ID]")))
                    .setValue(new CodeableConcept().addCoding(new Coding().setSystem("http://www.genenames.org/geneId")
                            .setCode(pgxData.getHgncID()).setDisplay(pgxData.getGeneSymbol()))));
        }

        return pgxGeno_7001;
    }
}
