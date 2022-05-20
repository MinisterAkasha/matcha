package ru.matcha.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.matcha.models.entities.Role;
import ru.matcha.models.entities.User;
import ru.matcha.models.requests.LoginRequest;
import ru.matcha.models.requests.SignupRequest;
import ru.matcha.models.responces.CurrentUserResponse;

import java.util.Set;

@Mapper(uses = {RoleMapper.class, GenderMapper.class, OrientationMapper.class})
public interface UserMapper {

    @Mapping(target = "roles", source = "user.authorities")
    CurrentUserResponse toUserRs(User user);

    @Mapping(target = "password", source = "password")
    User toEntity(SignupRequest request, String password, boolean enabled, Set<Role> authorities);

    LoginRequest toLoginRq(SignupRequest signupRequest);
}
