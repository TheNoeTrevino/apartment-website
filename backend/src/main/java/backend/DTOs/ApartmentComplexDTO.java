package backend.DTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApartmentComplexDTO {
    private long complexId;

    @Size(max = 255, message = "The name of this Apartment Complex is too long. Please submit a name under 255 characters.")
    @NotNull(message = "Apartment Complex Name can not be null.")
    private String complexName;

    @Size(max = 255, message = "The name of this Apartment Complex is too long. Please submit a name under 255 characters.")
    @NotNull(message = "Apartment Complex Name can not be null.")
    private String complexLocation;

    @Min(value = 0, message = "You can not win negative times")
    @Max(value = 21, message = "The Blue Devils have the most championships, at 21. This can not be true.")
    @NotNull(message = "The number of championships can not be null.")
    private Integer numOfBuildings;

    @Min(value = 0, message = "There cannot be negative number of units")
    @NotNull(message = "The number of units can not be null.")
    private Integer numOfUnits;

    @PastOrPresent(message = "Date Built must be in the past, or the current day.")
    @NotNull(message = "Date Built can not be null.")
    private LocalDate dateBuilt;

    @Size(max = 255, message = "The name of this Apartment Complex is too long. Please submit a name under 255 characters.")
    @NotNull(message = "Apartment Complex Name can not be null.")
    private String managerName;

    @Size(max = 255, message = "The name of this Apartment Complex is too long. Please submit a name under 255 characters.")
    @NotNull(message = "Apartment Complex Name can not be null.")
    private String managerEmail;

    @Size(max = 255, message = "The name of this Apartment Complex is too long. Please submit a name under 255 characters.")
    @NotNull(message = "Apartment Complex Name can not be null.")
    private String managerPhone;
}
