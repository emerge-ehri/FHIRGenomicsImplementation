package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.ServiceRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class ServiceRequestValueMapper {

    public ServiceRequest serviceRequestValueMapping(ServiceRequest serviceRequest, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport, SimpleDateFormat sdf) throws ParseException {

        //Profile
        serviceRequest.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/servicerequest");
        //Identifier
        if (mappingConfig.containsKey("HgscEmergeReport.accessionNumber")) {
            serviceRequest.addIdentifier(new Identifier().setSystem("https://emerge.hgsc.bcm.edu/").setValue(hgscEmergeReport.getAccessionNumber())
                    .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                            .setCode("ACSN").setDisplay("Accession ID"))));
        }
        //InstantiatesCanonical
        //serviceRequest.addInstantiatesCanonical("http://amc1.edu/lab-sc1/fhir/PlanDefinition/emerge-chop-pnl");
        //Status
        serviceRequest.setStatus(ServiceRequest.ServiceRequestStatus.COMPLETED);
        //Intent
        serviceRequest.setIntent(ServiceRequest.ServiceRequestIntent.FILLERORDER);
        //Category
        serviceRequest.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                .setCode("108252007").setDisplay("Laboratory procedure")));
        //Code
        if (mappingConfig.containsKey("HgscEmergeReport.testname")) {
            serviceRequest.setCode(new CodeableConcept().addCoding(new Coding().setSystem("https://hgsc.bcm.edu/lab-test-codes/")
                    .setCode("emerge-seq-ngs-pnl").setDisplay("eMERGE-Seq NGS Panel")).setText(hgscEmergeReport.getTestName()));
        }
        //AuthoredOn
        if (mappingConfig.containsKey("HgscEmergeReport.onDate")) {
            serviceRequest.setAuthoredOn(sdf.parse(hgscEmergeReport.getOnDate()));
        }
        //PerformerType
        serviceRequest.setPerformerType(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                .setCode("310049001").setDisplay("Clinical genetics service")));
        //ReasonCode
        if (mappingConfig.containsKey("HgscEmergeReport.indicationForTesting")) {
            serviceRequest.addReasonCode(new CodeableConcept().addCoding(new Coding().setSystem("https://emerge.geneinsight.com")
                    .setCode("10093-7").setDisplay(hgscEmergeReport.getIndicationForTesting())));
        }

        return serviceRequest;
    }
}
