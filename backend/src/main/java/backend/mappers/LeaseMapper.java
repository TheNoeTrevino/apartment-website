package backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import backend.DTOs.CreateUpdateLeaseDTO;
import backend.DTOs.LeaseDTO;
import backend.models.Lease;

@Mapper(componentModel = "spring")
public interface LeaseMapper {

    @Mapping(source = "id", target = "leaseId")
    LeaseDTO toDTO(Lease entity);

    @Mapping(source = "leaseId", target = "id")
    Lease toEntity(LeaseDTO dto);

    @Mapping(target = "id", ignore = true)
    Lease toEntity(CreateUpdateLeaseDTO dto);

    CreateUpdateLeaseDTO toCreateUpdateDTO(Lease entity);
}
