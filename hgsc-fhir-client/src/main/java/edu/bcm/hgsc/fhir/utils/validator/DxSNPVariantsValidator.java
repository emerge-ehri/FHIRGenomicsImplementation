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

        //Component:chromosome-identifier (extension)
        boolean validateChromosomeResult = true;
        Observation.ObservationComponentComponent component_3 = snpV.getComponent().get(3);
        if(v.getChromosome() != null && !v.getChromosome().equals("")) {
            validateChromosomeResult = component_3.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(v.getChromosome());
        }

        //Component:ref-allele
        boolean validateRefAlleleResult = true;
        Observation.ObservationComponentComponent component_4 = snpV.getComponent().get(4);
        if(v.getRef() != null && !v.getRef().equals("")) {
            validateRefAlleleResult = component_4.getValueStringType().toString().equals(v.getRef());
        }

        //Component:alt-allele
        boolean validateAltAlleleResult = true;
        Observation.ObservationComponentComponent component_5 = snpV.getComponent().get(5);
        if(v.getAlt() != null && !v.getAlt().equals("")) {
            validateAltAlleleResult = component_5.getValueStringType().toString().equals(v.getAlt());
        }

        //Component:exact-start-end
        boolean validateStartEndResult = true;
        Observation.ObservationComponentComponent component_6 = snpV.getComponent().get(6);
        if(v.getPosition() != null && !v.getPosition().equals("")) {
            validateStartEndResult = component_6.getValueRange().getLow().getValue().toString().equals(v.getPosition());
        }

        //Component:allelic-state
        boolean validateAllelicStateResult = true;
        Observation.ObservationComponentComponent component_7 = snpV.getComponent().get(7);
        if(v.getZygosity() != null && !v.getZygosity().equals("")) {
            validateAllelicStateResult = component_7.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(v.getZygosity());
        }

        //Component:gene-studied
        boolean validateGeneStudiedResult = true;
        Observation.ObservationComponentComponent component_8 = snpV.getComponent().get(8);
        if(v.getHgncID() != null && !v.getHgncID().equals("") && v.getGene() != null && !v.getGene().equals("")) {
            validateGeneStudiedResult = component_8.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(v.getGene());
        }

        //Component:dna-chg
        boolean validateDnaChgResult = true;
        Observation.ObservationComponentComponent component_11 = snpV.getComponent().get(11);
        if(v.getCDNA() != null && !v.getCDNA().equals("")) {
            validateDnaChgResult = component_11.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(v.getCDNA());
        }

        if(!validateChromosomeResult || !validateRefAlleleResult || !validateAltAlleleResult || !validateStartEndResult
                || !validateAllelicStateResult || !validateGeneStudiedResult || !validateDnaChgResult) {
            logger.error("Failed to validate FHIR DxSNPVariant resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }
}
