package com.kuoni.tumlare.tourpackageservice.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing a Tour Package in the system.
 * 
 * @author Mohit Kumar
 * @version 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@Table("TOUR_PACKAGE_TTS")
public class TourPackage {

    @Id
    private Long id;

    @Column("NAME")
    private String name;

    @Column("DESCRIPTION")
    private String description;

    @Column("LOCATION")
    private String location;

    @Column("PRICE")
    private BigDecimal price;

    @Column("DURATION_DAYS")
    private Integer durationDays;

    @Column("AVAILABLE_SLOTS")
    private Integer availableSlots;

    @Column("CREATED_AT")
    private LocalDateTime createdAt;

    @Column("UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column("ACTIVE")
    private Integer active;

    public TourPackage(Long id, String name, String description, String location, BigDecimal price, Integer durationDays, Integer availableSlots, LocalDateTime createdAt, LocalDateTime updatedAt, Integer active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.price = price;
        this.durationDays = durationDays;
        this.availableSlots = availableSlots;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Integer getDurationDays() { return durationDays; }
    public void setDurationDays(Integer durationDays) { this.durationDays = durationDays; }
    public Integer getAvailableSlots() { return availableSlots; }
    public void setAvailableSlots(Integer availableSlots) { this.availableSlots = availableSlots; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Integer getActive() { return active; }
    public void setActive(Integer active) { this.active = active; }

    public static TourPackageBuilder builder() {
        return new TourPackageBuilder();
    }

    public static class TourPackageBuilder {
        private Long id;
        private String name;
        private String description;
        private String location;
        private BigDecimal price;
        private Integer durationDays;
        private Integer availableSlots;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Integer active;

        public TourPackageBuilder id(Long id) { this.id = id; return this; }
        public TourPackageBuilder name(String name) { this.name = name; return this; }
        public TourPackageBuilder description(String description) { this.description = description; return this; }
        public TourPackageBuilder location(String location) { this.location = location; return this; }
        public TourPackageBuilder price(BigDecimal price) { this.price = price; return this; }
        public TourPackageBuilder durationDays(Integer durationDays) { this.durationDays = durationDays; return this; }
        public TourPackageBuilder availableSlots(Integer availableSlots) { this.availableSlots = availableSlots; return this; }
        public TourPackageBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public TourPackageBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public TourPackageBuilder active(Integer active) { this.active = active; return this; }
        public TourPackage build() {
            return new TourPackage(id, name, description, location, price, durationDays, availableSlots, createdAt, updatedAt, active);
        }
    }
}