package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.*;

import java.util.HashMap;

public class PractitionerRoleValueMapper {

    public PractitionerRole practitionerRoleValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        PractitionerRole practitionerRole = new PractitionerRole();

        //Identifier
        //practitionerRole.addIdentifier(new Identifier().setSystem("").setValue(""));
        //Code
        practitionerRole.addCode(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/practitioner-role")
                .setCode("doctor").setDisplay("Doctor")));

        return practitionerRole;
    }
}
