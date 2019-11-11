package edu.bcm.hgsc.fhir.utils.mapper;

import edu.bcm.hgsc.fhir.models.HgscEmergeReport;
import org.hl7.fhir.r4.model.*;

import java.util.HashMap;

public class TaskValueMapper {

    public Task taskValueMapping(HashMap<String, String> mappingConfig, HgscEmergeReport hgscEmergeReport) {

        Task task = new Task();

        //Profile
        task.getMeta().addProfile("http://hl7.org/fhir/uv/genomics-reporting/StructureDefinition/task-rec-followup");
        //Status
        task.setStatus(Task.TaskStatus.REQUESTED);
        //Intent
        task.setIntent(Task.TaskIntent.PROPOSAL);
        //Code
        task.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://loinc.org")
                .setCode("LA14020-4").setDisplay("Genetic counseling recommended")));
        //Description
        task.setDescription("Genetic counseling for the patient and at-risk family members is recommended.");

        return task;
    }
}
