package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.*;

import java.util.HashMap;

public class PractitionerValueMapper {

    public Practitioner orderingPhysicianValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Practitioner orderingPhysician = new Practitioner();

        //Identifier
        orderingPhysician.addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue("p101")
                .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                        .setCode("NPI").setDisplay("National provider identifier"))));
        //Name
        orderingPhysician.addName(new HumanName().setText("map.orderingPhysicianName"));
        //Address
        orderingPhysician.addAddress(new Address().setText("map.orderingPhysicianAddress"));

        return orderingPhysician;
    }

    public Practitioner geneticistOneValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Practitioner geneticistOne = new Practitioner();

        //Identifier
        geneticistOne.addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue("NPI101")
                .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                        .setCode("NPI").setDisplay("National provider identifier"))));
        //Name
        geneticistOne.addName(new HumanName().setUse(HumanName.NameUse.USUAL).setFamily("Murdock").addGiven("David")
                .addSuffix("M.D., F.A.C.M.G.").setText("David Murdock, M.D., F.A.C.M.G."));
        //Qualification
        geneticistOne.addQualification(new Practitioner.PractitionerQualificationComponent().setCode(new CodeableConcept().addCoding(new Coding()
                .setSystem("http://terminology.hl7.org/CodeSystem/v2-2.7-0360").setCode("MD").setDisplay("Doctor of Medicine")))
                .addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue("NPI???")
                        .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                                .setCode("NPI").setDisplay("National provider identifier")))));
        geneticistOne.addQualification(new Practitioner.PractitionerQualificationComponent().setCode(new CodeableConcept()
                .setText("ABMGG Certified Molecular Geneticist, Assistant Laboratory Director")));
        //Address
        geneticistOne.addAddress(new Address().addLine("One Baylor Plaza").setCity("Houston").setState("TX").setPostalCode("77030").setCountry("USA"));

        return geneticistOne;
    }

    public Practitioner geneticistTwoValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Practitioner geneticistTwo = new Practitioner();

        //Identifier
        geneticistTwo.addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue("NPI102")
                .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                        .setCode("NPI").setDisplay("National provider identifier"))));
        //Name
        geneticistTwo.addName(new HumanName().setUse(HumanName.NameUse.USUAL).setFamily("Eng").addGiven("Christine")
                .addSuffix("M.D.").setText("Christine Eng, M.D."));
        //Qualification
        geneticistTwo.addQualification(new Practitioner.PractitionerQualificationComponent().setCode(new CodeableConcept().addCoding(new Coding()
                .setSystem("http://terminology.hl7.org/CodeSystem/v2-2.7-0360").setCode("MD").setDisplay("Doctor of Medicine")))
                .addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue("NPI???")
                        .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                                .setCode("NPI").setDisplay("National provider identifier")))));
        geneticistTwo.addQualification(new Practitioner.PractitionerQualificationComponent().setCode(new CodeableConcept()
                .setText("ABMGG Certified Molecular Geneticist, Chief Quality Officer, Vice President, Chief Medical Officer")));
        //Address
        geneticistTwo.addAddress(new Address().addLine("One Baylor Plaza").setCity("Houston").setState("TX").setPostalCode("77030").setCountry("USA"));

        return geneticistTwo;
    }
}
