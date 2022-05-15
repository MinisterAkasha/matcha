package ru.matcha.mappers;

import org.mapstruct.Mapper;
import ru.matcha.models.entities.Role;

@Mapper
public interface RoleMapper {

    default String toString(Role role) {
        return role != null ? role.getAuthority() : null;
    }
}
