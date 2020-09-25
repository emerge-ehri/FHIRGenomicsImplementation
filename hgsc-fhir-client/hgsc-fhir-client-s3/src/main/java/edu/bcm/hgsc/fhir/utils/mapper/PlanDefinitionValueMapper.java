package edu.bcm.hgsc.fhir.utils.mapper;

import ca.uhn.fhir.context.FhirContext;
import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

import java.util.HashMap;

public class PlanDefinitionValueMapper {

    public PlanDefinition planDefinitionValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        PlanDefinition planDefinition = new PlanDefinition();

        planDefinition.setLanguage(hgscReport.getLanguage());

        //Identifier
        if (mappingConfig.containsKey("HgscReport.testName")) {
            if(hgscReport.getTestName() != null && !hgscReport.getTestName().equals("")) {
                planDefinition.addIdentifier(new Identifier().setSystem("http://namingsystem.org/lab-test-codes/").setValue(hgscReport.getTestName())
                        .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                                .setCode("FILL").setDisplay("Filler Identifier"))));
            }
        }
        //Version
        if (mappingConfig.containsKey("HgscReport.testCode")) {
            if(hgscReport.getTestCode() != null && !hgscReport.getTestCode().equals("")) {
                planDefinition.setVersion(hgscReport.getTestCode());
            }
        }
        //Title
        if (mappingConfig.containsKey("HgscReport.testName")) {
            if(hgscReport.getTestName() != null && !hgscReport.getTestName().equals("")) {
                planDefinition.setTitle(hgscReport.getTestName());
            }
        }
        //Type
        planDefinition.setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/plan-definition-type")
                .setCode("protocol").setDisplay("Protocol")));
        //Status
        planDefinition.setStatus(Enumerations.PublicationStatus.ACTIVE);
        //Description
        if (mappingConfig.containsKey("HgscReport.testinfosummary")) {
            if(hgscReport.getTestInfoSummary() != null && !hgscReport.getTestInfoSummary().equals("")) {
                planDefinition.setDescription(hgscReport.getTestInfoSummary());
            }
        }

        planDefinition.setText(new Narrative().setStatus(Narrative.NarrativeStatus.GENERATED)
                .setDiv(new XhtmlNode().setValue(FhirContext.forR4().newJsonParser().setPrettyPrint(true).encodeResourceToString(planDefinition))));

        //Action
        if (mappingConfig.containsKey("HgscReport.methodology")) {
            if(hgscReport.getMethodology() != null && hgscReport.getMethodology().size() > 0) {
                for(int i = 0; i < hgscReport.getMethodology().size(); i++) {
                    planDefinition.addAction(new PlanDefinition.PlanDefinitionActionComponent().setPrefix(String.valueOf(i + 1))
                            .setDescription(hgscReport.getMethodology().get(i)));
                }
            }
        }

        //RelatedArtifact
        if (mappingConfig.containsKey("HgscReport.references")) {
            if(hgscReport.getReferences() != null && hgscReport.getReferences().size() > 0) {
                for(String citation: hgscReport.getReferences()) {
                    planDefinition.addRelatedArtifact(new RelatedArtifact().setType(RelatedArtifact.RelatedArtifactType.CITATION).setCitation(citation));
                }
            }
        }

        return planDefinition;
    }
}
