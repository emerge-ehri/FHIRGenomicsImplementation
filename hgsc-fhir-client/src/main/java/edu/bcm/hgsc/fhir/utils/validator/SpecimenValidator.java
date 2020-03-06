package edu.bcm.hgsc.fhir.utils.validator;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import edu.bcm.hgsc.fhir.models.HgscReport;
import org.apache.log4j.Logger;
import org.hl7.fhir.r4.model.Specimen;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SpecimenValidator {

    private static Logger logger = Logger.getLogger(SpecimenValidator.class);

    public boolean validateSpecimenById(String resourceId, HgscReport hgscReport, IGenericClient client, SimpleDateFormat sdf, SimpleDateFormat sdf2) throws ParseException {

        Specimen specimen = client.read().resource(Specimen.class).withId(resourceId).execute();

        boolean validateSampleCollectedDateResult = sdf2.format(sdf.parse(specimen.getCollection().getCollectedDateTimeType().getValue().toString())).equals(hgscReport.getSampleCollectedDate());

        if(!validateSampleCollectedDateResult) {
            logger.error("Failed to validate FHIR Specimen resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }
}
