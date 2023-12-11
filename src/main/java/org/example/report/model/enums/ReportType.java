package org.example.report.model.enums;

public enum ReportType {
    INTERNAL("Internal"),
    EXTERNAL("External");

    private final String displayValue;

    ReportType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}