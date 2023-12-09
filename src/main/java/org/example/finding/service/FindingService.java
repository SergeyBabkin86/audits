package org.example.finding.service;

import org.example.finding.model.dto.FullFindingDto;
import org.example.finding.model.dto.NewFindingDto;
import org.example.finding.model.dto.ShortFindingDto;

public interface FindingService {

    ShortFindingDto save(NewFindingDto newFindingDto, int auditReportId);

    FullFindingDto getById(int findingId);

    void deleteById(int auditReportId);
}
