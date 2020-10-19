package com.bpmnlinter.logger;

import com.bpmnlinter.model.Issue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

public class ConsoleLogger {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleLogger.class);

    public static void log(boolean formatJson, List<Issue> issues) {
        Comparator<Issue> comparator = Comparator.comparing(Issue::getSeverity);
        issues.sort(comparator);
        issues.forEach(issue -> {
            if (!formatJson) {
                logger.info(issue.getSeverity() + "/" + issue.getReference() + ": " + issue.getMessage());
            } else {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    String jsonString = mapper.writeValueAsString(issue);
                    logger.info(jsonString);
                } catch (JsonProcessingException e) {
                    logger.error("Could not format as json", e);
                }
            }
        });
    }
}
