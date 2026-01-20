package com.kuoni.tumlare.tourpackageservice.exception;

/**
 * Exception thrown for validation errors.
 * 
 * @author Mohit Kumar
 * @version 1.0
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}

