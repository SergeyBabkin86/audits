package org.example.report.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportShortDto {

    private int id;

    private String number;

    private String status;

    private String subject;

    private String location;

    private String auditedBy;

    private String leadAuditor;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
