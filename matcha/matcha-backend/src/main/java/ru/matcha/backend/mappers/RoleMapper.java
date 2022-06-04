package ru.matcha.backend.mappers;

import org.mapstruct.Mapper;
import ru.matcha.backend.dto.RoleImpl;
import ru.matcha.datasource.entities.Role;

@Mapper
public interface RoleMapper {

    default String toString(RoleImpl role) {
        return role != null ? role.getAuthority() : null;
    }

    RoleImpl toDto(Role roleEntity);

    Role toEntity(RoleImpl role);
}
