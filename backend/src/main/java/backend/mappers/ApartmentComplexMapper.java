package backend.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import backend.DTOs.ApartmentComplexDTO;
import backend.DTOs.CreateUpdateApartmentComplexDTO;
import backend.models.Apartment;
import backend.models.ApartmentComplex;

@Mapper(componentModel = "spring")
public interface ApartmentComplexMapper {

    @Mapping(source = "id", target = "complexId")
    @Mapping(source = "apartments", target = "apartmentIds", qualifiedByName = "mapApartmentsToIds")
    ApartmentComplexDTO toDTO(ApartmentComplex entity);

    @Mapping(source = "apartmentIds", target = "apartments", ignore = true)
    ApartmentComplex toEntity(CreateUpdateApartmentComplexDTO dto);

    @Mapping(source = "apartmentIds", target = "apartments", ignore = true)
    void updateEntityFromDTO(CreateUpdateApartmentComplexDTO dto, @MappingTarget ApartmentComplex entity);

    @Named("mapApartmentsToIds")
    static List<Long> mapApartmentsToIds(List<Apartment> apartments) {
        if (apartments == null) {
            return new ArrayList<>();
        }
        return apartments.stream()
                         .map(Apartment::getId)
                         .collect(Collectors.toList());
    }
}
