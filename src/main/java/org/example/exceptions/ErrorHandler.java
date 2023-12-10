package org.example.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.example.exceptions.model.ErrorResponse;
import org.example.exceptions.model.ErrorState;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityNotFoundException(final EntityNotFoundException e) {
        log.debug("404 {}", e.getMessage(), e);
        return getErrorResponse(e, "Entity was not found.", ErrorState.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse defaultHandle(final Exception e) {
        log.debug("500 {}", e.getMessage(), e);
        return getErrorResponse(e, "Error occurred.", ErrorState.FORBIDDEN);
    }

    private ErrorResponse getErrorResponse(Exception e, String reason, ErrorState errorState) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .reason(reason)
                .status(errorState)
                .timestamp(String.valueOf(LocalDateTime.now()))
                .build();
    }
}