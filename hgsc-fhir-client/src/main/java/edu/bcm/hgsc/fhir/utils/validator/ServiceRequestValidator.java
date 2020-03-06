package edu.bcm.hgsc.fhir.utils.validator;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import edu.bcm.hgsc.fhir.models.HgscReport;
import org.apache.log4j.Logger;
import org.hl7.fhir.r4.model.ServiceRequest;

public class ServiceRequestValidator {

    private static Logger logger = Logger.getLogger(ServiceRequestValidator.class);

    public boolean validateServiceRequestById(String resourceId, HgscReport hgscReport, IGenericClient client) {

        ServiceRequest serviceRequest = client.read().resource(ServiceRequest.class).withId(resourceId).execute();

        boolean validateIdentifierResult = serviceRequest.getIdentifierFirstRep().getValue().equals(hgscReport.getPatientSampleID());

        boolean validateReasonCodeResult = serviceRequest.getReasonCodeFirstRep().getCodingFirstRep().getDisplay().equals(hgscReport.getIndicationForTesting());

        if(!validateIdentifierResult || !validateReasonCodeResult) {
            logger.error("Failed to validate FHIR ServiceRequest resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }
}
