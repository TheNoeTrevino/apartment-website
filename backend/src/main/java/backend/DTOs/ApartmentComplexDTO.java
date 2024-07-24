package backend.DTOs;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApartmentComplexDTO {
    private long complexId;
    private String complexName;
    private String complexLocation;
    private Integer numOfBuildings;
    private Integer numOfUnits;
    private LocalDate dateBuilt;
    private String managerName;
    private String managerEmail;
    private String managerPhone;
    private List<Long> apartmentIds;
}
