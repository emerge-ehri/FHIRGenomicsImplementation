package edu.bcm.hgsc.fhir.utils.validator;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DxPanelValidator {

    private static Logger logger = LoggerFactory.getLogger(DxPanelValidator.class);

    public boolean validateDxPanelById(String resourceId, HgscReport hgscReport, IGenericClient client) {

        Observation dxPanel = client.read().resource(Observation.class).withId(resourceId).execute();

        int snpVinhDPNum = 0;
        if(hgscReport.getOverallInterpretation().toLowerCase().equals("positive")
                && hgscReport.getVariants() != null && hgscReport.getVariants().size() > 0) {
            snpVinhDPNum = hgscReport.getVariants().size();
        }

        if(dxPanel.getHasMember().size() != (snpVinhDPNum * 2 + 1)) {
            logger.error("Failed to validate FHIR DxPanel resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }
}
