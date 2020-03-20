package edu.bcm.hgsc.fhir.utils.validator;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import edu.bcm.hgsc.fhir.models.Disease;
import edu.bcm.hgsc.fhir.models.HgscReport;
import edu.bcm.hgsc.fhir.models.Variant;
import org.apache.log4j.Logger;
import org.hl7.fhir.r4.model.*;

public class ObsInhDisPathsValidator {

    private static Logger logger = Logger.getLogger(ObsInhDisPathsValidator.class);

    public boolean validateObsInhDisPathById(String resourceId, HgscReport hgscReport, IGenericClient client, Variant v) {

        Observation inhDP = client.read().resource(Observation.class).withId(resourceId).execute();

        boolean validateValueCodeableConceptResult = true;
        if(v.getInterpretation() != null && !v.getInterpretation().equals("")) {
            if(!"Risk factor".equals(v.getInterpretation())){
                validateValueCodeableConceptResult = inhDP.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(v.getInterpretation());
            }else{
                validateValueCodeableConceptResult = inhDP.getValueCodeableConcept().getText().equals(v.getInterpretation());
            }
        }

        boolean validateSummaryTextResult = true;
        if(v.getVariantInterpretation() != null && !v.getVariantInterpretation().equals("")) {
            validateSummaryTextResult = inhDP.getExtensionByUrl("https://emerge.hgsc.bcm.edu/fhir/StructureDefinition/interpretation-summary-text")
                    .getValue().toString().equals(v.getVariantInterpretation());
        }

        if(v.getDiseases() != null && v.getDiseases().size() > 0) {
            for(int i = 0; i < v.getDiseases().size(); i++) {
                Disease d = v.getDiseases().get(i);
                if(d.getOntology() != null && !d.getOntology().equals("")
                        && d.getCode() != null && !d.getCode().equals("")
                        && d.getText() != null && !d.getText().equals("")) {

                    Observation.ObservationComponentComponent component = inhDP.getComponent().get(i);

                    boolean validateOntologyResult = component.getValueCodeableConcept().getCodingFirstRep().getSystem().equals(d.getOntology());
                    boolean validateCodeResult = component.getValueCodeableConcept().getCodingFirstRep().getCode().equals(d.getCode());
                    boolean validateTextResult = component.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(d.getText());

                    if(!validateOntologyResult || !validateCodeResult || !validateTextResult) {
                        logger.error("Failed to validate FHIR ObsInhDisPath resource with resourceId:" + resourceId);
                        return false;
                    }
                }
            }
        }

        boolean validateDerivedFromResult = inhDP.getDerivedFrom().size() == 1;

        if(!validateValueCodeableConceptResult || !validateSummaryTextResult || !validateDerivedFromResult) {
            logger.error("Failed to validate FHIR ObsInhDisPath resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }
}
