package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.Organization;

import java.util.HashMap;

public class OrganizationBCMValueMapper {

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
}
