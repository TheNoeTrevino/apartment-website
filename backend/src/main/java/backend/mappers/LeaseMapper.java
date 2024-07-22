package backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import backend.DTOs.CreateUpdateLeaseDTO;
import backend.DTOs.LeaseDTO;
import backend.models.Lease;

@Mapper(componentModel = "spring")
public interface LeaseMapper {

    LeaseDTO toDTO(Lease entity);

    Lease toEntity(LeaseDTO dto);

    Lease toEntity(CreateUpdateLeaseDTO dto);

    CreateUpdateLeaseDTO toCreateUpdateDTO(Lease entity);
}
