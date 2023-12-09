package org.example.util;

import lombok.extern.slf4j.Slf4j;
import org.example.exceptions.EntityNotFoundException;
import org.example.finding.model.Finding;
import org.example.finding.repository.FindingRepository;
import org.example.report.model.Report;
import org.example.report.repository.ReportRepository;

import static java.lang.String.format;

@Slf4j
public class Checkers {

    public static Report checkReportExists(Integer reportId, ReportRepository repository) {
        return repository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException(format("Audit report with id=%s was not found.",
                        reportId)));
    }

    public static Finding checkFindingExists(Integer findingId, FindingRepository repository) {
        return repository.findById(findingId)
                .orElseThrow(() -> new EntityNotFoundException(format("Finding with id=%s was not found.",
                        findingId)));
    }
}
