package com.kuoni.tumlare.tourpackageservice.service.impl;

import com.kuoni.tumlare.tourpackageservice.dto.TourPackageRequestDTO;
import com.kuoni.tumlare.tourpackageservice.dto.TourPackageResponseDTO;
import com.kuoni.tumlare.tourpackageservice.entity.TourPackage;
import com.kuoni.tumlare.tourpackageservice.exception.TourNotFoundException;
import com.kuoni.tumlare.tourpackageservice.mapper.TourPackageMapper;
import com.kuoni.tumlare.tourpackageservice.repository.TourPackageRepository;
import com.kuoni.tumlare.tourpackageservice.service.TourPackageService;
import com.kuoni.tumlare.tourpackageservice.util.AppConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Implementation of {@link TourPackageService} using Reactive repositories.
 * 
 * @author Mohit Kumar
 * @version 1.0
 */
@Service
public class TourPackageServiceImpl implements TourPackageService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TourPackageServiceImpl.class);

    private final TourPackageRepository tourPackageRepository;

    public TourPackageServiceImpl(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }

@Override
    @Transactional
    public Mono<TourPackageResponseDTO> createTour(TourPackageRequestDTO requestDTO) {
        TourPackage entity = TourPackageMapper.toEntity(requestDTO);
        return tourPackageRepository.save(entity)
                .map(TourPackageMapper::toResponseDTO)
                .doOnSuccess(saved -> log.info("Tour package created with ID: {}", saved.getId()))
                .doOnError(e -> log.error("Error creating tour package: {}", e.getMessage()));
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<TourPackageResponseDTO> getTourById(Long id) {
        log.debug("Fetching tour package by ID: {}", id);
        return tourPackageRepository.findById(id)
                .filter(TourPackage::getActive)
                .map(TourPackageMapper::toResponseDTO)
                .switchIfEmpty(Mono.error(new TourNotFoundException(String.format(AppConstants.TOUR_NOT_FOUND_MSG, id))))
                .doOnError(e -> log.error("Error fetching tour package {}: {}", id, e.getMessage()));
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<TourPackageResponseDTO> getAllTours() {
        log.debug("Fetching all active tour packages");
        return tourPackageRepository.findAllActive()
                .map(TourPackageMapper::toResponseDTO)
                .doOnError(e -> log.error("Error fetching all tours: {}", e.getMessage()));
    }

    @Override
    @Transactional
    public Mono<TourPackageResponseDTO> updateTour(Long id, TourPackageRequestDTO requestDTO) {
        log.info("Updating tour package with ID: {}", id);
        return tourPackageRepository.findById(id)
                .filter(TourPackage::getActive)
                .switchIfEmpty(Mono.error(new TourNotFoundException(String.format(AppConstants.TOUR_NOT_FOUND_MSG, id))))
                .flatMap(existingEntity -> {
                    TourPackageMapper.updateEntity(existingEntity, requestDTO);
                    return tourPackageRepository.save(existingEntity);
                })
                .map(TourPackageMapper::toResponseDTO)
                .doOnSuccess(updated -> log.info("Tour package updated with ID: {}", updated.getId()))
                .doOnError(e -> log.error("Error updating tour package {}: {}", id, e.getMessage()));
    }

    @Override
    @Transactional
    public Mono<Void> deleteTour(Long id) {
        log.info("Soft deleting tour package with ID: {}", id);
        return tourPackageRepository.findById(id)
                .filter(TourPackage::getActive)
                .switchIfEmpty(Mono.error(new TourNotFoundException(String.format(AppConstants.TOUR_NOT_FOUND_MSG, id))))
                .flatMap(existingEntity -> {
                    existingEntity.setActive(false);
                    existingEntity.setUpdatedAt(LocalDateTime.now());
                    return tourPackageRepository.save(existingEntity);
                })
                .doOnSuccess(v -> log.info("Tour package soft-deleted with ID: {}", id))
                .doOnError(e -> log.error("Error deleting tour package {}: {}", id, e.getMessage()))
                .then();
    }
}