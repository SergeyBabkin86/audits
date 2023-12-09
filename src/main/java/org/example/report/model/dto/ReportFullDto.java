package org.example.report.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.example.report.model.enums.ReportForm;
import org.example.report.model.enums.ReportSchedule;
import org.example.report.model.enums.ReportType;

import java.time.LocalDate;

@Data
public class ReportFullDto {

    private int id;

    private String number;

    private String status;

    private String organisation;

    private String department;

    private String location;

    private String manager;

    private String scope;

    private ReportSchedule schedule;

    private ReportType type;

    private ReportForm form;

    private String subject;

    private String criteria;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String auditedBy;

    private String leadAuditor;

    private String auditor;

    private String expert;

    private String trainee;

    private String auditee;
}
