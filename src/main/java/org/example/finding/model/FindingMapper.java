package org.example.finding.model;

import org.example.finding.model.dto.FullFindingDto;
import org.example.finding.model.dto.NewFindingDto;
import org.example.finding.model.dto.ShortFindingDto;
import org.mapstruct.Mapper;

@Mapper
public interface FindingMapper {

    Finding toFinding(NewFindingDto newFindingDto);

    ShortFindingDto toShortFindingDto(Finding finding);

    FullFindingDto toFullFindingDto(Finding finding);
}
