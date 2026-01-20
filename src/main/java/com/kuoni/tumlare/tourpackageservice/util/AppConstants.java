package com.kuoni.tumlare.tourpackageservice.util;

/**
 * Global Constants for the Tour Package Service.
 * 
 * @author Mohit Kumar
 * @version 1.0
 */
public final class AppConstants {

    private AppConstants() {
        // Private constructor to prevent instantiation
    }

    public static final String API_BASE_PATH = "/api/tours";
    
    // Logging Messages
    public static final String LOG_REQUEST_START = "Received {} request for {}";
    public static final String LOG_REQUEST_END = "Completed {} request for {}";
    
    // Exception Messages
    public static final String TOUR_NOT_FOUND_MSG = "Tour package with ID %s not found";
    public static final String VALIDATION_ERROR_MSG = "Validation failed for the request";

    // Business Defaults
    public static final boolean DEFAULT_ACTIVE_STATUS = true;
}