package com.bpmnlinter;

import com.bpmnlinter.logger.ConsoleLogger;
import com.bpmnlinter.model.Issue;
import com.bpmnlinter.validator.Validator;
import com.bpmnlinter.model.BpmnParser;
import io.micronaut.configuration.picocli.PicocliRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


@CommandLine.Command(name = "bpmn-linter",
        mixinStandardHelpOptions = true)
public class BpmnLinterCommand implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(BpmnLinterCommand.class);

    @CommandLine.Option(names = {"-file", "-f"}, description = "BPMN File to lint", required = true)
    public String file;

    @CommandLine.Option(names = {"-exclude-rule", "-e"}, description = "Rules to exclude")
    public List<String> excludedRules = new ArrayList<>();

    @CommandLine.Option(names = {"-formatJson", "-json"}, description = "Format output as JSON")
    public boolean formatJson = false;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(BpmnLinterCommand.class, args);
    }

    public void run() {
        BpmnParser parser = null;
        try {
            parser = new BpmnParser(file);
        } catch (FileNotFoundException e) {
            logger.error("ProcessFile not found");
            System.exit(1);
        }

        Validator validator = new Validator(excludedRules);
        List<Issue> issues = validator.validateBpmn(parser.getModel());

        ConsoleLogger.log(formatJson, issues);
    }
}
