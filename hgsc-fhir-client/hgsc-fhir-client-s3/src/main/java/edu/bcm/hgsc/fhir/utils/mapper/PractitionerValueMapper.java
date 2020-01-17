package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import edu.bcm.hgsc.fhir.models.PractitionerDatum;
import org.hl7.fhir.r4.model.*;

import java.util.HashMap;

public class PractitionerValueMapper {

    public Practitioner orderingPhysicianValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Practitioner orderingPhysician = new Practitioner();

        orderingPhysician.setLanguage(hgscReport.getLanguage());

        //Identifier
        if (hgscReport.getOrderingPhysicianNPI() != null && !hgscReport.getOrderingPhysicianNPI().equals("")) {
            orderingPhysician.addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue(hgscReport.getOrderingPhysicianNPI())
                    .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                            .setCode("NPI").setDisplay("National provider identifier"))));
        }

        //Name
        if (hgscReport.getOrderingPhysicianName() != null && !hgscReport.getOrderingPhysicianName().equals("")) {
            orderingPhysician.addName(new HumanName().setText(hgscReport.getOrderingPhysicianName()));
        }

        //Address
        if (hgscReport.getOrderingPhysicianAddress() != null && !hgscReport.getOrderingPhysicianAddress().equals("")) {
            orderingPhysician.addAddress(new Address().setText(hgscReport.getOrderingPhysicianAddress()));
        }

        return orderingPhysician;
    }

    public Practitioner geneticistOneValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Practitioner geneticistOne = new Practitioner();

        geneticistOne.setLanguage(hgscReport.getLanguage());

        PractitionerDatum practitionerDatum = hgscReport.getPractitionerData().get(0);

        if(practitionerDatum != null) {
            //Identifier
            geneticistOne.addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue(practitionerDatum.getNPI())
                    .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                            .setCode("NPI").setDisplay("National provider identifier"))));
            //Name
            geneticistOne.addName(new HumanName().setUse(HumanName.NameUse.USUAL).setFamily(practitionerDatum.getLastName() + " " + practitionerDatum.getMiddleName())
                    .addGiven(practitionerDatum.getFirstName())
                    .addSuffix(practitionerDatum.getSuffix()));
            //Qualification
            geneticistOne.addQualification(new Practitioner.PractitionerQualificationComponent().setCode(new CodeableConcept().addCoding(new Coding()
                    .setSystem("http://terminology.hl7.org/CodeSystem/v2-2.7-0360").setCode(practitionerDatum.getQualification().get(0)).setDisplay("Doctor of Medicine")))
                    .addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue(practitionerDatum.getNPI())
                            .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                                    .setCode("NPI").setDisplay("National provider identifier")))));
            geneticistOne.addQualification(new Practitioner.PractitionerQualificationComponent().setCode(new CodeableConcept()
                    .setText(practitionerDatum.getQualification().get(1))));
            //Address
            geneticistOne.addAddress(new Address().addLine(practitionerDatum.getAddress()).setCity(practitionerDatum.getCity())
                    .setState(practitionerDatum.getState()).setPostalCode(practitionerDatum.getPostalCode()).setCountry(practitionerDatum.getCountry()));
        }

        return geneticistOne;
    }

    public Practitioner geneticistTwoValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Practitioner geneticistTwo = new Practitioner();

        geneticistTwo.setLanguage(hgscReport.getLanguage());

        PractitionerDatum practitionerDatum = hgscReport.getPractitionerData().get(1);

        if(practitionerDatum != null) {
            //Identifier
            geneticistTwo.addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue(practitionerDatum.getNPI())
                    .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                            .setCode("NPI").setDisplay("National provider identifier"))));
            //Name
            geneticistTwo.addName(new HumanName().setUse(HumanName.NameUse.USUAL).setFamily(practitionerDatum.getLastName() + " " + practitionerDatum.getMiddleName())
                    .addGiven(practitionerDatum.getFirstName())
                    .addSuffix(practitionerDatum.getSuffix()));
            //Qualification
            geneticistTwo.addQualification(new Practitioner.PractitionerQualificationComponent().setCode(new CodeableConcept().addCoding(new Coding()
                    .setSystem("http://terminology.hl7.org/CodeSystem/v2-2.7-0360").setCode(practitionerDatum.getQualification().get(0)).setDisplay("Doctor of Medicine")))
                    .addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue(practitionerDatum.getNPI())
                            .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                                    .setCode("NPI").setDisplay("National provider identifier")))));
            geneticistTwo.addQualification(new Practitioner.PractitionerQualificationComponent().setCode(new CodeableConcept()
                    .setText(practitionerDatum.getQualification().get(1))));
            //Address
            geneticistTwo.addAddress(new Address().addLine(practitionerDatum.getAddress()).setCity(practitionerDatum.getCity())
                    .setState(practitionerDatum.getState()).setPostalCode(practitionerDatum.getPostalCode()).setCountry(practitionerDatum.getCountry()));
        }

        return geneticistTwo;
    }
}
