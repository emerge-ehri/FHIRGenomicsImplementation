package com.hgsc.fhir.utils.mapper;

import com.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.r4.model.codesystems.V3AdministrativeGender;
import org.hl7.fhir.r4.model.codesystems.V3Ethnicity;
import org.hl7.fhir.r4.model.codesystems.V3Race;

import java.util.Date;
import java.util.HashMap;
import java.util.stream.Stream;

public class PatientValueMapper {

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
        Extension ext1 = null;
        //Returns enum V3AdministrativeGender or V3AdministrativeGender.NULL if not present
        V3AdministrativeGender administrativeGender = Stream.of(V3AdministrativeGender.values()).filter(v3AdministrativeGender -> v3AdministrativeGender.getDefinition().equals(hgscEmergeReport.getSex()))
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

        Extension ext2 = new Extension("http://hl7.org/fhir/us/core/StructureDefinition/us-core-ethnicity");
        //Returns enum V3Ethnicity or V3Ethnicity.NULL if not present
        V3Ethnicity ethnicity = Stream.of(V3Ethnicity.values()).filter(v3Ethnicity -> v3Ethnicity.getDefinition().equals(hgscEmergeReport.getEthnicity()))
                .findFirst().orElse(V3Ethnicity.NULL);
        if(!ethnicity.toCode().equals("?")){
            Extension ext2child1 = new Extension("ombCategory", new Coding().setSystem("urn:oid:2.16.840.1.113883.6.238")
                    .setCode(ethnicity.toCode()).setDisplay(ethnicity.getDisplay()));
            ext2.addExtension(ext2child1);
        }else{
            Extension ext2child2 = new Extension("text", new StringType(hgscEmergeReport.getEthnicity()));
            ext2.addExtension(ext2child2);
        }

        Extension ext3 = new Extension("http://hl7.org/fhir/us/core/StructureDefinition/us-core-race");
        //Returns enum V3Race or V3Race.NULL if not present
        V3Race race = Stream.of(V3Race.values()).filter(v3Race -> v3Race.getDefinition().equals(hgscEmergeReport.getRace()))
                .findFirst().orElse(V3Race.NULL);
        Extension ext3child1 = new Extension("ombCategory", new Coding().setSystem("urn:oid:2.16.840.1.113883.6.238")
                .setCode(race.toCode()).setDisplay(race.getDisplay()));
        ext3.addExtension(ext3child1);
        Extension ext3child2 = new Extension("text", new StringType(hgscEmergeReport.getRace()));
        ext3.addExtension(ext3child2);

        Extension ext4 = new Extension("Age", new IntegerType(83));

        patient.addExtension(ext1);
        patient.addExtension(ext2);
        patient.addExtension(ext3);
        patient.addExtension(ext4);

        return patient;
    }
}
