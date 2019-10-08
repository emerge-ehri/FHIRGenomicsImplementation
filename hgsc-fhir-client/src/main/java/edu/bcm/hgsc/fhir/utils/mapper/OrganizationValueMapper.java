package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.*;

import java.util.HashMap;

public class OrganizationValueMapper {

    public Organization organizationValueMapping(Organization organization, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport) {

        //Identifier
        organization.addIdentifier(new Identifier().setSystem("http://www.hgsc.bcm.edu").setValue("CAP# 8004250 / CLIA# 45D2027450")
                .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                        .setCode("FILL").setDisplay("Filler Identifier"))));
        //Active
        organization.setActive(true);
        //Name
        organization.setName("Human Genome Sequencing Center Clinical Laboratory");
        //Telecom
        organization.addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.PHONE).setValue("(+1) 713-798-6539"))
                .addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.FAX).setValue("(+1) 713-798-5741"))
                .addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.URL).setValue("https://www.hgsc.bcm.edu/"))
                .addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.EMAIL).setValue("questions@hgsc.bcm.edu"));
        //Address
        organization.addAddress(new Address().addLine("One Baylor Plaza").setCity("Houston").setState("TX").setPostalCode("77030").setCountry("USA"));

        return organization;
    }
}
