package com.bpmnlinter.validator;

import com.bpmnlinter.model.Issue;
import com.bpmnlinter.rule.*;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    private final List<IRule> rules = new ArrayList<>();

    public Validator(List<String> excludedRules) {
        rules.add(new ServiceTaskTopicRule());
        rules.add(new CandidateGroupRule());
        rules.add(new FormKeyRule());
        rules.add(new GatewayExpressionRule());
        rules.add(new UserTaskPrefixRule());
        rules.add(new ServiceTaskAsyncBeforeRule());
        rules.add(new UserTaskAsyncAfterRule());
        rules.add(new ServiceTaskPrefixRule());

        if (!excludedRules.isEmpty()) {
            excludedRules.forEach(excludedRule -> rules.removeIf(rule -> rule.getRuleName().equalsIgnoreCase(excludedRule)));
        }
    }

    public List<Issue> validateBpmn(BpmnModelInstance model) {
        List<Issue> issues = new ArrayList<>();
        rules.forEach(rule -> {
            issues.addAll(rule.validate(model));
        });
        return issues;
    }
}
