package backend.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
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
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(max = 255, message = "The name of this apartment is too long. Please submit a name under 255 characters.")
    @NotNull(message = "Apartment name cannot be null.")
    private String apartmentName;

    @Size(max = 255, message = "The location of this apartment is too long. Please submit a location under 255 characters.")
    @NotNull(message = "Apartment location cannot be null.")
    private String apartmentLocation;

    @Min(value = 0, message = "The number of rooms cannot be negative.")
    @NotNull(message = "The number of rooms cannot be null.")
    private Integer numOfRooms;

    @Min(value = 0, message = "The square footage cannot be negative.")
    @NotNull(message = "The square footage cannot be null.")
    private Integer squareFootage;

    @PastOrPresent(message = "Date Built must be in the past or the current day.")
    @NotNull(message = "Date Built cannot be null.")
    private LocalDate dateBuilt;

    @Size(max = 255, message = "The name of the current tenant is too long. Please submit a name under 255 characters.")
    private String currentTenantName;

    @Size(max = 255, message = "The email of the current tenant is too long. Please submit an email under 255 characters.")
    @Email(message = "Tenant email should be valid")
    private String currentTenantEmail;

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Tenant phone number is invalid")
    @Size(max = 255, message = "The phone number of the current tenant is too long. Please submit a phone number under 255 characters.")
    private String currentTenantPhone;

    @ManyToOne
    @JoinColumn(name = "apartment_complex_id", nullable = false)
    private ApartmentComplex apartmentComplex;
}
