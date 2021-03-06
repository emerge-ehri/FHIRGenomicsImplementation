package edu.bcm.hgsc.fhir.utils.validator;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.RelatedArtifact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DiagnosticReportValidator {

    private static Logger logger = LoggerFactory.getLogger(DiagnosticReportValidator.class);

    public boolean validateDiagnosticReportById(String resourceId, ArrayList<String> resultURLArr, HgscReport hgscReport, IGenericClient client, SimpleDateFormat sdf, SimpleDateFormat sdf2, String orgName) throws ParseException {

        DiagnosticReport diagnosticReport = client.read().resource(DiagnosticReport.class).withId(resourceId).execute();

        boolean validateIdentifierResult = true;
        if(hgscReport.getReportIdentifier() != null && !hgscReport.getReportIdentifier().equals("")) {
            validateIdentifierResult = diagnosticReport.getIdentifierFirstRep().getValue().equals(hgscReport.getReportIdentifier());
        }

        boolean validateExcidResult = true;
        boolean validatePDFResult = true;
        RelatedArtifact relatedArtifact = (RelatedArtifact)(diagnosticReport.getExtensionByUrl("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/relatedArtifact").getValue());
        validateExcidResult = (relatedArtifact.getDocument().getData() != null && relatedArtifact.getDocument().getData().length != 0);

        byte[] pdfByteArray = diagnosticReport.getPresentedFormFirstRep().getData();
        validatePDFResult = (pdfByteArray != null && pdfByteArray.length != 0);

        boolean validatePatientRefResult = resultURLArr.get(0).contains(diagnosticReport.getSubject().getReference());

        boolean validateIssuedResult = true;
        if(hgscReport.getReportDate() != null && !hgscReport.getReportDate().equals("")) {
            validateIssuedResult = sdf2.format(sdf.parse(diagnosticReport.getIssued().toString())).equals(hgscReport.getReportDate());
        }

        boolean validateSpecimenRefResult = resultURLArr.get(1).contains(diagnosticReport.getSpecimenFirstRep().getReference());

        boolean validateBasedOnRefResult = resultURLArr.get(2).contains(diagnosticReport.getBasedOnFirstRep().getReference());

        boolean validateResultArrRef = diagnosticReport.getResult().size() == 4;

        if(!validateIdentifierResult || !validateExcidResult || !validatePDFResult || !validatePatientRefResult
                || !validateIssuedResult || !validateSpecimenRefResult || !validateBasedOnRefResult || !validateResultArrRef) {
            logger.error("Failed to validate FHIR DiagnosticReport resource with resourceId:" + resourceId);
            return false;
        }

        return true;
    }
}
