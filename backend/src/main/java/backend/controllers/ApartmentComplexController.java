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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.DTOs.*;
import backend.validations.*;
import backend.services.ApartmentComplexService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("apartment-complex")
    public class ApartmentComplexController {

    private static final Logger logger = LoggerFactory.getLogger(ApartmentComplexController.class);

    @Autowired
    private ApartmentComplexService apartmentComplexService;

    @GetMapping("/{id}")
    public ApartmentComplexDTO getApartmentComplexById(@PathVariable Long id) {
        return apartmentComplexService.getApartmentComplexById(id);
    }

    @GetMapping("")
    public ResponseEntity<Page<ApartmentComplexDTO>> getAllApartmentComplex(
        @PageableDefault(page = 0, size = 2, sort = {"complexName"}) Pageable pageable,
        @Valid @PageableConstraint(message = "Invalid page size", maxPerPage = 50)
            @RequestParam(required = false) String complexName,
            @RequestParam(required = false) String complexLocation,
            @RequestParam(required = false) Integer numOfBuildings,
            @RequestParam(required = false) Integer numOfUnits,
            @RequestParam(required = false) LocalDate dateBuilt,
            @RequestParam(required = false) String managerName,
            @RequestParam(required = false) String managerEmail,
            @RequestParam(required = false) String managerPhone) {

        return ResponseEntity.ok(apartmentComplexService.getAllApartmentComplexPageable(pageable,
        complexName, complexLocation, numOfBuildings, numOfUnits, dateBuilt, managerName, managerEmail, managerPhone));
    }

    @PostMapping("")
    public ResponseEntity<ApartmentComplexDTO> postApartmentComplex(@Valid @RequestBody CreateUpdateApartmentComplexDTO newApartmentComplexDTO) {
        return ResponseEntity.ok(apartmentComplexService.postApartmentComplex(newApartmentComplexDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApartmentComplexDTO> updateApartmentComplex(@Valid @PathVariable Long id,
            @RequestBody CreateUpdateApartmentComplexDTO apartmentComplexDetails) {
                logger.info("Updating Apartment Commplex with ID: {}, Details: {}", id, apartmentComplexDetails);
        return ResponseEntity.ok(apartmentComplexService.updateApartmentComplex(id, apartmentComplexDetails));
    }

    @DeleteMapping("/{id}")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApartmentComplexDTO> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(apartmentComplexService.deleteApartmentComplexById(id));
    }
}
