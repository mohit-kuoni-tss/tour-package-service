package com.kuoni.tumlare.tourpackageservice.service;

import com.kuoni.tumlare.tourpackageservice.dto.TourPackageRequestDTO;
import com.kuoni.tumlare.tourpackageservice.dto.TourPackageResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for Tour Package operations.
 * 
 * @author Mohit Kumar
 * @version 1.0
 */
public interface TourPackageService {

    /**
     * Creates a new tour package.
     *
     * @param requestDTO the tour package details
     * @return the created tour package
     */
    Mono<TourPackageResponseDTO> createTour(TourPackageRequestDTO requestDTO);

    /**
     * Retrieves a tour package by its ID.
     *
     * @param id the tour package ID
     * @return the found tour package
     */
    Mono<TourPackageResponseDTO> getTourById(Long id);

    /**
     * Retrieves all active tour packages.
     *
     * @return a Flux of tour packages
     */
    Flux<TourPackageResponseDTO> getAllTours();

    /**
     * Updates an existing tour package.
     *
     * @param id the tour package ID
     * @param requestDTO the updated details
     * @return the updated tour package
     */
    Mono<TourPackageResponseDTO> updateTour(Long id, TourPackageRequestDTO requestDTO);

    /**
     * Soft deletes a tour package by setting active to false.
     *
     * @param id the tour package ID
     * @return a Mono signaling completion
     */
    Mono<Void> deleteTour(Long id);
}

