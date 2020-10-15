package com.silvanbrenner.bpmnlinter.validator;

import com.silvanbrenner.bpmnlinter.model.Issue;
import com.silvanbrenner.bpmnlinter.rule.IRule;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    public List<Issue> validateBpmn(BpmnModelInstance model, List<IRule> rules) {
        List<Issue> issues = new ArrayList<>();
        rules.forEach(rule -> {
            issues.addAll(rule.validate(model));
        });
        return issues;
    }
}
