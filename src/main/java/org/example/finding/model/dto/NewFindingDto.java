package org.example.finding.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Data
public class NewFindingDto {
    String findingDescription;
    String findingLevel;
    String requirementExternal;
    String requirementLow;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dueDate;
    String remarks;
    String responsible;
    String coResponsible;
}
