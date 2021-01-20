package edu.bcm.hgsc.fhir.utils.validator;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceRequestValidator {

    private static Logger logger = LoggerFactory.getLogger(ServiceRequestValidator.class);

    public boolean validateServiceRequestById(String resourceId, HgscReport hgscReport, IGenericClient client) {

        ServiceRequest serviceRequest = client.read().resource(ServiceRequest.class).withId(resourceId).execute();

        boolean validateIdentifierResult = true;
        if(hgscReport.getPatientSampleID() != null && !hgscReport.getPatientSampleID().equals("")) {
            validateIdentifierResult = serviceRequest.getIdentifierFirstRep().getValue().equals(hgscReport.getPatientSampleID());
        }

        boolean validateReasonCodeResult = true;
        if(hgscReport.getIndicationForTesting() != null && !hgscReport.getIndicationForTesting().equals("")) {
            validateReasonCodeResult = serviceRequest.getReasonCodeFirstRep().getCodingFirstRep().getDisplay().equals(hgscReport.getIndicationForTesting());
        }

        if(!validateIdentifierResult || !validateReasonCodeResult) {
            logger.error("Failed to validate FHIR ServiceRequest resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }
}
