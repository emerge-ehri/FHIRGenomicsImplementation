package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.*;

import java.util.HashMap;

public class PractitionerValueMapper {

    public Practitioner orderingPhysicianValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Practitioner orderingPhysician = new Practitioner();

        orderingPhysician.setLanguage("en-US");

        //Identifier
        orderingPhysician.addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue(


                //TBD



                "p101"
        )
                .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                        .setCode("NPI").setDisplay("National provider identifier"))));
        //Name
        orderingPhysician.addName(new HumanName().setText(


                //TBD


                "map.orderingPhysicianName"));
        //Address
        orderingPhysician.addAddress(new Address().setText(


                //TBD


                "map.orderingPhysicianAddress"));

        return orderingPhysician;
    }

    public Practitioner geneticistOneValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Practitioner geneticistOne = new Practitioner();

        geneticistOne.setLanguage("en-US");

        if(hgscReport.getPractitionerData().get(0) != null) {
            //Identifier
            geneticistOne.addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue(hgscReport.getPractitionerData().get(0).getNPI())
                    .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                            .setCode("NPI").setDisplay("National provider identifier"))));
            //Name
            geneticistOne.addName(new HumanName().setUse(HumanName.NameUse.USUAL).setFamily(hgscReport.getPractitionerData().get(0).getLastName())
                    .addGiven(hgscReport.getPractitionerData().get(0).getFirstName() + " " + hgscReport.getPractitionerData().get(0).getMiddleName())
                    .addSuffix(hgscReport.getPractitionerData().get(0).getSuffix()));
            //Qualification
            geneticistOne.addQualification(new Practitioner.PractitionerQualificationComponent().setCode(new CodeableConcept().addCoding(new Coding()
                    .setSystem("http://terminology.hl7.org/CodeSystem/v2-2.7-0360").setCode(


                            //TBD
                            "MD").setDisplay(


                                    //TBD
                                    "Doctor of Medicine")))
                    .addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue(hgscReport.getPractitionerData().get(0).getNPI())
                            .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                                    .setCode("NPI").setDisplay("National provider identifier")))));
            geneticistOne.addQualification(new Practitioner.PractitionerQualificationComponent().setCode(new CodeableConcept()
                    .setText(

                            //TBD
                            "ABMGG Certified Molecular Geneticist, Assistant Laboratory Director")));
            //Address
            geneticistOne.addAddress(new Address().addLine("One Baylor Plaza").setCity("Houston").setState("TX").setPostalCode("77030").setCountry("USA"));
        }

        return geneticistOne;
    }

    public Practitioner geneticistTwoValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Practitioner geneticistTwo = new Practitioner();

        geneticistTwo.setLanguage("en-US");

        if(hgscReport.getPractitionerData().get(1) != null) {
            //Identifier
            geneticistTwo.addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue(hgscReport.getPractitionerData().get(1).getNPI())
                    .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                            .setCode("NPI").setDisplay("National provider identifier"))));
            //Name
            geneticistTwo.addName(new HumanName().setUse(HumanName.NameUse.USUAL).setFamily(hgscReport.getPractitionerData().get(1).getLastName())
                    .addGiven(hgscReport.getPractitionerData().get(1).getFirstName() + " " + hgscReport.getPractitionerData().get(1).getMiddleName())
                    .addSuffix(hgscReport.getPractitionerData().get(1).getSuffix()));
            //Qualification
            geneticistTwo.addQualification(new Practitioner.PractitionerQualificationComponent().setCode(new CodeableConcept().addCoding(new Coding()
                    .setSystem("http://terminology.hl7.org/CodeSystem/v2-2.7-0360").setCode(


                            //TBD
                            "MD").setDisplay(


                                    //TBD
                                    "Doctor of Medicine")))
                    .addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue(hgscReport.getPractitionerData().get(1).getNPI())
                            .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                                    .setCode("NPI").setDisplay("National provider identifier")))));
            geneticistTwo.addQualification(new Practitioner.PractitionerQualificationComponent().setCode(new CodeableConcept()
                    .setText(


                            //TBD
                            "ABMGG Certified Molecular Geneticist, Chief Quality Officer, Vice President, Chief Medical Officer")));
            //Address
            geneticistTwo.addAddress(new Address().addLine("One Baylor Plaza").setCity("Houston").setState("TX").setPostalCode("77030").setCountry("USA"));
        }

        return geneticistTwo;
    }
}
