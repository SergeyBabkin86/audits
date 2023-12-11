package org.example.finding.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "findings")
public class Finding {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "audit_rep_id", nullable = false)
    private Integer auditReportId;

    @Column(name = "item")
    private Integer item;

    @Transient
    private String status;

    @Column(name = "finding_desc")
    private String findingDescription;

    @Column(name = "finding_level")
    private String findingLevel;

    @Column(name = "requirement_ext")
    private String requirementExternal;

    @Column(name = "requirement_low")
    private String requirementLow;

    @Column(name = "due_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @Transient
    private Integer remainingDays;

    @Column(name = "root_cause_gen")
    private String rootCauseGeneral;

    @Column(name = "root_cause_desc")
    private String rootCauseDescription;

    @Column(name = "corr_act_plan")
    private String correctiveActionPlan;

    @Column(name = "corr_act_impl")
    private String correctiveActionImpl;

    @Column(name = "prev_act_plan")
    private String preventiveActionPlan;

    @Column(name = "prev_act_impl")
    private String preventiveActionImpl;

    @Column(name = "closure_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate closureDate;

    @Column(name = "extension_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate extensionDate;

    @Column(name = "extension_reason")
    private String extensionReason;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "responsible")
    private String responsible;

    @Column(name = "co_responsible")
    private String coResponsible;

    @PrePersist
    @PreUpdate
    @PostLoad
    void getTransientRemainingDays() {
        this.remainingDays = Math.toIntExact(ChronoUnit.DAYS.between(LocalDate.now(), this.dueDate));
    }
}

