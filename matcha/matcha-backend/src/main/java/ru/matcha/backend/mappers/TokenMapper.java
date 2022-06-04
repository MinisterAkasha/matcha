package ru.matcha.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.matcha.backend.dto.UserDetailsImpl;
import ru.matcha.datasource.entities.RefreshToken;
import ru.matcha.api.models.responses.jwt.TokenResponse;

import java.time.Instant;

@Mapper(uses = UserMapper.class)
public interface TokenMapper {

    TokenResponse toRs(Boolean success, String accessToken, String refreshToken);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    RefreshToken toEntity(UserDetailsImpl user, String token, Instant expiryDate);

}
