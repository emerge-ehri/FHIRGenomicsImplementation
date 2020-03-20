package edu.bcm.hgsc.fhir.utils.validator;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import edu.bcm.hgsc.fhir.models.HgscReport;
import org.apache.log4j.Logger;
import org.hl7.fhir.r4.model.*;

public class ObsOverallValidator {

    private static Logger logger = Logger.getLogger(ObsOverallValidator.class);

    public boolean validateObsOverallById(String resourceId, HgscReport hgscReport, IGenericClient client) {

        Observation obsOverall = client.read().resource(Observation.class).withId(resourceId).execute();

        boolean validatePanelSummaryResult = true;
        if(hgscReport.getPanelSummary() != null && !hgscReport.getPanelSummary().equals("")) {
            validatePanelSummaryResult = obsOverall.getExtensionByUrl("https://emerge.hgsc.bcm.edu/fhir/StructureDefinition/interpretation-summary-text").getValue().toString().equals(hgscReport.getPanelSummary());
        }

        boolean validateValueCodeableConceptResult = true;
        if(hgscReport.getOverallInterpretation() != null && !hgscReport.getOverallInterpretation().equals("")) {
            validateValueCodeableConceptResult = obsOverall.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(hgscReport.getOverallInterpretation());
        }

        if(hgscReport.getOverallInterpretation().toLowerCase().equals("positive")
                && hgscReport.getVariants() != null && hgscReport.getVariants().size() > 0) {
            if(obsOverall.getDerivedFrom().size() != hgscReport.getVariants().size()) {
                logger.error("Failed to validate FHIR ObsOverall resource with resourceId:" + resourceId);
                return false;
            }
        }

        if(!validatePanelSummaryResult || !validateValueCodeableConceptResult) {
            logger.error("Failed to validate FHIR ObsOverall resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }
}
