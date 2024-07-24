package backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import backend.DTOs.CreateUpdateTenantDTO;
import backend.DTOs.TenantDTO;
import backend.models.Tenant;


@Mapper(componentModel = "spring")
public interface TenantMapper {

    TenantMapper INSTANCE = Mappers.getMapper(TenantMapper.class);

    @Mapping(source = "apartment.id", target = "apartmentId")
    @Mapping(source = "id", target = "tenantId")
    TenantDTO toDTO(Tenant entity);

    @Mapping(source = "apartmentId", target = "apartment.id")
    @Mapping(source = "tenantId", target = "id")
    @Mapping(source = "rentDue", target = "rentDue")
    @Mapping(source = "tenantStatus", target = "tenantStatus")
    Tenant toEntity(TenantDTO dto);

    @Mapping(source = "apartmentId", target = "apartment.id")
    Tenant toEntity(CreateUpdateTenantDTO dto);

    @Mapping(source = "apartment.id", target = "apartmentId")
    CreateUpdateTenantDTO toCreateUpdateDTO(Tenant entity);
}
