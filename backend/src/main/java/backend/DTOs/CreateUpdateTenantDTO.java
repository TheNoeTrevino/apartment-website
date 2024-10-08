package backend.DTOs;

import java.time.LocalDate;

import backend.models.TenantStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUpdateTenantDTO {

    @Size(max = 255, message = "The first name of this tenant is too long. Please submit a name under 255 characters.")
    @NotNull(message = "First name cannot be null.")
    private String firstName;

    @Size(max = 255, message = "The last name of this tenant is too long. Please submit a name under 255 characters.")
    @NotNull(message = "Last name cannot be null.")
    private String lastName;

    @PastOrPresent(message = "Date of birth must be in the past or the current day.")
    @NotNull(message = "Date of birth cannot be null.")
    private LocalDate dateOfBirth;

    @Email(message = "Email should be valid")
    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Phone number cannot be null")
    private String phoneNumber;

    @Size(min = 9, max = 9, message = "Social security number must be 9 digits.")
    @NotNull(message = "Social security number cannot be null")
    private String socialSecurityNumber;

    @NotNull(message = "Driver's license cannot be null")
    private String driversLicenseNumber;

    @NotNull(message = "Current address cannot be null")
    private String currentAddress;

    private String previousAddress;

    @NotNull(message = "Employer name cannot be null")
    private String employerName;

    private String jobTitle;

    @Min(value = 0, message = "Annual income cannot be less than 0")
    private Integer annualIncome;

    @NotNull(message = "Emergency contact name cannot be null")
    private String emergencyContactName;

    @NotNull(message = "Emergency contact relationship cannot be null")
    private String emergencyContactRelationship;

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Emergency contact phone number is invalid")
    @NotNull(message = "Emergency contact phone number cannot be null")
    private String emergencyContactPhone;

    @NotNull(message = "Reference name cannot be null")
    private String referenceName;

    @NotNull(message = "Reference relationship cannot be null")
    private String referenceRelationship;

    @NotNull(message = "Reference phone number cannot be null")
    private String referencePhone;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Tenant status cannot be null")
    private TenantStatus tenantStatus;

    @NotNull(message = "Apartment cannot be null")
    private long apartmentId;
}

