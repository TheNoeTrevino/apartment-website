package backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import backend.DTOs.ApartmentDTO;
import backend.DTOs.CreateUpdateApartmentDTO;
import backend.models.Apartment;

@Mapper(componentModel = "spring")
public interface ApartmentMapper {

    ApartmentDTO toDTO(Apartment entity);

    Apartment toEntity(ApartmentDTO dto);

    Apartment toEntity(CreateUpdateApartmentDTO dto);

    CreateUpdateApartmentDTO toCreateUpdateDTO(Apartment entity);
}

