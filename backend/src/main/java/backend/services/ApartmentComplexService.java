package backend.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import backend.DTOs.ApartmentComplexDTO;
import backend.DTOs.CreateUpdateApartmentComplexDTO;
import backend.exceptions.NotFoundException;
import backend.mappers.ApartmentComplexMapper;
import backend.models.ApartmentComplex;
import backend.repositories.ApartmentComplexRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApartmentComplexService {

    @Autowired
    private ApartmentComplexRepository repo;

    @Autowired
    private ApartmentComplexMapper mapper;

    // @GetMapping("/{id}")
    public ApartmentComplexDTO getApartmentComplexById(Long id) {
        return mapper.apartmentComplexToApartmentComplexDTO(repo.findById(id)
            .orElseThrow(() -> new NotFoundException(id)));
    }

    // GetMapping("")
    public Page<ApartmentComplexDTO> getAllApartmentComplexPageable(Pageable pageable,String complexName, String complexLocation,
    Integer numOfBuildings, Integer numOfUnits, LocalDate dateBuilt, String managerName, String managerEmail, String managerPhone) {

        // Building query
        Specification<ApartmentComplex> spec = Specification.where(null);

        if (complexName != null) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("complexName")), "%" + complexName.toLowerCase() + "%"));
        }

        if (complexLocation != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("complexLocation"), complexLocation));
        }

        if (numOfBuildings != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("numOfBuildings"), numOfBuildings));
        }

        if (numOfUnits != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("numOfUnits"), numOfUnits));
        }

        if (dateBuilt != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("dateBuilt"), dateBuilt));
        }

        if (managerName != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("managerName"), managerName));
        }

        if (managerEmail != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("managerEmail"), managerEmail));
        }

        if (managerPhone != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("managerPhone"), managerPhone));
        }

        Page<ApartmentComplex> apartmentComplexPage = repo.findAll(spec, pageable);
        Page<ApartmentComplexDTO> apartmentComplexDTOPage = apartmentComplexPage.map(mapper::apartmentComplexToApartmentComplexDTO);

        return apartmentComplexDTOPage;
    }

    // PostMapping("")
    public ApartmentComplexDTO postApartmentComplex(CreateUpdateApartmentComplexDTO newApartmentComplexDTO) {

        // Map DTO into entity
        ApartmentComplex apartmentComplex = mapper.createUpdateApartmentComplexDTOToApartmentComplex(newApartmentComplexDTO);
        apartmentComplex = repo.save(apartmentComplex);

        // Map saved entity into a response DTO
        ApartmentComplexDTO responseDTO = mapper.apartmentComplexToApartmentComplexDTO(apartmentComplex);

        return responseDTO;
    }

    // PatchMapping("/{id}")
    public ApartmentComplexDTO updateApartmentComplex(Long id, CreateUpdateApartmentComplexDTO apartmentComplexDetails) {

        // Check if drum corps exists
        repo.findById(id).orElseThrow(() -> new NotFoundException(id)); // test for not found in patch

        // Map dto to the already existing entity
        ApartmentComplex updatedApartmentComplex = mapper.createUpdateApartmentComplexDTOToApartmentComplex(apartmentComplexDetails);
        updatedApartmentComplex.setId(id); // to keep original id, avoid autogeneration
        repo.save(updatedApartmentComplex);

        // Map saved entity back into reponseDTO and return
        ApartmentComplexDTO responseDTO = mapper.apartmentComplexToApartmentComplexDTO(updatedApartmentComplex);

        return responseDTO;
    }

    // @DeleteMapping("/{id}")
    public ApartmentComplexDTO deleteApartmentComplexById(Long id) {

        // Map corps into DTO
        ApartmentComplexDTO apartmentComplexDTO = mapper.apartmentComplexToApartmentComplexDTO(repo.findById(id).
            orElseThrow(() -> new NotFoundException(id)));

        repo.deleteByApartmentComplexId(id);

        return apartmentComplexDTO;
    }
}
