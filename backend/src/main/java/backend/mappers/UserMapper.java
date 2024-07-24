
package backend.mappers;

import org.mapstruct.*;

import backend.DTOs.CreateUpdateUserDTO;
import backend.DTOs.UserDTO;
import backend.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User entity);

    @Mapping(target = "id", ignore = true)
    User toEntity(CreateUpdateUserDTO dto);

    @Mapping(target = "id", ignore = true) // Ensure the id is not updated unintentionally
    void updateEntityFromDTO(CreateUpdateUserDTO dto, @MappingTarget User entity);
}
