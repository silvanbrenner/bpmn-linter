package com.silvanbrenner.bpmnlinter.rule;

import com.silvanbrenner.bpmnlinter.model.Issue;
import com.silvanbrenner.bpmnlinter.model.Severity;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.ServiceTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExternalTaskTopicRule implements IRule {

    @Override
    public String getRuleName() {
        return "ServiceTask_Topic";
    }

    @Override
    public Severity getSeverity() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Check if topic is set for ServiceTasks with type 'external'";
    }

    @Override
    public List<Issue> validate(BpmnModelInstance modelInstance) {
        List<Issue> issues = new ArrayList<>();
        Collection<ServiceTask> allServiceTasks = modelInstance.getModelElementsByType(ServiceTask.class);

        allServiceTasks.forEach(serviceTask -> {
            if (StringUtils.equalsAnyIgnoreCase(serviceTask.getCamundaType(),"external")) {
                issues.add(new Issue(Severity.Information, serviceTask.getId(), "Topic should be handled: " + serviceTask.getCamundaTopic()));
            }
        });

        return issues;
    }
}
