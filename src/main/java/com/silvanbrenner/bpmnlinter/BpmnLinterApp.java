package com.silvanbrenner.bpmnlinter;

import com.beust.jcommander.JCommander;
import com.silvanbrenner.bpmnlinter.model.BpmnLinterParameter;
import com.silvanbrenner.bpmnlinter.model.BpmnParser;
import com.silvanbrenner.bpmnlinter.model.Issue;
import com.silvanbrenner.bpmnlinter.rule.*;
import com.silvanbrenner.bpmnlinter.validator.Validator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BpmnLinterApp {

    public static void main(String[] args) {
        var parameter = new BpmnLinterParameter();
        JCommander.newBuilder()
                .addObject(parameter)
                .build()
                .parse(args);

        BpmnParser parser = null;
        try {
            parser = new BpmnParser(parameter.file);
        } catch (FileNotFoundException e) {
            System.out.println("ProcessFile not found");
            System.exit(1);
        }

        List<IRule> rules = new ArrayList<>();
        rules.add(new ExternalTaskTopicRule());
        rules.add(new CandidateGroupRule());
        rules.add(new FormKeyRule());
        rules.add(new GatewayExpressionRule());
        rules.add(new UserTaskPrefixRule());
        rules.add(new ServiceTaskAsyncBeforeRule());
        rules.add(new UserTaskAsyncAfterRule());

        if (!parameter.excludedRules.isEmpty()) {
            parameter.excludedRules.forEach(excludedRule -> rules.removeIf(rule -> rule.getRuleName().equalsIgnoreCase(excludedRule)));
        }

        Validator validator = new Validator();
        List<Issue> issues = validator.validateBpmn(parser.getModel(), rules);

        Comparator<Issue> comparator = Comparator.comparing(Issue::getSeverity);
        issues.sort(comparator);
        issues.forEach(issue -> {
            if (!parameter.formatJson) {
                System.out.println(issue.getSeverity() + "/" + issue.getReference() + ": " + issue.getMessage());
            } else {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    String jsonString = mapper.writeValueAsString(issue);
                    System.out.println(jsonString);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
