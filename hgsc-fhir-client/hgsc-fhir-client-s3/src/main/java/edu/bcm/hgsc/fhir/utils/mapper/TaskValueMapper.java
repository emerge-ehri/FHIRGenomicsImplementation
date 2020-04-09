package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.*;

import java.util.HashMap;

public class TaskValueMapper {

    public Task taskValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Task task = new Task();

        task.setLanguage(hgscReport.getLanguage());

        //Profile
        task.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/task-rec-followup");
        //Status
        task.setStatus(Task.TaskStatus.REQUESTED);
        //Intent
        task.setIntent(Task.TaskIntent.PROPOSAL);
        //Code
        if(hgscReport.getPanelRecommendation() != null && !hgscReport.getPanelRecommendation().equals("")) {
            if(hgscReport.getOverallInterpretation().equalsIgnoreCase("positive")) {
                task.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                        .setCode("LA14020-4").setDisplay("Genetic counseling recommended"))
                        .setText(hgscReport.getPanelRecommendation()));
            }else{
                task.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                        .setCode("LA14022-0").setDisplay("Additional testing recommended"))
                        .setText(hgscReport.getPanelRecommendation()));
            }
        }

        //Description
        if(hgscReport.getPanelRecommendationDetail() != null && !hgscReport.getPanelRecommendationDetail().equals("")) {
            task.setDescription(hgscReport.getPanelRecommendationDetail());
        }

        return task;
    }
}
