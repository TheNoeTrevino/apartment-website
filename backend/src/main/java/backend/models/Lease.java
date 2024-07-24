package backend.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
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
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Lease start date cannot be null")
    private LocalDate leaseStartDate;

    @NotNull(message = "Lease end date cannot be null")
    @FutureOrPresent(message = "Lease end date must be in the future or the current day")
    private LocalDate leaseEndDate;

    @DecimalMin(value = "0.0", inclusive = false, message = "Rent amount must be greater than 0")
    @NotNull(message = "Rent amount cannot be null")
    private Double rentAmount;

    @DecimalMin(value = "0.0", inclusive = false, message = "Security deposit amount must be greater than 0")
    @NotNull(message = "Security deposit amount cannot be null")
    private Double securityDepositAmount;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @OneToOne
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment;
}

