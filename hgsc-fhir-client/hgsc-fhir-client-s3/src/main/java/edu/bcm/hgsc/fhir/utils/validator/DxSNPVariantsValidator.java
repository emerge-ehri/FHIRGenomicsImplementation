package edu.bcm.hgsc.fhir.utils.validator;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import edu.bcm.hgsc.fhir.models.HgscReport;
import edu.bcm.hgsc.fhir.models.Variant;
import org.apache.log4j.Logger;
import org.hl7.fhir.r4.model.Observation;

public class DxSNPVariantsValidator {

    private static Logger logger = Logger.getLogger(DxSNPVariantsValidator.class);

    public boolean validateDxSNPVariantsById(String resourceId, HgscReport hgscReport, IGenericClient client, Variant v) {

        Observation snpV = client.read().resource(Observation.class).withId(resourceId).execute();

        boolean validateChromosomeResult = true;
        boolean validateRefAlleleResult = true;
        boolean validateAltAlleleResult = true;
        boolean validateStartEndResult = true;
        boolean validateAllelicStateResult = true;
        boolean validateGeneStudiedResult = true;
        boolean validateDnaChgResult = true;

        for(Observation.ObservationComponentComponent component : snpV.getComponent()) {
            String code = component.getCode().getCodingFirstRep().getCode();
            switch (code) {
                case "48000-4":
                    //Component:chromosome-identifier (extension)
                    if(v.getChromosome() != null && !v.getChromosome().equals("")) {
                        validateChromosomeResult = component.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(v.getChromosome());
                    }
                    break;
                case "69547-8":
                    //Component:ref-allele
                    if(v.getRef() != null && !v.getRef().equals("")) {
                        validateRefAlleleResult = component.getValueStringType().toString().equals(v.getRef());
                    }
                    break;
                case "69551-0":
                    //Component:alt-allele
                    if(v.getAlt() != null && !v.getAlt().equals("")) {
                        validateAltAlleleResult = component.getValueStringType().toString().equals(v.getAlt());
                    }
                    break;
                case "81254-5":
                    //Component:exact-start-end
                    if(v.getPosition() != null && !v.getPosition().equals("")) {
                        validateStartEndResult = component.getValueRange().getLow().getValue().toString().equals(v.getPosition());
                    }
                    break;
                case "53034-5":
                    //Component:allelic-state
                    if(v.getZygosity() != null && !v.getZygosity().equals("")) {
                        validateAllelicStateResult = component.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(v.getZygosity());
                    }
                    break;
                case "48018-6":
                    //Component:gene-studied
                    if(v.getHgncID() != null && !v.getHgncID().equals("") && v.getGene() != null && !v.getGene().equals("")) {
                        validateGeneStudiedResult = component.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(v.getGene());
                    }
                    break;
                case "48004-6":
                    //Component:dna-chg
                    if(v.getCDNA() != null && !v.getCDNA().equals("")) {
                        validateDnaChgResult = component.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(v.getCDNA());
                    }
                    break;
            }
        }

        if(!validateChromosomeResult || !validateRefAlleleResult || !validateAltAlleleResult || !validateStartEndResult
                || !validateAllelicStateResult || !validateGeneStudiedResult || !validateDnaChgResult) {
            logger.error("Failed to validate FHIR DxSNPVariant resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }
}
