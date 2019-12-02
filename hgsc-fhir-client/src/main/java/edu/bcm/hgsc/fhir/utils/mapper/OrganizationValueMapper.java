package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.*;

import java.util.HashMap;

public class OrganizationValueMapper {

    public Organization organizationHGSCValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Organization organizationHGSC = new Organization();

        //Profile
        organizationHGSC.getMeta().addProfile("http://hl7.org/fhir/us/core/StructureDefinition/us-core-organization");
        //Identifier
        organizationHGSC.addIdentifier(new Identifier().setSystem("urn:oid:2.16.840.1.113883.4.7").setValue("45D2027450"))
                .addIdentifier(new Identifier().setSystem("http://www.hgsc.bcm.edu").setValue("8004250"));
        //Active
        organizationHGSC.setActive(true);
        //Name
        organizationHGSC.setName("Human Genome Sequencing Center Clinical Laboratory");
        //Telecom
        organizationHGSC.addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.PHONE).setValue("(+1) 713-798-6539"))
                .addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.FAX).setValue("(+1) 713-798-5741"))
                .addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.URL).setValue("https://www.hgsc.bcm.edu/"))
                .addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.EMAIL).setValue("questions@hgsc.bcm.edu"));
        //Address
        organizationHGSC.addAddress(new Address().addLine("One Baylor Plaza").setCity("Houston").setState("TX").setPostalCode("77030").setCountry("USA"));

        return organizationHGSC;
    }

    public Organization organizationBCMValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Organization organizationBCM = new Organization();

        //Active
        organizationBCM.setActive(true);
        //Name
        organizationBCM.setName("Baylor College of Medicine");
        //Telecom
        organizationBCM.addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.PHONE).setValue("(+1) 713-798-4951"))
                .addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.URL).setValue("https://www.bcm.edu/"));
        //Address
        organizationBCM.addAddress(new Address().addLine("One Baylor Plaza").setCity("Houston").setState("TX").setPostalCode("77030").setCountry("USA"));

        return organizationBCM;
    }

    public Organization organizationCHPValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Organization organizationCHP = new Organization();

        //Active
        organizationCHP.setActive(true);
        //Name
        organizationCHP.setName("Children's Hospital of Philadelphia");

        return organizationCHP;
    }
}
