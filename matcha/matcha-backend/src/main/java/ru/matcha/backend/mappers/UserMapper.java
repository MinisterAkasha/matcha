package ru.matcha.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Slice;
import ru.matcha.api.models.responses.UserList;
import ru.matcha.api.models.responses.UserResponse;
import ru.matcha.backend.dto.RoleImpl;
import ru.matcha.backend.dto.UserDetailsImpl;
import ru.matcha.datasource.entities.User;
import ru.matcha.api.models.requests.LoginRequest;
import ru.matcha.api.models.requests.SignupRequest;
import ru.matcha.api.models.responses.CurrentUserResponse;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(uses = {RoleMapper.class, GenderMapper.class, OrientationMapper.class})
public interface UserMapper {

    @Mapping(target = "roles", source = "user.authorities")
    CurrentUserResponse toUserRs(UserDetailsImpl user);

    @Mapping(target = "password", source = "password")
    User toEntity(SignupRequest request, String password, boolean enabled, Set<RoleImpl> authorities);

    User toEntity(UserDetailsImpl user);

    LoginRequest toLoginRq(SignupRequest signupRequest);

    UserDetailsImpl toDto(User user);

    UserResponse toDtoRs(User user);

    default UserList toDto(Slice<User> users) {

        return UserList.builder()
                .hasNext(users.hasNext())
                .content(users.getContent().stream().map(this::toDtoRs)
                        .collect(Collectors.toList()))
                .build();
    }
}
