package org.example.report.model;

import org.example.report.model.dto.ReportFullDto;
import org.example.report.model.dto.ReportNewDto;
import org.example.report.model.dto.ReportShortDto;
import org.example.report.model.dto.ReportUpdateDto;
import org.mapstruct.Mapper;

@Mapper
public interface ReportMapper {

    Report toReport(ReportNewDto report);

    Report toReport(ReportUpdateDto report);

    ReportShortDto toReportShortDto(Report report);

    ReportFullDto toReportFullDto(Report report);
}
