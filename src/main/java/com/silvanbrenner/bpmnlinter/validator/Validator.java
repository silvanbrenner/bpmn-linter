package com.silvanbrenner.bpmnlinter.validator;

import com.silvanbrenner.bpmnlinter.model.BpmnLinterConfig;
import com.silvanbrenner.bpmnlinter.model.Issue;
import com.silvanbrenner.bpmnlinter.rule.*;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    private List<IRule> rules;

    public Validator(BpmnLinterConfig config, List<String> excludedRules) {
        List<IRule> rules = new ArrayList<>();
        rules.add(new ExternalTaskTopicRule());
        rules.add(new CandidateGroupRule());
        rules.add(new FormKeyRule());
        rules.add(new GatewayExpressionRule());
        rules.add(new UserTaskPrefixRule(config));
        rules.add(new ServiceTaskAsyncBeforeRule());
        rules.add(new UserTaskAsyncAfterRule());
        rules.add(new ServiceTaskPrefixRule(config));

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
