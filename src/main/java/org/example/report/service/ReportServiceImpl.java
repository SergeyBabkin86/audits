package org.example.report.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.example.report.model.QReport;
import org.example.report.model.Report;
import org.example.report.model.ReportMapper;
import org.example.report.model.ReportMapperImpl;
import org.example.report.model.dto.ReportFullDto;
import org.example.report.model.dto.ReportNewDto;
import org.example.report.model.dto.ReportShortDto;
import org.example.report.model.dto.ReportUpdateDto;
import org.example.report.model.enums.ReportStatus;
import org.example.report.repository.ReportRepository;
import org.example.util.GetReportRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.util.Optional.ofNullable;
import static org.example.util.Checkers.checkReportExists;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    private final ReportMapper reportMapper = new ReportMapperImpl();

    @Override
    public ReportFullDto save(ReportNewDto reportNewDto) {
        var report = reportMapper.toReport(reportNewDto);
        var internalNumber = generateInternalNumber(reportNewDto.getAuditedBy(),
                reportNewDto.getEndDate(),
                reportNewDto.getScope());
        report.setStatus(ReportStatus.OPEN);
        report.setNumber(internalNumber);
        return reportMapper.toReportFullDto(reportRepository.save(report));
    }

    @Override
    public ReportFullDto update(ReportUpdateDto reportUpdateDto) {
        var report = checkReportExists(reportUpdateDto.getId(), reportRepository);

        ofNullable(reportUpdateDto.getOrganisation()).ifPresent(report::setOrganisation);
        ofNullable(reportUpdateDto.getDepartment()).ifPresent(report::setDepartment);
        ofNullable(reportUpdateDto.getLocation()).ifPresent(report::setLocation);
        ofNullable(reportUpdateDto.getManager()).ifPresent(report::setManager);
        ofNullable(reportUpdateDto.getScope()).ifPresent(report::setScope);
        ofNullable(reportUpdateDto.getSchedule()).ifPresent(report::setSchedule);
        ofNullable(reportUpdateDto.getType()).ifPresent(report::setType);
        ofNullable(reportUpdateDto.getForm()).ifPresent(report::setForm);
        ofNullable(reportUpdateDto.getSubject()).ifPresent(report::setSubject);
        ofNullable(reportUpdateDto.getCriteria()).ifPresent(report::setCriteria);
        ofNullable(reportUpdateDto.getStartDate()).ifPresent(report::setStartDate);
        ofNullable(reportUpdateDto.getEndDate()).ifPresent(report::setEndDate);
        ofNullable(reportUpdateDto.getAuditedBy()).ifPresent(report::setAuditedBy);
        ofNullable(reportUpdateDto.getLeadAuditor()).ifPresent(report::setLeadAuditor);
        ofNullable(reportUpdateDto.getAuditor()).ifPresent(report::setAuditor);
        ofNullable(reportUpdateDto.getExpert()).ifPresent(report::setExpert);
        ofNullable(reportUpdateDto.getTrainee()).ifPresent(report::setTrainee);
        ofNullable(reportUpdateDto.getAuditee()).ifPresent(report::setAuditee);

        reportRepository.save(report);
        return reportMapper.toReportFullDto(report);
    }

    @Override
    public ReportFullDto findById(int reportId) {
        var report = checkReportExists(reportId, reportRepository);
        return reportMapper.toReportFullDto(report);
    }

    @Override
    public Page<ReportShortDto> getReports(String keyword, GetReportRequest reportRequest, Pageable pageRequest) {
        Page<Report> reports;
        var finalCondition = getFinalConditions(reportRequest);
        if (keyword == null || keyword.isEmpty()) {
            reports = reportRepository.findAll(finalCondition, pageRequest);
        } else {
            reports = reportRepository.search(keyword, pageRequest);
        }
        return reports.map(reportMapper::toReportShortDto);
    }

    @Override
    public void deleteById(int id) {
        checkReportExists(id, reportRepository);
        reportRepository.deleteById(id);
    }

    private BooleanExpression getFinalConditions(GetReportRequest reportRequest) {
        var report = QReport.report;
        var conditions = new ArrayList<BooleanExpression>();

        if (reportRequest.getReportStatus() != null) {
            conditions.add(report.status.eq(reportRequest.getReportStatus()));
        }

        if (reportRequest.getRangeStart() == null && reportRequest.getRangeEnd() == null) {
            conditions.add(report.endDate.before(LocalDate.now()));
        } else {
            if (reportRequest.getRangeStart() != null) {
                conditions.add(report.endDate.after(reportRequest.getRangeStart()));
            }
            if (reportRequest.getRangeEnd() != null) {
                conditions.add(report.endDate.before(reportRequest.getRangeEnd()));
            }
        }
        return conditions.stream()
                .reduce(BooleanExpression::and)
                .get();
    }

    private String generateInternalNumber(String auditedBy, LocalDate date, String scope) {
        StringBuilder sb = new StringBuilder();
        var count = reportRepository.countAuditReportsByAuditedByAndScope(auditedBy, scope);
        sb.append(auditedBy);
        sb.append("-");
        sb.append(date.getYear());
        sb.append(".");
        sb.append(scope);
        sb.append(".");
        sb.append(count + 1);
        return sb.toString();
    }
}
