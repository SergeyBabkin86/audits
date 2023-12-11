package org.example.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reports")
public class ReportControllerHTML {

    @GetMapping()
    public String getReports() {
        return "report-index";
    }
}
