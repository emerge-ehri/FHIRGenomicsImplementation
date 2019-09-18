package com.hgsc.fhir.utils.mapper;

import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.Narrative;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

public class OrganizationBCMValueMapper {

    public Organization organizationBCMValueMapping() {

        Organization organization = new Organization();

        //Text
        organization.setText(new Narrative().setStatus(Narrative.NarrativeStatus.EMPTY).setDiv(new XhtmlNode().setValue("fixed")));
        //Active
        organization.setActive(true);
        //Name
        organization.setName("Baylor College of Medicine");
        //Telecom
        organization.addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.PHONE).setValue("(+1) 713-798-4951"))
                .addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.URL).setValue("https://www.bcm.edu/"));
        //Address
        organization.addAddress(new Address().addLine("One Baylor Plaza").setCity("Houston").setState("TX").setPostalCode("77030").setCountry("USA"));

        return organization;
    }
}
