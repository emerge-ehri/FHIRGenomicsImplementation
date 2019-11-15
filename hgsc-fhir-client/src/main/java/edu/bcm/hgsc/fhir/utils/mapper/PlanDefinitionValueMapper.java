package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.*;

import java.util.HashMap;

/**
 * Created by sl9 on 10/16/19.
 */
public class PlanDefinitionValueMapper {

    public PlanDefinition planDefinitionValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        PlanDefinition planDefinition = new PlanDefinition();

        // Profile
        planDefinition.getMeta().addProfile("") ;

        // Identifier
        if (mappingConfig.containsKey("HgscReport.testName")) {
            planDefinition.addIdentifier(new Identifier().setSystem("https://hgsc.bcm.edu/lab-test-codes/").setValue(hgscReport.getTestName())
                    .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                            .setCode("FILL").setDisplay("Filler Identifier"))));
        }

        // Version


        // Name
        if (mappingConfig.containsKey("HgscReport.testName")) {
            planDefinition.setName(hgscReport.getTestName());
        }

        // Title
        if (mappingConfig.containsKey("HgscReport.reportName")) {
            //planDefinition.setTitle(hgscReport.getReportName); // Does not exist right now but should be added to JSON
        }

        // Subtitle
        if (mappingConfig.containsKey("HgscReport.clinicalSite")) {
            planDefinition.setSubtitle(hgscReport.getClinicalSite());
        }

        // Type
        planDefinition.setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/plan-definition-type")
                        .setCode("protocol").setDisplay("Protocol")));

        // Status
        planDefinition.setStatus(Enumerations.PublicationStatus.ACTIVE);

        // Description
        if (mappingConfig.containsKey("HgscReport.background")) {
            planDefinition.setDescription(hgscReport.getBackground());
        }

        // Action
        if (mappingConfig.containsKey("HgscReport.methodology")) {
            PlanDefinition.PlanDefinitionActionComponent planDefinitionActionComponent = new PlanDefinition.PlanDefinitionActionComponent();
            planDefinitionActionComponent.setPrefix("5");
            planDefinitionActionComponent.setDescription("");
            planDefinition.getAction().add(planDefinitionActionComponent);
        }

        // RelatedArtifact
        RelatedArtifact relatedArtifact = new RelatedArtifact();
        planDefinition.getRelatedArtifact().add(relatedArtifact);


        return  planDefinition;
    }
}
