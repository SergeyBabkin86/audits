package org.example.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class GetReportRequest {
    private LocalDate rangeStart;
    private LocalDate rangeEnd;

    public static GetReportRequest of(LocalDate rangeStart,
                                      LocalDate rangeEnd) {
        GetReportRequest request = new GetReportRequest();
        request.setRangeStart(rangeStart);
        request.setRangeEnd(rangeEnd);
        return request;
    }
}
