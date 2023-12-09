package org.example.finding.service;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.example.finding.model.FindingMapper;
import org.example.finding.model.FindingMapperImpl;
import org.example.finding.model.FindingStatus;
import org.example.finding.model.dto.FullFindingDto;
import org.example.finding.model.dto.NewFindingDto;
import org.example.finding.model.dto.ShortFindingDto;
import org.example.finding.repository.FindingRepository;
import org.springframework.stereotype.Service;

import static org.example.util.Checkers.*;

@Service
@RequiredArgsConstructor
public class FindingServiceImpl implements FindingService {

    private final FindingRepository findingRepository;

    private final FindingMapper findingMapper = new FindingMapperImpl();

    @Override
    public ShortFindingDto save(NewFindingDto newFindingDto, int auditReportId) {
        var finding = findingMapper.toFinding(newFindingDto);
        finding.setAuditReportId(auditReportId);
        finding.setItem(getItem(auditReportId));
        finding.setStatus(String.valueOf(FindingStatus.OPEN));
        return findingMapper.toShortFindingDto(findingRepository.save(finding));
    }

    @Override
    public FullFindingDto getById(int findingId) {
        var finding = checkFindingExists(findingId, findingRepository);
        return findingMapper.toFullFindingDto(finding);
    }

    @Override
    public void deleteById(int findingId) {
        checkFindingExists(findingId, findingRepository);
        findingRepository.deleteById(findingId);
    }

    public int getItem(int auditReportId) {
        var count = findingRepository.countFindingByAuditReportId(auditReportId);
        if (count == 0) {
            return 1;
        }
        return count + 1;
    }
}
