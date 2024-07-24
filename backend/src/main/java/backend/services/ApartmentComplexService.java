package backend.services;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final ApartmentComplexRepository repo;
    private final ApartmentComplexMapper mapper;

    public ApartmentComplexDTO getApartmentComplexById(Long id) {
        return mapper.toDTO(repo.findById(id)
            .orElseThrow(() -> new NotFoundException(id)));
    }

    public Page<ApartmentComplexDTO> getAllApartmentComplexPageable(Pageable pageable, String complexName, String complexLocation,
                                                                    Integer numOfBuildings, Integer numOfUnits, LocalDate dateBuilt,
                                                                    String managerName, String managerEmail, String managerPhone) {

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
        return apartmentComplexPage.map(mapper::toDTO);
    }

    public ApartmentComplexDTO postApartmentComplex(CreateUpdateApartmentComplexDTO newApartmentComplexDTO) {
        ApartmentComplex apartmentComplex = mapper.toEntity(newApartmentComplexDTO);
        apartmentComplex = repo.save(apartmentComplex);
        return mapper.toDTO(apartmentComplex);
    }

    @Transactional
    public ApartmentComplexDTO updateApartmentComplex(Long id, CreateUpdateApartmentComplexDTO apartmentComplexDetails) {
        ApartmentComplex existingApartmentComplex = repo.findById(id)
            .orElseThrow(() -> new NotFoundException(id));

        mapper.updateEntityFromDTO(apartmentComplexDetails, existingApartmentComplex);
        repo.save(existingApartmentComplex);

        return mapper.toDTO(existingApartmentComplex);
    }

    @Transactional
    public ApartmentComplexDTO deleteApartmentComplexById(Long id) {
        ApartmentComplex apartmentComplex = repo.findById(id)
            .orElseThrow(() -> new NotFoundException(id));

        ApartmentComplexDTO apartmentComplexDTO = mapper.toDTO(apartmentComplex);
        repo.deleteById(id);
        return apartmentComplexDTO;
    }
}

