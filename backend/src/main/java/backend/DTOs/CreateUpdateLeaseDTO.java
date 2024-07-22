package backend.DTOs;

import java.time.LocalDate;

import backend.models.Apartment;
import backend.models.Tenant;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUpdateLeaseDTO {

    @NotNull(message = "Lease start date cannot be null")
    private LocalDate leaseStartDate;

    @NotNull(message = "Lease end date cannot be null")
    private LocalDate leaseEndDate;

    @DecimalMin(value = "0.0", inclusive = false, message = "Rent amount must be greater than 0")
    @NotNull(message = "Rent amount cannot be null")
    private Double rentAmount;

    @DecimalMin(value = "0.0", inclusive = false, message = "Security deposit amount must be greater than 0")
    @NotNull(message = "Security deposit amount cannot be null")
    private Double securityDepositAmount;

    @NotNull(message = "Tenant cannot be null")
    private Tenant tenant;

    @NotNull(message = "Apartment cannot be null")
    private Apartment apartment;
}

