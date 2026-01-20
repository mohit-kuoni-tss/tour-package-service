package com.kuoni.tumlare.tourpackageservice.controller;

import com.kuoni.tumlare.tourpackageservice.dto.TourPackageRequestDTO;
import com.kuoni.tumlare.tourpackageservice.dto.TourPackageResponseDTO;
import com.kuoni.tumlare.tourpackageservice.service.TourPackageService;
import com.kuoni.tumlare.tourpackageservice.util.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST Controller for managing Tour Packages.
 * 
 * @author Mohit Kumar
 * @version 1.0
 */
@RestController
@RequestMapping(AppConstants.API_BASE_PATH)
@Tag(name = "Tour Package Controller", description = "APIs for managing Tour Packages")
public class TourPackageController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TourPackageController.class);

    private final TourPackageService tourPackageService;

    public TourPackageController(TourPackageService tourPackageService) {
        this.tourPackageService = tourPackageService;
    }

@Operation(summary = "Create a new tour package", description = "Adds a new tour package to the system")
    @ApiResponse(responseCode = "201", description = "Tour package created successfully",
            content = @Content(schema = @Schema(implementation = TourPackageResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Invalid request payload")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TourPackageResponseDTO> createTour(@Valid @RequestBody TourPackageRequestDTO requestDTO) {
        log.info(AppConstants.LOG_REQUEST_START, "POST", AppConstants.API_BASE_PATH);
        return tourPackageService.createTour(requestDTO);
    }

    @Operation(summary = "Create multiple tour packages in a batch", description = "Adds multiple new tour packages to the system")
    @ApiResponse(responseCode = "201", description = "Tour packages created successfully",
            content = @Content(schema = @Schema(implementation = TourPackageResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Invalid request payload")
    @PostMapping(value = "/batch", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Flux<TourPackageResponseDTO> createTours(@RequestBody Flux<TourPackageRequestDTO> requestDTOs) {
        log.info(AppConstants.LOG_REQUEST_START, "POST", AppConstants.API_BASE_PATH + "/batch");
        return tourPackageService.createTours(requestDTOs);
    }

@Operation(summary = "Get tour package by ID", description = "Fetches a tour package details by its primary key")
    @ApiResponse(responseCode = "200", description = "Tour package found")
    @ApiResponse(responseCode = "404", description = "Tour package not found")
    @GetMapping("/{id}")
    public Mono<TourPackageResponseDTO> getTourById(
            @Parameter(description = "ID of the tour package to be retrieved") @PathVariable Long id) {
        log.info(AppConstants.LOG_REQUEST_START, "GET", AppConstants.API_BASE_PATH + "/" + id);
        return tourPackageService.getTourById(id);
    }

@Operation(summary = "Get all tour packages", description = "Retrieves a stream of all active tour packages")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<TourPackageResponseDTO> getAllTours() {
        log.info(AppConstants.LOG_REQUEST_START, "GET", AppConstants.API_BASE_PATH);
        return tourPackageService.getAllTours();
    }

@Operation(summary = "Update an existing tour package", description = "Updates tour package details by its ID")
    @ApiResponse(responseCode = "200", description = "Tour package updated successfully")
    @ApiResponse(responseCode = "404", description = "Tour package not found")
    @PutMapping("/{id}")
    public Mono<TourPackageResponseDTO> updateTour(
            @Parameter(description = "ID of the tour package to be updated") @PathVariable Long id,
            @Valid @RequestBody TourPackageRequestDTO requestDTO) {
        log.info(AppConstants.LOG_REQUEST_START, "PUT", AppConstants.API_BASE_PATH + "/" + id);
        return tourPackageService.updateTour(id, requestDTO);
    }

@Operation(summary = "Delete a tour package", description = "Performs a soft delete by setting the 'active' flag to false")
    @ApiResponse(responseCode = "204", description = "Tour package deleted successfully")
    @ApiResponse(responseCode = "404", description = "Tour package not found")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTour(@Parameter(description = "ID of the tour package to be deleted") @PathVariable Long id) {
        log.info(AppConstants.LOG_REQUEST_START, "DELETE", AppConstants.API_BASE_PATH + "/" + id);
        return tourPackageService.deleteTour(id);
    }
}