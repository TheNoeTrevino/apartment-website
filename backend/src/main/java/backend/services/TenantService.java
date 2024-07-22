package backend.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.ArrayBuilders.BooleanBuilder;

import backend.DTOs.CreateUpdateTenantDTO;
import backend.DTOs.TenantDTO;
import backend.exceptions.NotFoundException;
import backend.mappers.TenantMapper;
import backend.models.Tenant;
import backend.repositories.TenantRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantService {

    @Autowired
    private final TenantRepository repo;

    @Autowired
    private final TenantMapper mapper;

    // @GetMapping("/{id}")
    public TenantDTO getTenantById(Long id) {
        return mapper.toDTO(repo.findById(id)
            .orElseThrow(() -> new NotFoundException(id)));
    }

    // @GetMapping("")
    public Page<TenantDTO> getAllTenantsPageable(Pageable pageable, String firstName, String lastName,
                                                 LocalDate dateOfBirth, String email, String phoneNumber,
                                                 String socialSecurityNumber, String driversLicenseNumber,
                                                 String currentAddress, String previousAddress, String employerName,
                                                 String jobTitle, Integer annualIncome, String emergencyContactName,
                                                 String emergencyContactRelationship, String emergencyContactPhone,
                                                 String referenceName, String referenceRelationship,
                                                 String referencePhone) {

    Specification<Tenant> spec = Specification.where(null);

    if (firstName != null) {
        spec = spec.and((root, query, cb) ->
            cb.like(cb.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%")
        );
    }

    if (lastName != null) {
        spec = spec.and((root, query, cb) ->
            cb.like(cb.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%")
        );
    }

    if (dateOfBirth != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("dateOfBirth"), dateOfBirth)
        );
    }

    if (email != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("email"), email)
        );
    }

    if (phoneNumber != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("phoneNumber"), phoneNumber)
        );
    }

    if (socialSecurityNumber != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("socialSecurityNumber"), socialSecurityNumber)
        );
    }

    if (driversLicenseNumber != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("driversLicenseNumber"), driversLicenseNumber)
        );
    }

    if (currentAddress != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("currentAddress"), currentAddress)
        );
    }

    if (previousAddress != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("previousAddress"), previousAddress)
        );
    }

    if (employerName != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("employerName"), employerName)
        );
    }

    if (jobTitle != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("jobTitle"), jobTitle)
        );
    }

    if (annualIncome != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("annualIncome"), annualIncome)
        );
    }

    if (emergencyContactName != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("emergencyContactName"), emergencyContactName)
        );
    }

    if (emergencyContactRelationship != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("emergencyContactRelationship"), emergencyContactRelationship)
        );
    }

    if (emergencyContactPhone != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("emergencyContactPhone"), emergencyContactPhone)
        );
    }

    if (referenceName != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("referenceName"), referenceName)
        );
    }

    if (referenceRelationship != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("referenceRelationship"), referenceRelationship)
        );
    }

    if (referencePhone != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("referencePhone"), referencePhone)
        );
    }

    Page<Tenant> tenantPage = repo.findAll(spec, pageable);
    Page<TenantDTO> tenantDTOPage = tenantPage.map(mapper::toDTO);

    return tenantDTOPage;
}

    // @PostMapping("")
    public TenantDTO postTenant(CreateUpdateTenantDTO newTenantDTO) {

        // Map DTO into entity
        Tenant tenant = mapper.toEntity(newTenantDTO);
        tenant = repo.save(tenant);

        // Map saved entity into a response DTO
        TenantDTO responseDTO = mapper.toDTO(tenant);

        return responseDTO;
    }

    // @PatchMapping("/{id}")
    public TenantDTO updateTenant(Long id, CreateUpdateTenantDTO tenantDetails) {

        // Check if tenant exists
        repo.findById(id).orElseThrow(() -> new NotFoundException(id)); // test for not found in patch

        // Map dto to the already existing entity
        Tenant updatedTenant = mapper.toEntity(tenantDetails);
        updatedTenant.setId(id); // to keep original id, avoid autogeneration
        repo.save(updatedTenant);

        // Map saved entity back into responseDTO and return
        TenantDTO responseDTO = mapper.toDTO(updatedTenant);

        return responseDTO;
    }

    // @DeleteMapping("/{id}")
    public TenantDTO deleteTenantById(Long id) {

        // Map tenant into DTO
        TenantDTO tenantDTO = mapper.toDTO(repo.findById(id).
            orElseThrow(() -> new NotFoundException(id)));

        repo.deleteById(id);

        return tenantDTO;
    }
}

