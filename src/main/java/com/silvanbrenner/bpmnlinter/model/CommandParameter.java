package com.silvanbrenner.bpmnlinter.model;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class CommandParameter {

    @Parameter(names = {"-file", "-f"}, description = "BPMN File to lint", required = true)
    public String file;

    @Parameter(names = {"-exclude-rule", "-e"}, description = "Rules to exclude")
    public List<String> excludedRules = new ArrayList<>();

    @Parameter(names = {"-formatJson", "-json"}, description = "Format output as JSON")
    public boolean formatJson = false;

}