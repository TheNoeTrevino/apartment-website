package backend.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
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
    @Max(value = 4, message = "blah blah blah!") // talk to mother about this
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

    @OneToMany(mappedBy = "apartmentComplex", cascade = CascadeType.ALL)
    private List<Apartment> apartments;

    public ApartmentComplex(String complexName, String complexLocation, Integer numOfBuildings,
                    Integer numOfUnits, String managerName, String managerEmail, String managerPhone) {

        this.complexName = complexName;
        this.complexLocation = complexLocation;
        this.numOfBuildings = numOfBuildings;
        this.numOfUnits = numOfUnits;
        this.managerName = managerName;
        this.managerPhone = managerPhone;
    }
}
