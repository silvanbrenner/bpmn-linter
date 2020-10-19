package com.bpmnlinter.model;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BpmnParser {

    private final BpmnModelInstance modelInstance;

    public BpmnParser(String filePath) throws FileNotFoundException {
        Path path = Path.of(filePath);
        if (Files.exists(path)) {
            modelInstance = Bpmn.readModelFromFile(path.toFile());
        } else {
            throw new FileNotFoundException("File not found");
        }
    }

    public BpmnModelInstance getModel() {
        return modelInstance;
    }
}
