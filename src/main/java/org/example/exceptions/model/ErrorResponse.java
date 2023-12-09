package org.example.exceptions.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    String message;
    String reason;
    ErrorState status;
    String timestamp;
}