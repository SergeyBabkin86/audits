package org.example.report.model.dto;

import lombok.Data;
import org.example.report.model.enums.ReportForm;
import org.example.report.model.enums.ReportSchedule;
import org.example.report.model.enums.ReportType;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ReportUpdateDto {

    @NotNull(message = "Id should not be null.")
    Integer id;

    String organisation;

    String department;

    String location;

    String manager;

    String scope;

    ReportSchedule schedule;

    ReportType type;

    ReportForm form;

    String subject;

    String criteria;

    LocalDate startDate;

    LocalDate endDate;

    String auditedBy; //TODO Ввести коды компаний, таблица.

    String leadAuditor;

    String auditor;

    String expert;

    String trainee;

    String auditee;
}
