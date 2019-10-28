package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.*;

import java.util.HashMap;

/**
 * Created by sl9 on 10/16/19.
 */
public class PlanDefinitionValueMapper {

    public PlanDefinition planDefinitionValueMapping(PlanDefinition planDefinition, HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport) {

        // Profile
        planDefinition.getMeta().addProfile("") ;

        // Identifier
        if (mappingConfig.containsKey("HgscEmergeReport.testName")) {
            planDefinition.addIdentifier(new Identifier().setSystem("https://hgsc.bcm.edu/lab-test-codes/").setValue(hgscEmergeReport.getTestName())
                    .setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v2-0203")
                            .setCode("FILL").setDisplay("Filler Identifier"))));
        }

        // Version


        // Name
        if (mappingConfig.containsKey("HgscEmergeReport.testName")) {
            planDefinition.setName(hgscEmergeReport.getTestName());
        }

        // Title
        if (mappingConfig.containsKey("HgscEmergeReport.reportName")) {
            //planDefinition.setTitle(hgscEmergeReport.getReportName); // Does not exist right now but should be added to JSON
        }

        // Subtitle
        if (mappingConfig.containsKey("HgscEmergeReport.clinicalSite")) {
            planDefinition.setSubtitle(hgscEmergeReport.getClinicalSite());
        }

        // Type
        planDefinition.setType(new CodeableConcept().addCoding(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/plan-definition-type")
                        .setCode("protocol").setDisplay("Protocol")));

        // Status
        planDefinition.setStatus(Enumerations.PublicationStatus.ACTIVE);

        // Description
        if (mappingConfig.containsKey("HgscEmergeReport.background")) {
            planDefinition.setDescription(hgscEmergeReport.getBackground());
        }

        // Action
        if (mappingConfig.containsKey("HgscEmergeReport.methodology")) {
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
