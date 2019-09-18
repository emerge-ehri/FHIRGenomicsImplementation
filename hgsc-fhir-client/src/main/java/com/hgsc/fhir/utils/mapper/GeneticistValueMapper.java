package com.hgsc.fhir.utils.mapper;

import com.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

import java.util.HashMap;

public class GeneticistValueMapper {

    public Practitioner geneticistValueMapping(Practitioner geneticist, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport) {

        //Text
        geneticist.setText(new Narrative().setStatus(Narrative.NarrativeStatus.EMPTY).setDiv(new XhtmlNode().setValue("fixed")));
        //Identifier
        geneticist.addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue("101")
                .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                        .setCode("NPI").setDisplay("National provider identifier"))));
        //Name
        geneticist.addName(new HumanName().setUse(HumanName.NameUse.USUAL).setFamily("Murdock").addGiven("David")
                .addSuffix("M.D., F.A.C.M.G.").setText("David Murdock, M.D., F.A.C.M.G."));
        //Qualification
        geneticist.addQualification(new Practitioner.PractitionerQualificationComponent().setCode(new CodeableConcept().addCoding(new Coding()
                .setSystem("http://terminology.hl7.org/CodeSystem/v2-2.7-0360")
                .setCode("MD").setDisplay("Doctor of Medicine")).setText("ABMGG Certified Molecular Geneticist, Assistant Laboratory Director"))
                .addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-npi").setValue("101")
                        .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                                .setCode("NPI").setDisplay("National provider identifier")))));
        //Address
        geneticist.addAddress(new Address().addLine("One Baylor Plaza").setCity("Houston").setState("TX").setPostalCode("77030").setCountry("USA"));

        return geneticist;
    }
}
