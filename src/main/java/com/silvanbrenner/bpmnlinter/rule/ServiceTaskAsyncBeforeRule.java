package com.silvanbrenner.bpmnlinter.rule;

import com.silvanbrenner.bpmnlinter.model.Issue;
import com.silvanbrenner.bpmnlinter.model.Severity;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.ServiceTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ServiceTaskAsyncBeforeRule implements IRule {

    @Override
    public String getRuleName() {
        return "ServiceTask_AsyncBefore";
    }

    @Override
    public String getDescription() {
        return "For all ServiceTasks the async before flag should be set";
    }

    @Override
    public List<Issue> validate(BpmnModelInstance modelInstance) {
        List<Issue> issues = new ArrayList<>();
        Collection<ServiceTask> allServiceTasks = modelInstance.getModelElementsByType(ServiceTask.class);

        allServiceTasks.forEach(task -> {
            if (!task.isCamundaAsyncBefore()) {
                issues.add(new Issue(Severity.Warning, task.getId(), "AsyncBefore flag should be set for all ServiceTasks"));
            }
        });

        return issues;
    }
}
