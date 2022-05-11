package ru.matcha.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.matcha.models.entities.Role;
import ru.matcha.models.entities.User;
import ru.matcha.models.requests.SignupRequest;
import ru.matcha.models.responces.UserJwtResponse;
import ru.matcha.models.responces.UserResponse;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {

    @Mapping(target = "roles", source = "user.authorities", qualifiedByName = "rolesToString")
    UserResponse toUserRs(User user);

    @Mapping(target = "roles", source = "user.authorities", qualifiedByName = "rolesToString")
    UserJwtResponse toUserJwtRs(User user, String accessToken, String refreshToken);

    @Mapping(target = "username", source = "request.username")
    @Mapping(target = "email", source = "request.email")
    User toEntity(SignupRequest request, String password, boolean enabled, Set<Role> authorities);

    @Named("rolesToString")
    default Set<String> rolesToString(Set<Role> roles) {
        return roles.stream().map(Role::getAuthority).collect(Collectors.toSet());
    }
}
