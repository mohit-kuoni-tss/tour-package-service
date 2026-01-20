package com.kuoni.tumlare.tourpackageservice.mapper;

import com.kuoni.tumlare.tourpackageservice.dto.TourPackageRequestDTO;
import com.kuoni.tumlare.tourpackageservice.dto.TourPackageResponseDTO;
import com.kuoni.tumlare.tourpackageservice.entity.TourPackage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Manual mapper for converting between Entities and DTOs.
 * 
 * @author Mohit Kumar
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TourPackageMapper {

    /**
     * Converts Request DTO to Entity for creation.
     *
     * @param dto the request DTO
     * @return the tour package entity
     */
    public static TourPackage toEntity(TourPackageRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return TourPackage.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .price(dto.getPrice())
                .durationDays(dto.getDurationDays())
                .availableSlots(dto.getAvailableSlots())
                .active(1) // Default to active (1) on creation
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    /**
     * Updates an existing entity with values from Request DTO.
     *
     * @param entity the existing entity
     * @param dto the request DTO
     */
    public static void updateEntity(TourPackage entity, TourPackageRequestDTO dto) {
        if (entity == null || dto == null) {
            return;
        }

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setLocation(dto.getLocation());
        entity.setPrice(dto.getPrice());
        entity.setDurationDays(dto.getDurationDays());
        entity.setAvailableSlots(dto.getAvailableSlots());
        entity.setUpdatedAt(LocalDateTime.now());
    }

    /**
     * Converts Entity to Response DTO.
     *
     * @param entity the tour package entity
     * @return the response DTO
     */
    public static TourPackageResponseDTO toResponseDTO(TourPackage entity) {
        if (entity == null) {
            return null;
        }

        return TourPackageResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .location(entity.getLocation())
                .price(entity.getPrice())
                .durationDays(entity.getDurationDays())
                .availableSlots(entity.getAvailableSlots())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .active(entity.getActive() != null && entity.getActive() == 1)
                .build();
    }
}

