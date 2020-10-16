package com.silvanbrenner.bpmnlinter;

import com.beust.jcommander.JCommander;
import com.silvanbrenner.bpmnlinter.logger.ConsoleLogger;
import com.silvanbrenner.bpmnlinter.model.BpmnLinterConfig;
import com.silvanbrenner.bpmnlinter.model.BpmnParser;
import com.silvanbrenner.bpmnlinter.model.CommandParameter;
import com.silvanbrenner.bpmnlinter.model.Issue;
import com.silvanbrenner.bpmnlinter.validator.Validator;

import java.io.FileNotFoundException;
import java.util.List;

public class BpmnLinterApp {

    public static void main(String[] args) {
        var parameter = new CommandParameter();
        JCommander.newBuilder()
                .addObject(parameter)
                .build()
                .parse(args);

        BpmnLinterConfig config = new BpmnLinterConfig();

        BpmnParser parser = null;
        try {
            parser = new BpmnParser(parameter.file);
        } catch (FileNotFoundException e) {
            System.out.println("ProcessFile not found");
            System.exit(1);
        }

        Validator validator = new Validator(config, parameter.excludedRules);
        List<Issue> issues = validator.validateBpmn(parser.getModel());

        ConsoleLogger.log(parameter, issues);
    }

}
