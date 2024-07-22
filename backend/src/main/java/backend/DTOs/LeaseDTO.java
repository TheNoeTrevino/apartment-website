package backend.DTOs;

import java.time.LocalDate;

import backend.models.Apartment;
import backend.models.Tenant;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
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
@Data
public class LeaseDTO {

  @NotNull(message = "Lease start date cannot be null")
  private long leaseId;

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

  @ManyToOne
  @JoinColumn(name = "tenant_id", nullable = false)
  private Tenant tenant;

  @ManyToOne
  @JoinColumn(name = "apartment_id", nullable = false)
  private Apartment apartment;
}
