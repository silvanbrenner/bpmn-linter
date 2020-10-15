package com.silvanbrenner.bpmnlinter.rule;

import com.silvanbrenner.bpmnlinter.model.Issue;
import com.silvanbrenner.bpmnlinter.util.NamingConvention;
import com.silvanbrenner.bpmnlinter.model.Severity;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.UserTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CandidateGroupRule implements IRule {

    @Override
    public String getRuleName() {
        return "UserTask_CandidateGroup";
    }

    @Override
    public String getDescription() {
        return "Give all different candidateGroups";
    }

    @Override
    public List<Issue> validate(BpmnModelInstance modelInstance) {
        List<Issue> issues = new ArrayList<>();
        Collection<UserTask> allUserTasks = modelInstance.getModelElementsByType(UserTask.class);

        allUserTasks.forEach(task -> {
            if (StringUtils.isNotEmpty(task.getCamundaCandidateGroups())) {
                String[] allGroups = StringUtils.split(StringUtils.deleteWhitespace(task.getCamundaCandidateGroups()), NamingConvention.CANDIDATE_GROUP_SPLIT);

                Arrays.stream(allGroups).forEach(group -> {
                    issues.add(new Issue(Severity.Information, task.getId(), "Group should be handled: " + group));
                });
            }
        });

        return issues;
    }
}
