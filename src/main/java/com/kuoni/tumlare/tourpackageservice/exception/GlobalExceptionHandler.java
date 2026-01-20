package com.kuoni.tumlare.tourpackageservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global Exception Handler for the Tour Package Service.
 * 
 * @author Mohit Kumar
 * @version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles TourNotFoundException.
     *
     * @param ex the exception
     * @return a Mono containing the error response
     */
    @ExceptionHandler(TourNotFoundException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleNotFound(TourNotFoundException ex) {
        log.error("Resource not found: {}", ex.getMessage());
        return Mono.just(createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    /**
     * Handles ValidationException and WebExchangeBindException.
     *
     * @param ex the exception
     * @return a Mono containing the error response
     */
    @ExceptionHandler({ValidationException.class, WebExchangeBindException.class})
    public Mono<ResponseEntity<Map<String, Object>>> handleValidation(Exception ex) {
        String message;
        if (ex instanceof WebExchangeBindException bindEx) {
            message = bindEx.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
        } else {
            message = ex.getMessage();
        }
        log.error("Validation error: {}", message);
        return Mono.just(createErrorResponse(HttpStatus.BAD_REQUEST, message));
    }

    /**
     * Handles all other unhandled exceptions.
     *
     * @param ex the exception
     * @return a Mono containing the error response
     */
    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleGeneralException(Exception ex) {
        log.error("Unexpected error occurred", ex);
        return Mono.just(createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred"));
    }

    private ResponseEntity<Map<String, Object>> createErrorResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return ResponseEntity.status(status).body(body);
    }
}

