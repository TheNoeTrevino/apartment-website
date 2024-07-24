package backend.DTOs;

import java.time.LocalDate;

import backend.models.Lease;
import backend.models.TenantStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TenantDTO {
    private long tenantId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private String socialSecurityNumber;
    private String driversLicenseNumber;
    private String currentAddress;
    private String previousAddress;
    private String employerName;
    private String jobTitle;
    private Integer annualIncome;
    private String emergencyContactName;
    private String emergencyContactRelationship;
    private String emergencyContactPhone;
    private String referenceName;
    private String referenceRelationship;
    private String referencePhone;
    private long apartmentId;
    private Double rentDue;
    private TenantStatus tenantStatus;
    private long leaseId;
}
