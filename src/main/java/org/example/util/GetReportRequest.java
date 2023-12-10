package org.example.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.report.model.enums.ReportStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class GetReportRequest {
    private ReportStatus reportStatus;
    private LocalDate rangeStart;
    private LocalDate rangeEnd;

    public static GetReportRequest of(String status, LocalDate rangeStart, LocalDate rangeEnd) {
        GetReportRequest request = new GetReportRequest();
        if (status.equals("OPEN") || status.equals("CLOSED")) {
            request.setReportStatus(ReportStatus.valueOf(status));
        }
        request.setRangeStart(rangeStart);
        request.setRangeEnd(rangeEnd);
        return request;
    }
}
