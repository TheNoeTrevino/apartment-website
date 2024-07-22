package backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import backend.DTOs.ApartmentComplexDTO;
import backend.DTOs.CreateUpdateApartmentComplexDTO;
import backend.models.ApartmentComplex;

@Mapper(componentModel = "spring")
public interface ApartmentComplexMapper {

    ApartmentComplexDTO apartmentComplexToApartmentComplexDTO(ApartmentComplex apartmentComplex);

    ApartmentComplex createUpdateApartmentComplexDTOToApartmentComplex(CreateUpdateApartmentComplexDTO createUpdateApartmentComplexDTO);

    CreateUpdateApartmentComplexDTO apartmentComplexToCreateUpdateDrumCorpApartmentComplexDTO(ApartmentComplex apartmentComplex);
}
