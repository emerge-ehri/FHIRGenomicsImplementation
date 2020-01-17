package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.*;

import java.util.HashMap;

public class PractitionerRoleValueMapper {

    public PractitionerRole practitionerRoleValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        PractitionerRole practitionerRole = new PractitionerRole();

        practitionerRole.setLanguage(hgscReport.getLanguage());

        //Code
        practitionerRole.addCode(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/practitioner-role")
                .setCode("doctor").setDisplay("Doctor")));

        return practitionerRole;
    }

    public PractitionerRole practitionerRoleGeneticistOneValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        PractitionerRole practitionerRoleGeneticistOne = new PractitionerRole();

        practitionerRoleGeneticistOne.setLanguage(hgscReport.getLanguage());

        //Code
        practitionerRoleGeneticistOne.addCode(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                .setCode("159141008").setDisplay("Geneticist")).setText(hgscReport.getPractitionerData().get(0).getQualification().get(1)
                + ", " + hgscReport.getPractitionerData().get(0).getRole()));

        return practitionerRoleGeneticistOne;
    }

    public PractitionerRole practitionerRoleGeneticistTwoValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        PractitionerRole practitionerRoleGeneticistTwo = new PractitionerRole();

        practitionerRoleGeneticistTwo.setLanguage(hgscReport.getLanguage());

        //Code
        practitionerRoleGeneticistTwo.addCode(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                .setCode("159141008").setDisplay("Geneticist")).setText(hgscReport.getPractitionerData().get(1).getQualification().get(1)
                + ", " + hgscReport.getPractitionerData().get(1).getRole()));

        return practitionerRoleGeneticistTwo;
    }
}
