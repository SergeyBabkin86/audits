package org.example.finding.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FullFindingDto {

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

    private String rootCauseGeneral;

    private String rootCauseDescription;

    private String correctiveActionPlan;

    private String correctiveActionImpl;

    private String preventiveActionPlan;

    private String preventiveActionImpl;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate closureDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate extensionDate;

    private String extensionReason;

    private String remarks;

    private String responsible;

    private String coResponsible;
}
