package com.bpmnlinter.validator;


import com.bpmnlinter.model.Issue;
import com.bpmnlinter.model.BpmnParser;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidatorTest {

    @Test
    public void validateBpmn_DummyProcess_Ok() throws FileNotFoundException {
        Validator validator = new Validator(Collections.emptyList());
        BpmnParser parser = new BpmnParser("src/test/resources/dummy_process.bpmn");

        List<Issue> issues = validator.validateBpmn(parser.getModel());

        assertFalse(issues.isEmpty());
    }
}
