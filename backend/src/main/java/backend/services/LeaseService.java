package backend.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import backend.DTOs.CreateUpdateLeaseDTO;
import backend.DTOs.LeaseDTO;
import backend.exceptions.NotFoundException;
import backend.mappers.LeaseMapper;
import backend.models.Lease;
import backend.repositories.LeaseRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaseService {

  @Autowired
  private LeaseRepository repo;

  @Autowired
  private LeaseMapper mapper;

  // @GetMapping("/{id}")
  public LeaseDTO getLeaseById(Long id) {
    return mapper.toDTO(repo.findById(id)
        .orElseThrow(() -> new NotFoundException(id)));
  }

  // @GetMapping("")
  public Page<LeaseDTO> getAllLeasesPageable(Pageable pageable, LocalDate leaseStartDate, LocalDate leaseEndDate,
      Double rentAmount, Double securityDepositAmount, Long tenantId,
      Long apartmentId) {

    Specification<Lease> spec = Specification.where(null);

    if (leaseStartDate != null) {
      spec = spec.and((root, query, cb) -> cb.equal(root.get("leaseStartDate"), leaseStartDate));
    }

    if (leaseEndDate != null) {
      spec = spec.and((root, query, cb) -> cb.equal(root.get("leaseEndDate"), leaseEndDate));
    }

    if (rentAmount != null) {
      spec = spec.and((root, query, cb) -> cb.equal(root.get("rentAmount"), rentAmount));
    }

    if (securityDepositAmount != null) {
      spec = spec.and((root, query, cb) -> cb.equal(root.get("securityDepositAmount"), securityDepositAmount));
    }

    if (tenantId != null) {
      spec = spec.and((root, query, cb) -> cb.equal(root.join("tenant").get("id"), tenantId));
    }

    if (apartmentId != null) {
      spec = spec.and((root, query, cb) -> cb.equal(root.join("apartment").get("id"), apartmentId));
    }

    Page<Lease> leasePage = repo.findAll(spec, pageable);
    Page<LeaseDTO> leaseDTOPage = leasePage.map(mapper::toDTO);

    return leaseDTOPage;
  }

  // @PostMapping("")
  public LeaseDTO postLease(CreateUpdateLeaseDTO newLeaseDTO) {

    // Map DTO into entity
    Lease lease = mapper.toEntity(newLeaseDTO);
    lease = repo.save(lease);

    // Map saved entity into a response DTO
    LeaseDTO responseDTO = mapper.toDTO(lease);

    return responseDTO;
  }

  // @PatchMapping("/{id}")
  public LeaseDTO updateLease(Long id, CreateUpdateLeaseDTO leaseDetails) {

    // Check if lease exists
    repo.findById(id).orElseThrow(() -> new NotFoundException(id)); // test for not found in patch

    // Map dto to the already existing entity
    Lease updatedLease = mapper.toEntity(leaseDetails);
    updatedLease.setId(id); // to keep original id, avoid autogeneration
    repo.save(updatedLease);

    // Map saved entity back into responseDTO and return
    LeaseDTO responseDTO = mapper.toDTO(updatedLease);

    return responseDTO;
  }

  // @DeleteMapping("/{id}")
  public LeaseDTO deleteLeaseById(Long id) {

    // Map lease into DTO
    LeaseDTO leaseDTO = mapper.toDTO(repo.findById(id).orElseThrow(() -> new NotFoundException(id)));

    repo.deleteById(id);

    return leaseDTO;
  }
}
