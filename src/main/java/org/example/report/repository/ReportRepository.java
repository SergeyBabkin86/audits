package org.example.report.repository;

import org.example.report.model.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ReportRepository extends JpaRepository<Report, Integer>, QuerydslPredicateExecutor<Report> {

    Integer countAuditReportsByAuditedByAndScope(String scope, String auditedBy);

    @Query("select r from Report r where upper(r.subject) like upper(concat('%', ?1, '%')) " +
            "or upper(r.location) like upper(concat('%', ?1, '%'))" +
            "or upper(r.leadAuditor) like upper(concat('%', ?1, '%'))")
    Page<Report> search(String keyword, Pageable pageable);
}
