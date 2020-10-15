package com.silvanbrenner.bpmnlinter.rule;

import com.silvanbrenner.bpmnlinter.model.Issue;
import com.silvanbrenner.bpmnlinter.model.Severity;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import java.util.List;

public interface IRule {

    String getRuleName();

    Severity getSeverity();

    String getDescription();

    List<Issue> validate(BpmnModelInstance modelInstance);
}
