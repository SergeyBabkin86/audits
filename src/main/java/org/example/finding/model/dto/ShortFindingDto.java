package org.example.finding.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Data
public class ShortFindingDto {
    private Integer id;
    private Integer auditReportId;
    private Integer item;
    private String status;
    private String findingDescription;
    private String findingLevel;
    private String requirementExternal;
    private String requirementLow;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
    private Integer remainingDays;
    private String remarks;
    private String responsible;
    private String coResponsible;
}
