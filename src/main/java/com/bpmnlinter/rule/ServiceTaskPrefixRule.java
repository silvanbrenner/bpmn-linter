package com.bpmnlinter.rule;

import com.bpmnlinter.model.Issue;
import com.bpmnlinter.model.Severity;
import io.micronaut.context.annotation.Value;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.ServiceTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ServiceTaskPrefixRule implements IRule {

    @Value("${rules.service-task.prefix}")
    private String serviceTaskPrefix;

    @Override
    public String getRuleName() {
        return "ServiceTask_Prefix";
    }

    @Override
    public String getDescription() {
        return "ServiceTasks should have the Id with prefix '" + serviceTaskPrefix + "'";
    }

    @Override
    public List<Issue> validate(BpmnModelInstance modelInstance) {
        List<Issue> issues = new ArrayList<>();
        Collection<ServiceTask> allServiceTasks = modelInstance.getModelElementsByType(ServiceTask.class);

        allServiceTasks.forEach(task -> {
            if (!StringUtils.startsWith(task.getId(), serviceTaskPrefix)) {
                issues.add(new Issue(Severity.Warning, task.getId(), "ServiceTask-Id should prefix with " + serviceTaskPrefix));
            }
        });

        return issues;
    }
}
