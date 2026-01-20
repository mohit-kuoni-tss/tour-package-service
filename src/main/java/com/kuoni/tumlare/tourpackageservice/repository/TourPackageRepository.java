package com.kuoni.tumlare.tourpackageservice.repository;

import com.kuoni.tumlare.tourpackageservice.entity.TourPackage;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Reactive Repository for {@link TourPackage} entity.
 * 
 * @author Mohit Kumar
 * @version 1.0
 */
@Repository
public interface TourPackageRepository extends ReactiveCrudRepository<TourPackage, Long> {

    /**
     * Finds all active tour packages.
     *
     * @return a Flux of active tour packages
     */
    @Query("SELECT ID, NAME, DESCRIPTION, LOCATION, PRICE, DURATION_DAYS, AVAILABLE_SLOTS, ACTIVE, CREATED_AT, UPDATED_AT FROM TOUR_PACKAGE_TTS WHERE ACTIVE = 1")
    Flux<TourPackage> findAllActive();
}