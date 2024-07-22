package backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import backend.DTOs.CreateUpdateTenantDTO;
import backend.DTOs.TenantDTO;
import backend.models.Tenant;

@Mapper(componentModel = "spring")
public interface TenantMapper {

    TenantDTO toDTO(Tenant entity);

    Tenant toEntity(TenantDTO dto);

    Tenant toEntity(CreateUpdateTenantDTO dto);

    CreateUpdateTenantDTO toCreateUpdateDTO(Tenant entity);
}
