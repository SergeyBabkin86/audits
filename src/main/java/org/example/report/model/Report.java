package org.example.report.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.example.finding.model.Finding;
import org.example.report.model.enums.ReportForm;
import org.example.report.model.enums.ReportSchedule;
import org.example.report.model.enums.ReportType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audit_reports")
public class Report {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @Column(name = "status")
    private String status;

    @Column(name = "organisation")
    private String organisation;

    @Column(name = "department")
    private String department;

    @Column(name = "location")
    private String location;

    @Column(name = "manager")
    private String manager;

    @Column(name = "scope", nullable = false)
    private String scope;

    @Column(name = "schedule")
    @Enumerated(EnumType.STRING)
    private ReportSchedule schedule;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ReportType type;

    @Column(name = "form")
    @Enumerated(EnumType.STRING)
    private ReportForm form;

    @Column(name = "subject")
    private String subject;

    @Column(name = "criteria")
    private String criteria;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name = "audited_by", nullable = false)
    private String auditedBy; //TODO Ввести коды компаний, таблица.

    @Column(name = "lead_auditor", nullable = false)
    private String leadAuditor;

    @Column(name = "auditor")
    private String auditor;

    @Column(name = "expert")
    private String expert;

    @Column(name = "tranee")
    private String trainee;

    @Column(name = "auditee")
    private String auditee;

    @OneToMany(mappedBy = "auditReportId", fetch = FetchType.LAZY)
    private Set<Finding> findings;
}
