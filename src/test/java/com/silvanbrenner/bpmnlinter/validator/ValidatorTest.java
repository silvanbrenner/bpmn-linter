package com.silvanbrenner.bpmnlinter.validator;


import com.silvanbrenner.bpmnlinter.model.BpmnLinterConfig;
import com.silvanbrenner.bpmnlinter.model.BpmnParser;
import com.silvanbrenner.bpmnlinter.model.Issue;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidatorTest {

    @Test
    public void validateBpmn_DummyProcess_Ok() throws FileNotFoundException {
        BpmnLinterConfig config = new BpmnLinterConfig();
        Validator validator = new Validator(config, Collections.emptyList());
        BpmnParser parser = new BpmnParser("src/test/resources/dummy_process.bpmn");

        List<Issue> issues = validator.validateBpmn(parser.getModel());

        assertFalse(issues.isEmpty());
    }
}
