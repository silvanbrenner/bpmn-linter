package com.bpmnlinter.rule;

import com.bpmnlinter.model.Issue;
import com.bpmnlinter.model.Severity;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.UserTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserTaskAsyncAfterRule implements IRule {

    @Override
    public String getRuleName() {
        return "UserTask_AsyncAfter";
    }

    @Override
    public String getDescription() {
        return "For all UserTask the async after flag should be set";
    }

    @Override
    public List<Issue> validate(BpmnModelInstance modelInstance) {
        List<Issue> issues = new ArrayList<>();
        Collection<UserTask> allUserTasks = modelInstance.getModelElementsByType(UserTask.class);

        allUserTasks.forEach(task -> {
            if (!task.isCamundaAsyncAfter()) {
                issues.add(new Issue(Severity.Warning, task.getId(), "AsyncAfter flag should be set for all UserTasks"));
            }
        });

        return issues;
    }
}
