package backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import backend.DTOs.ApartmentDTO;
import backend.DTOs.CreateUpdateApartmentDTO;
import backend.models.Apartment;

@Mapper(componentModel = "spring")
public interface ApartmentMapper {

    ApartmentMapper INSTANCE = Mappers.getMapper(ApartmentMapper.class);

    @Mapping(source = "apartmentComplex.id", target = "complexId")
    ApartmentDTO toDTO(Apartment entity);

    @Mapping(source = "complexId", target = "apartmentComplex.id")
    @Mapping(target = "lease", ignore = true) // Avoid circular reference for lease
    Apartment toEntity(ApartmentDTO dto);

    @Mapping(source = "complexId", target = "apartmentComplex.id")
    @Mapping(target = "id", ignore = true) // ID will be generated automatically
    @Mapping(target = "lease", ignore = true) // Lease will be set separately
    Apartment toEntity(CreateUpdateApartmentDTO dto);

    @Mapping(source = "apartmentComplex.id", target = "complexId")
    CreateUpdateApartmentDTO toCreateUpdateDTO(Apartment entity);
}
