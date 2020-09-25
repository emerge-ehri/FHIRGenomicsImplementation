package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.ServiceRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class ServiceRequestValueMapper {

    public ServiceRequest serviceRequestValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport, SimpleDateFormat sdf) throws ParseException {

        ServiceRequest serviceRequest = new ServiceRequest();

        serviceRequest.setLanguage(hgscReport.getLanguage());

        //Profile
        serviceRequest.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/servicerequest");
        //Identifier
        if (mappingConfig.containsKey("HgscReport.patientSampleID")) {
            if(hgscReport.getPatientSampleID() != null && !hgscReport.getPatientSampleID().equals("")) {
                serviceRequest.addIdentifier(new Identifier().setSystem("http://namingsystem.org/").setValue(hgscReport.getPatientSampleID())
                        .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                                .setCode("ACSN").setDisplay("Accession ID"))));
            }
        }
        //Status
        serviceRequest.setStatus(ServiceRequest.ServiceRequestStatus.COMPLETED);
        //Intent
        serviceRequest.setIntent(ServiceRequest.ServiceRequestIntent.FILLERORDER);
        //Category
        serviceRequest.addCategory(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                .setCode("108252007").setDisplay("Laboratory procedure")));
        //Code
        if (mappingConfig.containsKey("HgscReport.testname")) {
            if(hgscReport.getTestName() != null && !hgscReport.getTestName().equals("")) {
                serviceRequest.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://namingsystem.org/lab-test-codes/")
                        .setCode("emerge-seq-ngs-pnl").setDisplay("eMERGE-Seq NGS Panel")).setText(hgscReport.getTestName()));
            }
        }
        //AuthoredOn
        if (mappingConfig.containsKey("HgscReport.onDate")) {
            if(hgscReport.getOnDate() != null && !hgscReport.getOnDate().equals("")) {
                serviceRequest.setAuthoredOn(sdf.parse(hgscReport.getOnDate()));
            }
        }
        //PerformerType
        serviceRequest.setPerformerType(new CodeableConcept().addCoding(new Coding().setSystem("http://snomed.info/sct")
                .setCode("310049001").setDisplay("Clinical genetics service")));
        //ReasonCode
        if (mappingConfig.containsKey("HgscReport.indicationForTesting")) {
            if(hgscReport.getIndicationForTesting() != null && !hgscReport.getIndicationForTesting().equals("")) {
                serviceRequest.addReasonCode(new CodeableConcept().addCoding(new Coding().setSystem("http://namingsystem.org")
                        .setCode("10093-7").setDisplay(hgscReport.getIndicationForTesting())));
            }
        }

        return serviceRequest;
    }
}
