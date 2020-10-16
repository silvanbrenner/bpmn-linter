package com.silvanbrenner.bpmnlinter.rule;

import com.silvanbrenner.bpmnlinter.model.BpmnLinterConfig;
import com.silvanbrenner.bpmnlinter.model.Issue;
import com.silvanbrenner.bpmnlinter.model.Severity;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.ServiceTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ServiceTaskPrefixRule implements IRule {

    private final BpmnLinterConfig config;

    public ServiceTaskPrefixRule(BpmnLinterConfig config) {
        this.config = config;
    }

    @Override
    public String getRuleName() {
        return "ServiceTask_Prefix";
    }

    @Override
    public String getDescription() {
        return "ServiceTasks should have the Id with prefix '" + config.serviceTaskPrefix + "'";
    }

    @Override
    public List<Issue> validate(BpmnModelInstance modelInstance) {
        List<Issue> issues = new ArrayList<>();
        Collection<ServiceTask> allServiceTasks = modelInstance.getModelElementsByType(ServiceTask.class);

        allServiceTasks.forEach(task -> {
            if (!StringUtils.startsWith(task.getId(), config.serviceTaskPrefix)) {
                issues.add(new Issue(Severity.Warning, task.getId(), "ServiceTask-Id should prefix with " + config.serviceTaskPrefix));
            }
        });

        return issues;
    }
}
