package backend.DTOs;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApartmentDTO {
    private long id;
    private String apartmentName;
    private String apartmentLocation;
    private Integer numOfRooms;
    private Integer squareFootage;
    private LocalDate dateBuilt;
    private String currentTenantName;
    private String currentTenantEmail;
    private String currentTenantPhone;
    private long complexId;
}
