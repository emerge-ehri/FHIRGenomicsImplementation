package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.*;

import java.util.HashMap;

public class GeneticistValueMapper {

    public Practitioner geneticistOneValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Practitioner geneticistOne = new Practitioner();

        //Identifier
        geneticistOne.addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue("NPI???")
                .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                        .setCode("NPI").setDisplay("National provider identifier"))));
        //Name
        geneticistOne.addName(new HumanName().setUse(HumanName.NameUse.USUAL).setFamily("Murdock").addGiven("David")
                .addSuffix("M.D., F.A.C.M.G.").setText("David Murdock, M.D., F.A.C.M.G."));
        //Qualification
        geneticistOne.addQualification(new Practitioner.PractitionerQualificationComponent().setCode(new CodeableConcept().addCoding(new Coding()
                .setSystem("http://terminology.hl7.org/CodeSystem/v2-2.7-0360")
                .setCode("MD").setDisplay("Doctor of Medicine")).setText("ABMGG Certified Molecular Geneticist, Assistant Laboratory Director"))
                .addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue("NPI???")
                        .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                                .setCode("NPI").setDisplay("National provider identifier")))));
        //Address
        geneticistOne.addAddress(new Address().addLine("One Baylor Plaza").setCity("Houston").setState("TX").setPostalCode("77030").setCountry("USA"));

        return geneticistOne;
    }

    public Practitioner geneticistTwoValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Practitioner geneticistTwo = new Practitioner();

        //Identifier
        geneticistTwo.addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue("NPI???")
                .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                        .setCode("NPI").setDisplay("National provider identifier"))));
        //Name
        geneticistTwo.addName(new HumanName().setUse(HumanName.NameUse.USUAL).setFamily("Eng").addGiven("Christine")
                .addSuffix("M.D.").setText("Christine Eng, M.D."));
        //Qualification
        geneticistTwo.addQualification(new Practitioner.PractitionerQualificationComponent().setCode(new CodeableConcept().addCoding(new Coding()
                .setSystem("http://terminology.hl7.org/CodeSystem/v2-2.7-0360")
                .setCode("MD").setDisplay("Doctor of Medicine")).setText("ABMGG Certified Molecular Geneticist, Chief Quality Officer, Vice President, Chief Medical Officer"))
                .addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue("NPI???")
                        .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                                .setCode("NPI").setDisplay("National provider identifier")))));
        //Address
        geneticistTwo.addAddress(new Address().addLine("One Baylor Plaza").setCity("Houston").setState("TX").setPostalCode("77030").setCountry("USA"));

        return geneticistTwo;
    }
}
