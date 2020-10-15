# bpmn-linter
BPMN-Linter for camunda BPMN files

![Java CI with Maven](https://github.com/silvanbrenner/bpmn-linter/workflows/Java%20CI%20with%20Maven/badge.svg?branch=main)


## Examples arguments

-f src\test\resources\dummy_process.bpmn

-f src\test\resources\dummy_process.bpmn -json

src\test\resources\dummy_process.bpmn -json -exclude-rule Gateway_Expression

## Rules

| ruleName                  | Description |
|---------------------------|-------------|
| UserTask_CandidateGroup   | Give all different candidateGroups |
| ServiceTask_Topic         | Check if topic is set for ServiceTasks with type 'external' |
| UserTask_FormKey          | Check if every UserTask have a FormKey defined |
| ServiceTask_AsyncBefore   | For all ServiceTasks the async before flag should be set|
| UserTask_Prefix           | UserTask should have the Id with prefix 'UserTask_'|
| UserTask_AsyncAfter       | For all UserTask the async after flag should be set
