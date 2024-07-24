package backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class MaintenanceRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @NotNull
  private String description;

  @NotNull
  private LocalDateTime requestDate;

  @ManyToOne
  @JoinColumn(name = "tenant_id", nullable = false)
  private Tenant tenant;

  @NotNull
  private String type;

  public MaintenanceRequest setDescription(String description) {
    this.description = description;
    return this;
  }

  public MaintenanceRequest setRequestDate(LocalDateTime requestDate) {
    this.requestDate = requestDate;
    return this;
  }

  public MaintenanceRequest setTenant(Tenant tenant) {
    this.tenant = tenant;
    return this;
  }

  public MaintenanceRequest setType(String type) {
    this.type = type;
    return this;
  }
}
