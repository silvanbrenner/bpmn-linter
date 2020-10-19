package com.bpmnlinter.rule;

import com.bpmnlinter.model.Issue;
import com.bpmnlinter.model.Severity;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.ExclusiveGateway;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GatewayExpressionRule implements IRule {

    @Override
    public String getRuleName() {
        return "Gateway_Expression";
    }

    @Override
    public String getDescription() {
        return "Check if gateway outgoing flows with type expression have an expression defined";
    }

    @Override
    public List<Issue> validate(BpmnModelInstance modelInstance) {
        List<Issue> issues = new ArrayList<>();
        Collection<ExclusiveGateway> allGateways = modelInstance.getModelElementsByType(ExclusiveGateway.class);

        allGateways.forEach(gateway -> {
            gateway.getOutgoing().forEach(outgoing -> {
                if (outgoing.getConditionExpression() != null &&
                        outgoing.getConditionExpression().getType().equalsIgnoreCase("bpmn:tFormalExpression")
                ) {
                    if (StringUtils.isNoneEmpty(outgoing.getConditionExpression().getRawTextContent())) {
                        issues.add(new Issue(Severity.Information, outgoing.getId(), "Expression should be handled: " + outgoing.getConditionExpression().getRawTextContent()));
                    } else {
                        issues.add(new Issue(Severity.Error, outgoing.getId(), "No Expression defined "));
                    }
                }
            });
        });

        return issues;
    }
}
