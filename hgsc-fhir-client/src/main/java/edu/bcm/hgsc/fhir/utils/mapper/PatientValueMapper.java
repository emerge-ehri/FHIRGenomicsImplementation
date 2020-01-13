package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.r4.model.codesystems.V3AdministrativeGender;
import org.hl7.fhir.r4.model.codesystems.V3Ethnicity;
import org.hl7.fhir.r4.model.codesystems.V3Race;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.HashMap;
import java.util.stream.Stream;

public class PatientValueMapper {

    public Patient patientValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        Patient patient = new Patient();

        patient.setLanguage(hgscReport.getLanguage());

        //Identifier
        //The code PI creates warnings during validation but until the HL7 Vocabulary group resolves this, we will be ignoring the warnings.
        if (mappingConfig.containsKey("HgscReport.patientID")) {
            if(hgscReport.getPatientID() != null && !hgscReport.getPatientID().equals("")) {
                patient.addIdentifier(new Identifier().setSystem("https://emerge.mc.vanderbilt.edu/").setValue(hgscReport.getPatientID())
                        .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                                .setCode("PI").setDisplay("Patient internal identifier"))));
            }
        }

        //Name
        if(hgscReport.getPatientLastName() != null && !hgscReport.getPatientLastName().equals("")) {
            if (mappingConfig.containsKey("HgscReport.patientLastName")) {
                patient.addName(new HumanName().setUse(HumanName.NameUse.USUAL).setFamily(hgscReport.getPatientLastName()));
            }
            if (mappingConfig.containsKey("HgscReport.patientFirstName")) {
                if(hgscReport.getPatientFirstName() != null && !hgscReport.getPatientFirstName().equals("")) {
                    patient.getNameFirstRep().addGiven(hgscReport.getPatientFirstName());
                }
            }
            if (mappingConfig.containsKey("HgscReport.patientMiddleInitial")) {
                if(hgscReport.getPatientMiddleInitial() != null && !hgscReport.getPatientMiddleInitial().equals("")) {
                    patient.getNameFirstRep().addGiven(hgscReport.getPatientMiddleInitial());
                }
            }
            if (mappingConfig.containsKey("HgscReport.patientName")) {
                patient.getNameFirstRep().setText(hgscReport.getPatientFirstName()
                        + " " + hgscReport.getPatientMiddleInitial() + " " + hgscReport.getPatientLastName());
            }
        }

        //DateOfBirth
        if (mappingConfig.containsKey("HgscReport.dateOfBirth")) {
            if(hgscReport.getDateOfBirth() != null && !hgscReport.getDateOfBirth().equals("")) {
                patient.setBirthDate(sdf.parse(hgscReport.getDateOfBirth()));
            }
        }

        //extensions
        if(hgscReport.getSex() != null && !hgscReport.getSex().equals("")) {
            Extension ext1 = null;
            //Returns enum V3AdministrativeGender or V3AdministrativeGender.NULL if not present
            V3AdministrativeGender administrativeGender = Stream.of(V3AdministrativeGender.values()).filter(v3AdministrativeGender -> v3AdministrativeGender.getDefinition().equals(hgscReport.getSex()))
                    .findFirst().orElse(V3AdministrativeGender.NULL);
            if(!administrativeGender.toCode().equals("?")){
                ext1 = new Extension("http://hl7.org/fhir/us/core/StructureDefinition/us-core-birthsex",
                        new CodeType().setSystem("http://terminology.hl7.org/CodeSystem/v3-AdministrativeGender")
                                .setValue(administrativeGender.toCode()));
            }else{
                ext1 = new Extension("http://hl7.org/fhir/us/core/StructureDefinition/us-core-birthsex",
                        new CodeType().setSystem("http://terminology.hl7.org/CodeSystem/v3-AdministrativeGender")
                                .setValue("Unknown"));
            }
            patient.addExtension(ext1);
        }

        if(hgscReport.getEthnicity() != null && !hgscReport.getEthnicity().equals("")) {
            Extension ext2 = new Extension("http://hl7.org/fhir/us/core/StructureDefinition/us-core-ethnicity");
            //Returns enum V3Ethnicity or V3Ethnicity.NULL if not present
            V3Ethnicity ethnicity = Stream.of(V3Ethnicity.values()).filter(v3Ethnicity -> v3Ethnicity.getDefinition().equals(hgscReport.getEthnicity()))
                    .findFirst().orElse(V3Ethnicity.NULL);
            if(!ethnicity.toCode().equals("?")){
                Extension ext2child1 = new Extension("ombCategory", new Coding().setSystem("urn:oid:2.16.840.1.113883.6.238")
                        .setCode(ethnicity.toCode()).setDisplay(ethnicity.getDisplay()));
                ext2.addExtension(ext2child1);
            }else{
                Extension ext2child2 = new Extension("text", new StringType(hgscReport.getEthnicity()));
                ext2.addExtension(ext2child2);
            }
            patient.addExtension(ext2);
        }

        if(hgscReport.getRace() != null && !hgscReport.getRace().equals("")) {
            Extension ext3 = new Extension("http://hl7.org/fhir/us/core/StructureDefinition/us-core-race");
            //Returns enum V3Race or V3Race.NULL if not present
            V3Race race = Stream.of(V3Race.values()).filter(v3Race -> v3Race.getDefinition().equals(hgscReport.getRace()))
                    .findFirst().orElse(V3Race.NULL);
            Extension ext3child1 = new Extension("ombCategory", new Coding().setSystem("urn:oid:2.16.840.1.113883.6.238")
                    .setCode(race.toCode()).setDisplay(race.getDisplay()));
            ext3.addExtension(ext3child1);
            Extension ext3child2 = new Extension("text", new StringType(hgscReport.getRace()));
            ext3.addExtension(ext3child2);
            patient.addExtension(ext3);
        }

        if (mappingConfig.containsKey("HgscReport.dateOfBirth")) {
            if(hgscReport.getDateOfBirth() != null && !hgscReport.getDateOfBirth().equals("")) {
                Extension ext4 = new Extension("http://hl7.org/fhir/StructureDefinition/patient-age", new IntegerType(calAge(hgscReport.getDateOfBirth(), sdf)));
                patient.addExtension(ext4);
            }
        }

        return patient;
    }

    private int calAge(String patientDOB, SimpleDateFormat sdf) throws ParseException {

        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(patientDOB));
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        LocalDate birthDate = LocalDate.of(year, month, date);
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
