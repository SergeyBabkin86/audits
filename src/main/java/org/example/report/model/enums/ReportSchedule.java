package org.example.report.model.enums;

public enum ReportSchedule {
    SCHEDULED("Scheduled"),
    UNSCHEDULED("Unscheduled");

    private final String displayValue;

    ReportSchedule(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}

