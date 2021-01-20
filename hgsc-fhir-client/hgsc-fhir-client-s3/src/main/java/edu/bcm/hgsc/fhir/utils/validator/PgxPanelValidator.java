package edu.bcm.hgsc.fhir.utils.validator;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PgxPanelValidator {

    private static Logger logger = LoggerFactory.getLogger(PgxPanelValidator.class);

    public boolean validatePgxPanelById(String resourceId, HgscReport hgscReport, IGenericClient client) {

        Observation pgxPanel = client.read().resource(Observation.class).withId(resourceId).execute();

        if(pgxPanel.getHasMember().size() != 6) {
            logger.error("Failed to validate FHIR PgxPanel resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }
}
