package edu.bcm.hgsc.fhir.utils.validator;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import edu.bcm.hgsc.fhir.models.HgscReport;
import edu.bcm.hgsc.fhir.models.PgxDatum;
import edu.bcm.hgsc.fhir.models.PgxDrugRec;
import edu.bcm.hgsc.fhir.utils.mapper.PgxMedImplicationsValueMapper;
import org.hl7.fhir.r4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class PgxMedImplicationsValidator {

    private static Logger logger = LoggerFactory.getLogger(PgxMedImplicationsValidator.class);

    PgxMedImplicationsValueMapper pgxMedValueMapper = new PgxMedImplicationsValueMapper();

    public boolean validatePgxResult_1001_ById(String resourceId, HgscReport hgscReport, IGenericClient client, HashMap<String, String> pgxDataPhenotypeCodeMap) {

        Observation pgxResult_1001 = client.read().resource(Observation.class).withId(resourceId).execute();

        PgxDatum pgxData = pgxMedValueMapper.getPgxDataByGeneSymbol(hgscReport, "CYP2C19");

        boolean validateCodeableConceptResult = true;
        if(pgxData != null && pgxData.getPhenotype() != null && !pgxData.getPhenotype().equals("")) {
            if(pgxDataPhenotypeCodeMap.get(pgxData.getPhenotype()) != null){
                validateCodeableConceptResult = pgxResult_1001.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxData.getPhenotype());
            }else{
                validateCodeableConceptResult = pgxResult_1001.getValueCodeableConcept().getText().equals(pgxData.getPhenotype());
            }
        }

        boolean validatePgxDrugRecResult = true;
        if(pgxData.getPgxDrugRecommendation() != null && pgxData.getPgxDrugRecommendation().size() > 0) {
            for(int i = 0; i < pgxData.getPgxDrugRecommendation().size(); i++) {
                PgxDrugRec pgxDrugRec = pgxData.getPgxDrugRecommendation().get(i);
                Observation.ObservationComponentComponent component = pgxResult_1001.getComponent().get(i);

                validatePgxDrugRecResult = component.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxDrugRec.getDrug());

                RelatedArtifact relatedArtifact = (RelatedArtifact) (component.getExtensionByUrl("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact").getValue());

                validatePgxDrugRecResult = validatePgxDrugRecResult && relatedArtifact.getUrl().equals(pgxDrugRec.getRecommendation());

                if(!validatePgxDrugRecResult) break;
            }
        }

        if(!validateCodeableConceptResult || !validatePgxDrugRecResult) {
            logger.error("Failed to validate FHIR PgxResult_1001 resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }

    public boolean validatePgxResult_2001_ById(String resourceId, HgscReport hgscReport, IGenericClient client, HashMap<String, String> pgxDataPhenotypeCodeMap) {

        Observation pgxResult_2001 = client.read().resource(Observation.class).withId(resourceId).execute();

        PgxDatum pgxData = pgxMedValueMapper.getPgxDataByGeneSymbol(hgscReport, "DPYD");

        boolean validateCodeableConceptResult = true;
        if(pgxData != null && pgxData.getPhenotype() != null && !pgxData.getPhenotype().equals("")) {
            if(pgxDataPhenotypeCodeMap.get(pgxData.getPhenotype()) != null){
                validateCodeableConceptResult = pgxResult_2001.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxData.getPhenotype());
            }else{
                validateCodeableConceptResult = pgxResult_2001.getValueCodeableConcept().getText().equals(pgxData.getPhenotype());
            }
        }

        boolean validatePgxDrugRecResult = true;
        if(pgxData.getPgxDrugRecommendation() != null && pgxData.getPgxDrugRecommendation().size() > 0) {
            for(int i = 0; i < pgxData.getPgxDrugRecommendation().size(); i++) {
                PgxDrugRec pgxDrugRec = pgxData.getPgxDrugRecommendation().get(i);
                Observation.ObservationComponentComponent component = pgxResult_2001.getComponent().get(i);

                validatePgxDrugRecResult = component.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxDrugRec.getDrug());

                RelatedArtifact relatedArtifact = (RelatedArtifact) (component.getExtensionByUrl("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact").getValue());

                validatePgxDrugRecResult = validatePgxDrugRecResult && relatedArtifact.getUrl().equals(pgxDrugRec.getRecommendation());

                if(!validatePgxDrugRecResult) break;
            }
        }

        if(!validateCodeableConceptResult || !validatePgxDrugRecResult) {
            logger.error("Failed to validate FHIR PgxResult_2001 resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }

    public boolean validatePgxResult_3001_ById(String resourceId, HgscReport hgscReport, IGenericClient client, HashMap<String, String> pgxDataPhenotypeCodeMap) {

        Observation pgxResult_3001 = client.read().resource(Observation.class).withId(resourceId).execute();

        PgxDatum pgxData = pgxMedValueMapper.getPgxDataByGeneSymbol(hgscReport, "IFNL3");

        boolean validateCodeableConceptResult = true;
        if(pgxData != null && pgxData.getPhenotype() != null && !pgxData.getPhenotype().equals("")) {
            if(pgxDataPhenotypeCodeMap.get(pgxData.getPhenotype()) != null){
                validateCodeableConceptResult = pgxResult_3001.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxData.getPhenotype());
            }else{
                validateCodeableConceptResult = pgxResult_3001.getValueCodeableConcept().getText().equals(pgxData.getPhenotype());
            }
        }

        boolean validatePgxDrugRecResult = true;
        if(pgxData.getPgxDrugRecommendation() != null && pgxData.getPgxDrugRecommendation().size() > 0) {
            for(int i = 0; i < pgxData.getPgxDrugRecommendation().size(); i++) {
                PgxDrugRec pgxDrugRec = pgxData.getPgxDrugRecommendation().get(i);
                Observation.ObservationComponentComponent component = pgxResult_3001.getComponent().get(i);

                validatePgxDrugRecResult = component.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxDrugRec.getDrug());

                RelatedArtifact relatedArtifact = (RelatedArtifact) (component.getExtensionByUrl("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact").getValue());

                validatePgxDrugRecResult = validatePgxDrugRecResult && relatedArtifact.getUrl().equals(pgxDrugRec.getRecommendation());

                if(!validatePgxDrugRecResult) break;
            }
        }

        if(!validateCodeableConceptResult || !validatePgxDrugRecResult) {
            logger.error("Failed to validate FHIR PgxResult_3001 resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }

    public boolean validatePgxResult_4001_ById(String resourceId, HgscReport hgscReport, IGenericClient client, HashMap<String, String> pgxDataPhenotypeCodeMap) {

        Observation pgxResult_4001 = client.read().resource(Observation.class).withId(resourceId).execute();

        PgxDatum pgxData = pgxMedValueMapper.getPgxDataByGeneSymbol(hgscReport, "SLCO1B1");

        boolean validateCodeableConceptResult = true;
        if(pgxData != null && pgxData.getPhenotype() != null && !pgxData.getPhenotype().equals("")) {
            if(pgxDataPhenotypeCodeMap.get(pgxData.getPhenotype()) != null){
                validateCodeableConceptResult = pgxResult_4001.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxData.getPhenotype());
            }else{
                validateCodeableConceptResult = pgxResult_4001.getValueCodeableConcept().getText().equals(pgxData.getPhenotype());
            }
        }

        boolean validatePgxDrugRecResult = true;
        if(pgxData.getPgxDrugRecommendation() != null && pgxData.getPgxDrugRecommendation().size() > 0) {
            for(int i = 0; i < pgxData.getPgxDrugRecommendation().size(); i++) {
                PgxDrugRec pgxDrugRec = pgxData.getPgxDrugRecommendation().get(i);
                Observation.ObservationComponentComponent component = pgxResult_4001.getComponent().get(i);

                validatePgxDrugRecResult = component.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxDrugRec.getDrug());

                RelatedArtifact relatedArtifact = (RelatedArtifact) (component.getExtensionByUrl("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact").getValue());

                validatePgxDrugRecResult = validatePgxDrugRecResult && relatedArtifact.getUrl().equals(pgxDrugRec.getRecommendation());

                if(!validatePgxDrugRecResult) break;
            }
        }

        if(!validateCodeableConceptResult || !validatePgxDrugRecResult) {
            logger.error("Failed to validate FHIR PgxResult_4001 resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }

    public boolean validatePgxResult_5001_ById(String resourceId, HgscReport hgscReport, IGenericClient client, HashMap<String, String> pgxDataPhenotypeCodeMap) {

        Observation pgxResult_5001 = client.read().resource(Observation.class).withId(resourceId).execute();

        PgxDatum pgxData = pgxMedValueMapper.getPgxDataByGeneSymbol(hgscReport, "TPMT");

        boolean validateCodeableConceptResult = true;
        if(pgxData != null && pgxData.getPhenotype() != null && !pgxData.getPhenotype().equals("")) {
            if(pgxDataPhenotypeCodeMap.get(pgxData.getPhenotype()) != null){
                validateCodeableConceptResult = pgxResult_5001.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxData.getPhenotype());
            }else{
                validateCodeableConceptResult = pgxResult_5001.getValueCodeableConcept().getText().equals(pgxData.getPhenotype());
            }
        }

        boolean validatePgxDrugRecResult = true;
        if(pgxData.getPgxDrugRecommendation() != null && pgxData.getPgxDrugRecommendation().size() > 0) {
            for(int i = 0; i < pgxData.getPgxDrugRecommendation().size(); i++) {
                PgxDrugRec pgxDrugRec = pgxData.getPgxDrugRecommendation().get(i);
                Observation.ObservationComponentComponent component = pgxResult_5001.getComponent().get(i);

                validatePgxDrugRecResult = component.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxDrugRec.getDrug());

                RelatedArtifact relatedArtifact = (RelatedArtifact) (component.getExtensionByUrl("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact").getValue());

                validatePgxDrugRecResult = validatePgxDrugRecResult && relatedArtifact.getUrl().equals(pgxDrugRec.getRecommendation());

                if(!validatePgxDrugRecResult) break;
            }
        }

        if(!validateCodeableConceptResult || !validatePgxDrugRecResult) {
            logger.error("Failed to validate FHIR PgxResult_5001 resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }

    public boolean validatePgxResult_6001_ById(String resourceId, HgscReport hgscReport, IGenericClient client, HashMap<String, String> pgxDataPhenotypeCodeMap) {

        Observation pgxResult_6001 = client.read().resource(Observation.class).withId(resourceId).execute();

        PgxDatum pgxData = pgxMedValueMapper.getPgxDataByGeneSymbol(hgscReport, "CYP2C9");

        boolean validateCodeableConceptResult = true;
        if(pgxData != null && pgxData.getPhenotype() != null && !pgxData.getPhenotype().equals("")) {
            if(pgxDataPhenotypeCodeMap.get(pgxData.getPhenotype()) != null){
                validateCodeableConceptResult = pgxResult_6001.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxData.getPhenotype());
            }else{
                validateCodeableConceptResult = pgxResult_6001.getValueCodeableConcept().getText().equals(pgxData.getPhenotype());
            }
        }

        boolean validatePgxDrugRecResult = true;
        if(pgxData.getPgxDrugRecommendation() != null && pgxData.getPgxDrugRecommendation().size() > 0) {
            for(int i = 0; i < pgxData.getPgxDrugRecommendation().size(); i++) {
                PgxDrugRec pgxDrugRec = pgxData.getPgxDrugRecommendation().get(i);
                Observation.ObservationComponentComponent component = pgxResult_6001.getComponent().get(i);

                validatePgxDrugRecResult = component.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxDrugRec.getDrug());

                RelatedArtifact relatedArtifact = (RelatedArtifact) (component.getExtensionByUrl("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/RelatedArtifact").getValue());

                validatePgxDrugRecResult = validatePgxDrugRecResult && relatedArtifact.getUrl().equals(pgxDrugRec.getRecommendation());

                if(!validatePgxDrugRecResult) break;
            }
        }

        if(!validateCodeableConceptResult || !validatePgxDrugRecResult) {
            logger.error("Failed to validate FHIR PgxResult_6001 resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }
}
