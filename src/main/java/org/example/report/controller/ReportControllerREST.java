package org.example.report.controller;

import lombok.RequiredArgsConstructor;
import org.example.report.model.dto.ReportFullDto;
import org.example.report.model.dto.ReportNewDto;
import org.example.report.model.dto.ReportShortDto;
import org.example.report.model.dto.ReportUpdateDto;
import org.example.report.service.ReportService;
import org.example.util.GetPageRequest;
import org.example.util.GetReportRequest;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportControllerREST {

    private final ReportService reportService;

    @GetMapping()
    public Page<ReportShortDto> findAll(@RequestParam(required = false) String keyword,
                                        @RequestParam String status,
                                        @RequestParam(required = false)
                                        @DateTimeFormat(pattern = "yyyy-MM-dd")
                                        LocalDate rangeStart,
                                        @RequestParam(required = false)
                                        @DateTimeFormat(pattern = "yyyy-MM-dd")
                                        LocalDate rangeEnd,
                                        @RequestParam(defaultValue = "endDate") String sortProperties,
                                        @RequestParam(defaultValue = "-1") int direction,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "5") int size) {
        return reportService.getReports(keyword,
                GetReportRequest.of(status, rangeStart, rangeEnd),
                GetPageRequest.of(page, size, sortProperties, direction));
    }

    @GetMapping("/{id}")
    public ReportFullDto findById(@PathVariable int id) {
        return reportService.findById(id);
    }

    @PostMapping
    public ReportFullDto create(@RequestBody ReportNewDto reportNewDto) {
        return reportService.save(reportNewDto);
    }

    @PostMapping("/update")
    public ReportFullDto update(@RequestBody ReportUpdateDto reportUpdateDto) {
        return reportService.update(reportUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        reportService.deleteById(id);
    }
}

