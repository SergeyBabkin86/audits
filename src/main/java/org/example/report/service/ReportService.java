package org.example.report.service;

import org.example.report.model.dto.ReportFullDto;
import org.example.report.model.dto.ReportNewDto;
import org.example.report.model.dto.ReportShortDto;
import org.example.report.model.dto.ReportUpdateDto;
import org.example.util.GetReportRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportService {

    ReportFullDto save(ReportNewDto reportNewDto);

    ReportFullDto update(ReportUpdateDto reportUpdateDto);

    Page<ReportShortDto> getReports(String keyword, GetReportRequest reportRequest, Pageable pageRequest);

    void deleteById(int id);

    ReportFullDto findById(int reportId);
}
