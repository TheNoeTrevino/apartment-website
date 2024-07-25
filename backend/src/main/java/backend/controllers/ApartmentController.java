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

import backend.DTOs.ApartmentDTO;
import backend.DTOs.CreateUpdateApartmentDTO;
import backend.services.ApartmentService;
import backend.validations.PageableConstraint;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/apartments")
public class ApartmentController {

    private static final Logger logger = LoggerFactory.getLogger(ApartmentController.class);

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("/{id}")
    public ApartmentDTO getApartmentById(@PathVariable Long id) {
        return apartmentService.getApartmentById(id);
    }

    @GetMapping("")
    public ResponseEntity<Page<ApartmentDTO>> getAllApartments(
            @PageableDefault(page = 0, size = 10, sort = {"apartmentName"})
            @Valid @PageableConstraint(message = "Invalid page size", maxPerPage = 50) Pageable pageable,
            @RequestParam(required = false) String apartmentName,
            @RequestParam(required = false) String apartmentLocation,
            @RequestParam(required = false) Integer numOfRooms,
            @RequestParam(required = false) Integer squareFootage,
            @RequestParam(required = false) LocalDate dateBuilt,
            @RequestParam(required = false) String currentTenantName,
            @RequestParam(required = false) String currentTenantEmail,
            @RequestParam(required = false) String currentTenantPhone) {

        return ResponseEntity.ok(apartmentService.getAllApartmentsPageable(pageable,
        apartmentName, apartmentLocation, numOfRooms, squareFootage, dateBuilt, currentTenantName, currentTenantEmail, currentTenantPhone));
    }

    @PostMapping("")
    public ResponseEntity<ApartmentDTO> createApartment(@Valid @RequestBody CreateUpdateApartmentDTO newApartmentDTO) {
        return ResponseEntity.ok(apartmentService.postApartment(newApartmentDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApartmentDTO> updateApartment(@Valid @PathVariable Long id,
            @RequestBody CreateUpdateApartmentDTO apartmentDetails) {
                logger.info("Updating Apartment with ID: {}, Details: {}", id, apartmentDetails);
        return ResponseEntity.ok(apartmentService.updateApartment(id, apartmentDetails));
    }

    @DeleteMapping("/{id}")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApartmentDTO> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(apartmentService.deleteApartmentById(id));
    }
}

