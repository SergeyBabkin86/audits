package org.example.finding.controller;

import lombok.RequiredArgsConstructor;
import org.example.finding.model.dto.FullFindingDto;
import org.example.finding.model.dto.NewFindingDto;
import org.example.finding.model.dto.ShortFindingDto;
import org.example.finding.service.FindingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/findings")
@RequiredArgsConstructor
public class FindingController {

    private final FindingService findingService;

    @PostMapping()
    public ShortFindingDto save(@RequestBody NewFindingDto newFindingDto,
                                @RequestHeader("X-Sharer-Report-Id") int findingId) {
        return findingService.save(newFindingDto, findingId);
    }

    @GetMapping("/{id}")
    public FullFindingDto getById(@PathVariable("id") int id) {
        return findingService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        findingService.deleteById(id);
    }
}
