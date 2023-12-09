package org.example.report.model.dto;

import lombok.Data;
import org.example.report.model.enums.ReportForm;
import org.example.report.model.enums.ReportSchedule;
import org.example.report.model.enums.ReportType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ReportNewDto {

    String organisation;

    String department;

    @NotNull(message = "Location should not be null.")
    String location;

    String manager;

    @NotNull(message = "Scope should not be null.")
    String scope;

    ReportSchedule schedule;

    ReportType type;

    ReportForm form;

    @NotNull(message = "Subject should not be null.")
    String subject;

    String criteria;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;

    @NotNull(message = "End date should not be null.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;

    @NotNull(message = "Audited by should not be null.")
    String auditedBy; //TODO Ввести коды компаний, таблица.

    @NotNull(message = "Lead auditor should not be null.")
    String leadAuditor;

    String auditor;

    String expert;

    String trainee;

    String auditee;
}
