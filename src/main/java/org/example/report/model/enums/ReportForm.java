package org.example.report.model.enums;

public enum ReportForm {
    ONSITE("On-site"),
    DESKTOP("Desktop");

    private final String displayValue;

    ReportForm(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
