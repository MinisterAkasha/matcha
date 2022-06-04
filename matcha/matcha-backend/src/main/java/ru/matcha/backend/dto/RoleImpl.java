package ru.matcha.backend.dto;

import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import ru.matcha.datasource.entities.enums.RoleDto;

@Value
public class RoleImpl implements GrantedAuthority {

    Long id;
    RoleDto name;

    @Override
    public String getAuthority() {
        return name.name();
    }
}
