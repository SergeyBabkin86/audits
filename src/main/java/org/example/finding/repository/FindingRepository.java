package org.example.finding.repository;

import org.example.finding.model.Finding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FindingRepository extends JpaRepository<Finding, Integer> {

    Integer countFindingByAuditReportId(int auditReportId);
}
