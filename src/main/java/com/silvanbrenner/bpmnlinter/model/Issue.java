package com.silvanbrenner.bpmnlinter.model;

public class Issue {

    private final Severity severity;

    private final String reference;

    private final String message;

    public Issue(Severity severity, String reference, String message) {
        this.severity = severity;
        this.reference = reference;
        this.message = message;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getReference() {
        return reference;
    }

    public String getMessage() {
        return message;
    }
}
