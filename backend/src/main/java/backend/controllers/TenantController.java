package backend.controllers;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.DTOs.CreateUpdateTenantDTO;
import backend.DTOs.TenantDTO;
import backend.services.TenantService;
import backend.validations.PageableConstraint;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/tenant")
public class TenantController {

  private static final Logger logger = LoggerFactory.getLogger(TenantController.class);

  @Autowired
  private TenantService tenantService;

  @GetMapping("/{id}")
  public TenantDTO getTenantById(@PathVariable Long id) {
    logger.info("Searching for tenant with ID: {}", id);
    return tenantService.getTenantById(id);
  }

  @GetMapping("")
  public ResponseEntity<Page<TenantDTO>> getAllTenant(
      @Valid @PageableConstraint(maxPerPage = 50) @PageableDefault(page = 0, size = 25, sort = "id") Pageable pageable,
      @RequestParam(required = false) String firstName,
      @RequestParam(required = false) String lastName,
      @RequestParam(required = false) LocalDate dateOfBirth,
      @RequestParam(required = false) String email,
      @RequestParam(required = false) String phoneNumber,
      @RequestParam(required = false) String socialSecurityNumber,
      @RequestParam(required = false) String driversLicenseNumber,
      @RequestParam(required = false) String currentAddress,
      @RequestParam(required = false) String previousAddress,
      @RequestParam(required = false) String employerName,
      @RequestParam(required = false) String jobTitle,
      @RequestParam(required = false) Integer annualIncome,
      @RequestParam(required = false) String emergencyContactName,
      @RequestParam(required = false) String emergencyContactRelationship,
      @RequestParam(required = false) String emergencyContactPhone,
      @RequestParam(required = false) String referenceName,
      @RequestParam(required = false) String referenceRelationship,
      @RequestParam(required = false) String referencePhone) {
    logger.info("Searching for all tenants");
    return ResponseEntity.ok(tenantService.getAllTenantsPageable(pageable,
        firstName, lastName, dateOfBirth, email, phoneNumber, socialSecurityNumber,
        driversLicenseNumber, currentAddress, previousAddress, employerName,
        jobTitle, annualIncome, emergencyContactName,
        emergencyContactRelationship, emergencyContactPhone, referenceName, referenceRelationship, referencePhone));
  }

  @PostMapping("")
  public ResponseEntity<TenantDTO> postTenant(@Valid @RequestBody CreateUpdateTenantDTO newTenantDTO) {
    logger.info("Creating Tenant Details: {}", newTenantDTO);
    return ResponseEntity.ok(tenantService.postTenant(newTenantDTO));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<TenantDTO> updateTenant(@Valid @PathVariable Long id,
      @RequestBody CreateUpdateTenantDTO tenantDetails) {
    logger.info("Updating Tenant with ID: {}, Details: {}", id, tenantDetails);
    return ResponseEntity.ok(tenantService.updateTenant(id, tenantDetails));
  }

  @DeleteMapping("/{id}")
  @Transactional(rollbackFor = Exception.class)
  public ResponseEntity<TenantDTO> deleteById(@PathVariable Long id) {
    logger.info("Deleteing tenant with ID: {}", id);
    return ResponseEntity.ok(tenantService.deleteTenantById(id));
  }
}
