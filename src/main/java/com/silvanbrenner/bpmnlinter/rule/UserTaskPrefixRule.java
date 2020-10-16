package com.silvanbrenner.bpmnlinter.rule;

import com.silvanbrenner.bpmnlinter.model.Issue;
import com.silvanbrenner.bpmnlinter.model.Severity;
import io.micronaut.context.annotation.Value;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.UserTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserTaskPrefixRule implements IRule {

    @Value("${rules.user-task.prefix}")
    private String userTaskPrefix;

    @Override
    public String getRuleName() {
        return "UserTask_Prefix";
    }

    @Override
    public String getDescription() {
        return "UserTasks should have the Id with prefix '" + userTaskPrefix + "'";
    }

    @Override
    public List<Issue> validate(BpmnModelInstance modelInstance) {
        List<Issue> issues = new ArrayList<>();
        Collection<UserTask> allUserTasks = modelInstance.getModelElementsByType(UserTask.class);

        allUserTasks.forEach(task -> {
            if (!StringUtils.startsWith(task.getId(), userTaskPrefix)) {
                issues.add(new Issue(Severity.Warning, task.getId(), "UserTask-Id should prefix with " + userTaskPrefix));
            }
        });

        return issues;
    }
}
