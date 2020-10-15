package com.silvanbrenner.bpmnlinter.rule;

import com.silvanbrenner.bpmnlinter.model.Issue;
import com.silvanbrenner.bpmnlinter.model.Severity;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.UserTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FormKeyRule implements IRule {

    @Override
    public String getRuleName() {
        return "UserTask_FormKey";
    }

    @Override
    public Severity getSeverity() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Check if every UserTask have a FormKey defined";
    }

    @Override
    public List<Issue> validate(BpmnModelInstance modelInstance) {
        List<Issue> issues = new ArrayList<>();
        Collection<UserTask> allUserTasks = modelInstance.getModelElementsByType(UserTask.class);

        allUserTasks.forEach(task -> {
            if (StringUtils.isNotEmpty(task.getCamundaFormKey())) {
                issues.add(new Issue(Severity.Information, task.getId(), "FormKey defined "+ task.getCamundaFormKey()));
            } else {
                issues.add(new Issue(Severity.Error, task.getId(), "No FormKey defined"));
            }
        });

        return issues;
    }
}
