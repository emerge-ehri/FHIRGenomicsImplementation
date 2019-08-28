package com.hgsc.fhir.utils.mapper;

import com.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Narrative;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

import java.util.HashMap;

public class ServiceRequestValueMapper {

    public ServiceRequest serviceRequestValueMapping(ServiceRequest serviceRequest, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport) {

        //Text
        serviceRequest.setText(new Narrative().setStatus(Narrative.NarrativeStatus.EMPTY).setDiv(new XhtmlNode().setValue("fixed")));
        //Profile
        serviceRequest.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/servicerequest");
        //Identifier
//        if (mappingConfig.containsKey("HgscEmergeReport.reportIdentifier")) {
//            serviceRequest.addIdentifier(new Identifier().setSystem("").setValue(hgscEmergeReport.getReportIdentifier()));
//        }
        //InstantiatesCanonical
        serviceRequest.addInstantiatesCanonical("http://amc1.edu/lab-sc1/fhir/PlanDefinition/emerge-chop-pnl");
        //Status--???
        if (mappingConfig.containsKey("HgscEmergeReport.labStatus")) {
            serviceRequest.setStatus(ServiceRequest.ServiceRequestStatus.COMPLETED
                    //.fromCode(hgscEmergeReport.getLabStatus().toLowerCase())
            );
        }
        //Intent-???fixed
        serviceRequest.setIntent(ServiceRequest.ServiceRequestIntent.FILLERORDER);
        //Category
        serviceRequest.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                .setCode("108252007").setDisplay("Laboratory procedure")));
        //Code
        serviceRequest.setCode(new CodeableConcept().addCoding(new Coding().setSystem("")
                .setCode("").setDisplay("")).setText("eMERGE-Seq Panel - CHOP"));
        //AuthoredOn

        //PerformerType
        serviceRequest.setPerformerType(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                .setCode("410852461000087105").setDisplay("Clinical genetics service")));
        //ReasonCode
        serviceRequest.addReasonCode(new CodeableConcept().addCoding(new Coding().setSystem("https://emerge.geneinsight.com")
                .setCode("10093-7").setDisplay("Not selected for trait")));

        return serviceRequest;
    }
}
