package backend.DTOs;

import java.time.LocalDate;

import backend.models.ApartmentComplex;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApartmentDTO {
    private long complexId;
    private String apartmentName;
    private String apartmentLocation;
    private Integer numOfRooms;
    private Integer squareFootage;
    private LocalDate dateBuilt;
    private String currentTenantName;
    private String currentTenantEmail;
    private String currentTenantPhone;
    private ApartmentComplex apartmentComplex;
}
