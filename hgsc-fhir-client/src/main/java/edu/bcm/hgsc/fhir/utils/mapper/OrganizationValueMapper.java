package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.*;

import java.util.HashMap;

public class OrganizationValueMapper {

    public Organization organizationHGSCValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Organization organizationHGSC = new Organization();

        organizationHGSC.setLanguage(hgscReport.getLanguage());

        //Profile
        organizationHGSC.getMeta().addProfile("http://hl7.org/fhir/us/core/StructureDefinition/us-core-organization");
        //Identifier
        organizationHGSC.addIdentifier(new Identifier().setSystem("urn:oid:00000").setValue("00000"))
                .addIdentifier(new Identifier().setSystem("http://www.hgsc.bcm.edu").setValue("00000"));
        //Active
        organizationHGSC.setActive(true);
        //Name
        organizationHGSC.setName("Human Genome Sequencing Center Clinical Laboratory");
        //Telecom
        organizationHGSC.addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.PHONE).setValue("(+1) 713-000-0000"))
                .addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.FAX).setValue("(+1) 713-000-0000"))
                .addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.URL).setValue("https://www.hgsc.bcm.edu/"))
                .addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.EMAIL).setValue("sample-email@hgsc.bcm.edu"));
        //Address
        organizationHGSC.addAddress(new Address().addLine("One Baylor Plaza").setCity("Houston").setState("TX").setPostalCode("77030").setCountry("USA"));

        return organizationHGSC;
    }

    public Organization organizationBCMValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Organization organizationBCM = new Organization();

        organizationBCM.setLanguage(hgscReport.getLanguage());

        //Active
        organizationBCM.setActive(true);
        //Name
        organizationBCM.setName("Baylor College of Medicine");
        //Telecom
        organizationBCM.addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.PHONE).setValue("(+1) 713-000-0000"))
                .addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.URL).setValue("https://www.bcm.edu/"));
        //Address
        organizationBCM.addAddress(new Address().addLine("One Baylor Plaza").setCity("Houston").setState("TX").setPostalCode("77030").setCountry("USA"));

        return organizationBCM;
    }

    public Organization organizationCHPValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Organization organizationCHP = new Organization();

        organizationCHP.setLanguage(hgscReport.getLanguage());

        //Active
        organizationCHP.setActive(true);
        //Name
        organizationCHP.setName("Sample CLient");
        //Address
        if (hgscReport.getOrderingPhysicianAddress() != null && !hgscReport.getOrderingPhysicianAddress().equals("")) {
            organizationCHP.addAddress(new Address().setText(hgscReport.getOrderingPhysicianAddress()));
        }

        return organizationCHP;
    }
}
