package com.kuoni.tumlare.tourpackageservice.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Tour Package response.
 * 
 * @author Mohit Kumar
 * @version 1.0
 */
@Getter
@Setter
@Builder
//@Schema(description = "Response DTO representing a Tour Package")
public class TourPackageResponseDTO {

//    @Schema(description = "Unique identifier of the tour package")
    private Long id;

//    @Schema(description = "Name of the tour package")
    private String name;

//    @Schema(description = "Detailed description of the tour")
    private String description;

//    @Schema(description = "Primary location of the tour")
    private String location;

//    @Schema(description = "Price of the tour package")
    private BigDecimal price;

//    @Schema(description = "Duration of the tour in days")
    private Integer durationDays;

//    @Schema(description = "Number of slots available for booking")
    private Integer availableSlots;

//    @Schema(description = "Timestamp when the package was created")
    private LocalDateTime createdAt;

//    @Schema(description = "Timestamp when the package was last updated")
    private LocalDateTime updatedAt;

//    @Schema(description = "Flag indicating if the tour package is active")
    private Boolean active;

    public TourPackageResponseDTO() {
    }

    public TourPackageResponseDTO(Long id, String name, String description, String location, BigDecimal price, Integer durationDays, Integer availableSlots, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean active) {
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
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public static TourPackageResponseDTOBuilder builder() {
        return new TourPackageResponseDTOBuilder();
    }

    public static class TourPackageResponseDTOBuilder {
        private Long id;
        private String name;
        private String description;
        private String location;
        private BigDecimal price;
        private Integer durationDays;
        private Integer availableSlots;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Boolean active;

        public TourPackageResponseDTOBuilder id(Long id) { this.id = id; return this; }
        public TourPackageResponseDTOBuilder name(String name) { this.name = name; return this; }
        public TourPackageResponseDTOBuilder description(String description) { this.description = description; return this; }
        public TourPackageResponseDTOBuilder location(String location) { this.location = location; return this; }
        public TourPackageResponseDTOBuilder price(BigDecimal price) { this.price = price; return this; }
        public TourPackageResponseDTOBuilder durationDays(Integer durationDays) { this.durationDays = durationDays; return this; }
        public TourPackageResponseDTOBuilder availableSlots(Integer availableSlots) { this.availableSlots = availableSlots; return this; }
        public TourPackageResponseDTOBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public TourPackageResponseDTOBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public TourPackageResponseDTOBuilder active(Boolean active) { this.active = active; return this; }
        public TourPackageResponseDTO build() {
            return new TourPackageResponseDTO(id, name, description, location, price, durationDays, availableSlots, createdAt, updatedAt, active);
        }
    }
}

