package com.bpmnlinter.rule;

import com.bpmnlinter.model.Issue;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import java.util.List;

public interface IRule {

    String getRuleName();

    String getDescription();

    List<Issue> validate(BpmnModelInstance modelInstance);
}
