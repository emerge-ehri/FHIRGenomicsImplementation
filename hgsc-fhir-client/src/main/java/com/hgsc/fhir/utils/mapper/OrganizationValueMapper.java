package com.hgsc.fhir.utils.mapper;

import com.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

import java.util.HashMap;

public class OrganizationValueMapper {

    public Organization organizationValueMapping(Organization organization, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport) {

        //Text
        organization.setText(new Narrative().setStatus(Narrative.NarrativeStatus.EMPTY).setDiv(new XhtmlNode().setValue("fixed")));
        //Active
        organization.setActive(true);
        //Type
        organization.addType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/organization-type")
                .setCode("edu").setDisplay("Educational Institute")));
        //Name
        organization.setName("HGSC");
        //Telecom
        organization.addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.PHONE).setValue("(+1) 713-798-6539"))
                .addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.FAX).setValue("(+1) 713-798-5741"))
                .addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.URL).setValue("https://www.hgsc.bcm.edu/"))
                .addTelecom(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.EMAIL).setValue("questions@hgsc.bcm.edu"));
        //Address
        organization.addAddress(new Address().addLine("1 Baylor Plaza").setCity("Houston").setState("TX").setPostalCode("77030").setCountry("USA"));

        return organization;
    }
}
