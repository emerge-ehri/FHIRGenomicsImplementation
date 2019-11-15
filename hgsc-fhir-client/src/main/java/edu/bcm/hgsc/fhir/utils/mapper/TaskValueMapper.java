package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscReport;
import org.hl7.fhir.r4.model.*;

import java.util.HashMap;

public class TaskValueMapper {

    public Task taskValueMapping(HashMap<String, String> mappingConfig, HgscReport hgscReport) {

        Task task = new Task();

        //Profile
        task.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/task-rec-followup");
        //Status
        task.setStatus(Task.TaskStatus.REQUESTED);
        //Intent
        task.setIntent(Task.TaskIntent.PROPOSAL);
        //Code
        task.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("LL1037-2").setDisplay("Genetic counseling recommended"))
                .setText("It is recommended that correlation of these findings with the clinical phenotype be performed. Genetic counseling for the patient and at-risk family members is recommended."));
        //Description
        task.setDescription("It is recommended that correlation of these findings with the clinical phenotype be performed. Genetic counseling for the patient and at-risk family members is recommended.");

        return task;
    }
}
