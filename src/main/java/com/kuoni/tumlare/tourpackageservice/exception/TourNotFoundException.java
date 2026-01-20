package com.kuoni.tumlare.tourpackageservice.exception;

/**
 * Custom exception thrown when a Tour Package is not found.
 * 
 * @author Mohit Kumar
 * @version 1.0
 */
public class TourNotFoundException extends RuntimeException {
    public TourNotFoundException(String message) {
        super(message);
    }
}