package com.kuoni.tumlare.tourpackageservice.dto;

//import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * Data Transfer Object for creating or updating a Tour Package.
 * 
 * @author Mohit Kumar
 * @version 1.0
 */
@Getter
@Setter
@Builder
public class TourPackageRequestDTO {

    @NotBlank(message = "Tour name is mandatory")
    private String name;

    private String description;

    @NotBlank(message = "Location is mandatory")
    private String location;

    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private BigDecimal price;

    @NotNull(message = "Duration is mandatory")
    @Min(value = 1, message = "Duration must be at least 1 day")
    private Integer durationDays;

    @NotNull(message = "Available slots are mandatory")
    @Min(value = 0, message = "Available slots cannot be negative")
    private Integer availableSlots;

    public TourPackageRequestDTO() {}

    public TourPackageRequestDTO(String name, String description, String location, BigDecimal price, Integer durationDays, Integer availableSlots) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.price = price;
        this.durationDays = durationDays;
        this.availableSlots = availableSlots;
    }

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

    public static TourPackageRequestDTOBuilder builder() {
        return new TourPackageRequestDTOBuilder();
    }

    public static class TourPackageRequestDTOBuilder {
        private String name;
        private String description;
        private String location;
        private BigDecimal price;
        private Integer durationDays;
        private Integer availableSlots;

        public TourPackageRequestDTOBuilder name(String name) { this.name = name; return this; }
        public TourPackageRequestDTOBuilder description(String description) { this.description = description; return this; }
        public TourPackageRequestDTOBuilder location(String location) { this.location = location; return this; }
        public TourPackageRequestDTOBuilder price(BigDecimal price) { this.price = price; return this; }
        public TourPackageRequestDTOBuilder durationDays(Integer durationDays) { this.durationDays = durationDays; return this; }
        public TourPackageRequestDTOBuilder availableSlots(Integer availableSlots) { this.availableSlots = availableSlots; return this; }
        public TourPackageRequestDTO build() {
            return new TourPackageRequestDTO(name, description, location, price, durationDays, availableSlots);
        }
    }
}

