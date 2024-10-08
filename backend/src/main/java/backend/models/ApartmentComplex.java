package backend.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Data
public class ApartmentComplex {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(max = 255, message = "The name of this Apartment Complex is too long. Please submit a name under 255 characters.")
    @NotNull(message = "Apartment Complex Name can not be null.")
    private String complexName;

    @Size(max = 255, message = "The name of this Apartment Complex is too long. Please submit a name under 255 characters.")
    @NotNull(message = "Apartment Complex Name can not be null.")
    private String complexLocation;

    @Min(value = 0, message = "Building numbers cannot be negative")
    @NotNull(message = "The number of championships can not be null.")
    private Integer numOfBuildings;

    @Min(value = 0, message = "There cannot be negative number of units")
    @NotNull(message = "The number of units can not be null.")
    private Integer numOfUnits;

    @PastOrPresent(message = "Date Built must be in the past, or the current day.")
    @NotNull(message = "Date Built can not be null.")
    private LocalDate dateBuilt;

    @Size(max = 255, message = "The manager's name is too long. Please submit a name under 255 characters.")
    @NotNull(message = "Manager Name can not be null.")
    private String managerName;

    @Email(message = "Manager email should be valid")
    @Size(max = 255, message = "The manager's email is too long. Please submit an email under 255 characters.")
    @NotNull(message = "Manager Email can not be null.")
    private String managerEmail;

    @Size(max = 20, message = "Phone number is too long")
    @NotNull(message = "Apartment Complex Name can not be null.")
    private String managerPhone;

    @OneToMany(mappedBy = "apartmentComplex")
    private List<Apartment> apartments;
}
