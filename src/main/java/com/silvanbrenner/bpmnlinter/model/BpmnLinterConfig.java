package com.silvanbrenner.bpmnlinter.model;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class BpmnLinterConfig {

    public final String userTaskPrefix;

    public BpmnLinterConfig() {
        Config conf = ConfigFactory.load("bpmn-linter");

        userTaskPrefix = conf.getString("rules-config.user-task.prefix");

    }
}
