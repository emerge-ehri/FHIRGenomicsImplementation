package edu.bcm.hgsc.fhir.utils.validator;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import edu.bcm.hgsc.fhir.models.HgscReport;
import edu.bcm.hgsc.fhir.models.PgxDatum;
import edu.bcm.hgsc.fhir.utils.mapper.PgxMedImplicationsValueMapper;
import org.apache.log4j.Logger;
import org.hl7.fhir.r4.model.Observation;

public class PgxgenotypesValidator {

    private static Logger logger = Logger.getLogger(PgxgenotypesValidator.class);

    PgxMedImplicationsValueMapper pgxMedImplicationsValueMapper = new PgxMedImplicationsValueMapper();

    public boolean validatePgxGeno_1001_ById(String resourceId, HgscReport hgscReport, IGenericClient client) {

        Observation pgxGeno_1001 = client.read().resource(Observation.class).withId(resourceId).execute();

        PgxDatum pgxData = pgxMedImplicationsValueMapper.getPgxDataByGeneSymbol(hgscReport, "CYP2C19");

        boolean validateCodeableConceptResult = true;
        if(pgxData != null && pgxData.getDiplotype() != null && !pgxData.getDiplotype().equals("")) {
            validateCodeableConceptResult = pgxGeno_1001.getValueCodeableConcept().getText().equals(pgxData.getDiplotype());
        }

        boolean validateGeneSymbolResult = true;
        if(pgxData != null && pgxData.getGeneSymbol() != null && !pgxData.getGeneSymbol().equals("")) {
            Observation.ObservationComponentComponent component = pgxGeno_1001.getComponentFirstRep();
            validateGeneSymbolResult = component.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxData.getGeneSymbol());
        }

        if(!validateCodeableConceptResult || !validateGeneSymbolResult) {
            logger.error("Failed to validate FHIR PgxGeno_1001 resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }

    public boolean validatePgxGeno_2001_ById(String resourceId, HgscReport hgscReport, IGenericClient client) {

        Observation pgxGeno_2001 = client.read().resource(Observation.class).withId(resourceId).execute();

        PgxDatum pgxData = pgxMedImplicationsValueMapper.getPgxDataByGeneSymbol(hgscReport, "DPYD");

        boolean validateCodeableConceptResult = true;
        if(pgxData != null && pgxData.getDiplotype() != null && !pgxData.getDiplotype().equals("")) {
            validateCodeableConceptResult = pgxGeno_2001.getValueCodeableConcept().getText().equals(pgxData.getDiplotype());
        }

        boolean validateGeneSymbolResult = true;
        if(pgxData != null && pgxData.getGeneSymbol() != null && !pgxData.getGeneSymbol().equals("")) {
            Observation.ObservationComponentComponent component = pgxGeno_2001.getComponentFirstRep();
            validateGeneSymbolResult = component.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxData.getGeneSymbol());
        }

        if(!validateCodeableConceptResult || !validateGeneSymbolResult) {
            logger.error("Failed to validate FHIR PgxGeno_2001 resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }

    public boolean validatePgxGeno_3001_ById(String resourceId, HgscReport hgscReport, IGenericClient client) {

        Observation pgxGeno_3001 = client.read().resource(Observation.class).withId(resourceId).execute();

        PgxDatum pgxData = pgxMedImplicationsValueMapper.getPgxDataByGeneSymbol(hgscReport, "IFNL3");

        boolean validateCodeableConceptResult = true;
        if(pgxData != null && pgxData.getDiplotype() != null && !pgxData.getDiplotype().equals("")) {
            validateCodeableConceptResult = pgxGeno_3001.getValueCodeableConcept().getText().equals(pgxData.getDiplotype());
        }

        boolean validateGeneSymbolResult = true;
        if(pgxData != null && pgxData.getGeneSymbol() != null && !pgxData.getGeneSymbol().equals("")) {
            Observation.ObservationComponentComponent component = pgxGeno_3001.getComponentFirstRep();
            validateGeneSymbolResult = component.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxData.getGeneSymbol());
        }

        if(!validateCodeableConceptResult || !validateGeneSymbolResult) {
            logger.error("Failed to validate FHIR PgxGeno_3001 resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }

    public boolean validatePgxGeno_4001_ById(String resourceId, HgscReport hgscReport, IGenericClient client) {

        Observation pgxGeno_4001 = client.read().resource(Observation.class).withId(resourceId).execute();

        PgxDatum pgxData = pgxMedImplicationsValueMapper.getPgxDataByGeneSymbol(hgscReport, "SLCO1B1");

        boolean validateCodeableConceptResult = true;
        if(pgxData != null && pgxData.getDiplotype() != null && !pgxData.getDiplotype().equals("")) {
            validateCodeableConceptResult = pgxGeno_4001.getValueCodeableConcept().getText().equals(pgxData.getDiplotype());
        }

        boolean validateGeneSymbolResult = true;
        if(pgxData != null && pgxData.getGeneSymbol() != null && !pgxData.getGeneSymbol().equals("")) {
            Observation.ObservationComponentComponent component = pgxGeno_4001.getComponentFirstRep();
            validateGeneSymbolResult = component.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxData.getGeneSymbol());
        }

        if(!validateCodeableConceptResult || !validateGeneSymbolResult) {
            logger.error("Failed to validate FHIR PgxGeno_4001 resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }

    public boolean validatePgxGeno_5001_ById(String resourceId, HgscReport hgscReport, IGenericClient client) {

        Observation pgxGeno_5001 = client.read().resource(Observation.class).withId(resourceId).execute();

        PgxDatum pgxData = pgxMedImplicationsValueMapper.getPgxDataByGeneSymbol(hgscReport, "TPMT");

        boolean validateCodeableConceptResult = true;
        if(pgxData != null && pgxData.getDiplotype() != null && !pgxData.getDiplotype().equals("")) {
            validateCodeableConceptResult = pgxGeno_5001.getValueCodeableConcept().getText().equals(pgxData.getDiplotype());
        }

        boolean validateGeneSymbolResult = true;
        if(pgxData != null && pgxData.getGeneSymbol() != null && !pgxData.getGeneSymbol().equals("")) {
            Observation.ObservationComponentComponent component = pgxGeno_5001.getComponentFirstRep();
            validateGeneSymbolResult = component.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxData.getGeneSymbol());
        }

        if(!validateCodeableConceptResult || !validateGeneSymbolResult) {
            logger.error("Failed to validate FHIR PgxGeno_5001 resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }

    public boolean validatePgxGeno_6001_ById(String resourceId, HgscReport hgscReport, IGenericClient client) {

        Observation pgxGeno_6001 = client.read().resource(Observation.class).withId(resourceId).execute();

        PgxDatum pgxData = pgxMedImplicationsValueMapper.getPgxDataByGeneSymbol(hgscReport, "CYP2C9");

        boolean validateCodeableConceptResult = true;
        if(pgxData != null && pgxData.getDiplotype() != null && !pgxData.getDiplotype().equals("")) {
            validateCodeableConceptResult = pgxGeno_6001.getValueCodeableConcept().getText().equals(pgxData.getDiplotype());
        }

        boolean validateGeneSymbolResult = true;
        if(pgxData != null && pgxData.getGeneSymbol() != null && !pgxData.getGeneSymbol().equals("")) {
            Observation.ObservationComponentComponent component = pgxGeno_6001.getComponentFirstRep();
            validateGeneSymbolResult = component.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxData.getGeneSymbol());
        }

        if(!validateCodeableConceptResult || !validateGeneSymbolResult) {
            logger.error("Failed to validate FHIR PgxGeno_6001 resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }

    public boolean validatePgxGeno_7001_ById(String resourceId, HgscReport hgscReport, IGenericClient client) {

        Observation pgxGeno_7001 = client.read().resource(Observation.class).withId(resourceId).execute();

        PgxDatum pgxData = pgxMedImplicationsValueMapper.getPgxDataByGeneSymbol(hgscReport, "VKORC1");

        boolean validateCodeableConceptResult = true;
        if(pgxData != null && pgxData.getDiplotype() != null && !pgxData.getDiplotype().equals("")) {
            validateCodeableConceptResult = pgxGeno_7001.getValueCodeableConcept().getText().equals(pgxData.getDiplotype());
        }

        boolean validateGeneSymbolResult = true;
        if(pgxData != null && pgxData.getGeneSymbol() != null && !pgxData.getGeneSymbol().equals("")) {
            Observation.ObservationComponentComponent component = pgxGeno_7001.getComponentFirstRep();
            validateGeneSymbolResult = component.getValueCodeableConcept().getCodingFirstRep().getDisplay().equals(pgxData.getGeneSymbol());
        }

        if(!validateCodeableConceptResult || !validateGeneSymbolResult) {
            logger.error("Failed to validate FHIR PgxGeno_7001 resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }
}
