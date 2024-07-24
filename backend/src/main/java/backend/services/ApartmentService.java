package backend.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import backend.DTOs.ApartmentDTO;
import backend.DTOs.CreateUpdateApartmentDTO;
import backend.exceptions.NotFoundException;
import backend.mappers.ApartmentMapper;
import backend.models.Apartment;
import backend.repositories.ApartmentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApartmentService {

    private final ApartmentRepository repo;

    private final ApartmentMapper mapper;

    // @GetMapping("/{id}")
    public ApartmentDTO getApartmentById(Long id) {
        return mapper.toDTO(repo.findById(id)
            .orElseThrow(() -> new NotFoundException(id)));
    }

    // @GetMapping("")
    public Page<ApartmentDTO> getAllApartmentsPageable(Pageable pageable, String apartmentName, String apartmentLocation,
                                                Integer numOfRooms, Integer squareFootage, LocalDate dateBuilt,
                                                String currentTenantName, String currentTenantEmail, String currentTenantPhone) {

        // Building query
    Specification<Apartment> spec = Specification.where(null);

    if (apartmentName != null) {
        spec = spec.and((root, query, cb) ->
            cb.like(cb.lower(root.get("apartmentName")), "%" + apartmentName.toLowerCase() + "%")
        );
    }

    if (apartmentLocation != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("apartmentLocation"), apartmentLocation)
        );
    }

    if (numOfRooms != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("numOfRooms"), numOfRooms)
        );
    }

    if (squareFootage != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("squareFootage"), squareFootage)
        );
    }

    if (dateBuilt != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("dateBuilt"), dateBuilt)
        );
    }

    if (currentTenantName != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("currentTenantName"), currentTenantName)
        );
    }

    if (currentTenantEmail != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("currentTenantEmail"), currentTenantEmail)
        );
    }

    if (currentTenantPhone != null) {
        spec = spec.and((root, query, cb) ->
            cb.equal(root.get("currentTenantPhone"), currentTenantPhone)
        );
    }

    Page<Apartment> apartmentPage = repo.findAll(spec, pageable);
    Page<ApartmentDTO> apartmentDTOPage = apartmentPage.map(mapper::toDTO);

    return apartmentDTOPage;
}

    // @PostMapping("")
    public ApartmentDTO postApartment(CreateUpdateApartmentDTO newApartmentDTO) {

        // Map DTO into entity
        Apartment apartment = mapper.toEntity(newApartmentDTO);
        apartment = repo.save(apartment);

        // Map saved entity into a response DTO
        ApartmentDTO responseDTO = mapper.toDTO(apartment);

        return responseDTO;
    }

    // @PatchMapping("/{id}")
    public ApartmentDTO updateApartment(Long id, CreateUpdateApartmentDTO apartmentDetails) {

        // Check if apartment exists
        repo.findById(id).orElseThrow(() -> new NotFoundException(id)); // test for not found in patch

        // Map dto to the already existing entity
        Apartment updatedApartment = mapper.toEntity(apartmentDetails);
        updatedApartment.setId(id); // to keep original id, avoid autogeneration
        repo.save(updatedApartment);

        // Map saved entity back into responseDTO and return
        ApartmentDTO responseDTO = mapper.toDTO(updatedApartment);

        return responseDTO;
    }

    // @DeleteMapping("/{id}")
    public ApartmentDTO deleteApartmentById(Long id) {

        // Map apartment into DTO
        ApartmentDTO apartmentDTO = mapper.toDTO(repo.findById(id).
            orElseThrow(() -> new NotFoundException(id)));

        repo.deleteById(id);

        return apartmentDTO;
    }
}

