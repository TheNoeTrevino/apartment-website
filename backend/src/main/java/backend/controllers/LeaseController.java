package backend.controllers;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.DTOs.CreateUpdateLeaseDTO;
import backend.DTOs.LeaseDTO;
import backend.services.LeaseService;
import backend.validations.PageableConstraint;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/leases")
public class LeaseController {

  private static final Logger logger = LoggerFactory.getLogger(LeaseController.class);

  @Autowired
  private LeaseService leaseService;

  @GetMapping("/{id}")
  public LeaseDTO getLeaseById(@PathVariable Long id) {
    return leaseService.getLeaseById(id);
  }

  @GetMapping("")
  public ResponseEntity<Page<LeaseDTO>> getAllLeases(
      @PageableDefault(page = 0, size = 10, sort = { "leaseStartDate" })
      @Valid @PageableConstraint(message = "Invalid page size", maxPerPage = 50) Pageable pageable,
      @RequestParam(required = false) LocalDate leaseStartDate,
      @RequestParam(required = false) LocalDate leaseEndDate,
      @RequestParam(required = false) Double rentAmount,
      @RequestParam(required = false) Double securityDepositAmount,
      @RequestParam(required = false) Long tenantId,
      @RequestParam(required = false) Long apartmentId) {

    return ResponseEntity.ok(leaseService.getAllLeasesPageable(pageable,
        leaseStartDate, leaseEndDate, rentAmount, securityDepositAmount, tenantId, apartmentId));
  }

  @PostMapping("")
  public ResponseEntity<LeaseDTO> createLease(@Valid @RequestBody CreateUpdateLeaseDTO newLeaseDTO) {
    return ResponseEntity.ok(leaseService.postLease(newLeaseDTO));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<LeaseDTO> updateLease(@Valid @PathVariable Long id,
      @RequestBody CreateUpdateLeaseDTO leaseDetails) {
    logger.info("Updating Lease with ID: {}, Details: {}", id, leaseDetails);
    return ResponseEntity.ok(leaseService.updateLease(id, leaseDetails));
  }

  @DeleteMapping("/{id}") // add transactional here?
  public ResponseEntity<LeaseDTO> deleteById(@PathVariable Long id) {
    return ResponseEntity.ok(leaseService.deleteLeaseById(id));
  }
}
