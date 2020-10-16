package com.silvanbrenner.bpmnlinter.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silvanbrenner.bpmnlinter.model.CommandParameter;
import com.silvanbrenner.bpmnlinter.model.Issue;

import java.util.Comparator;
import java.util.List;

public class ConsoleLogger {

    public static void log(CommandParameter parameter, List<Issue> issues) {
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
