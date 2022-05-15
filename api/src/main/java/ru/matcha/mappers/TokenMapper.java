package ru.matcha.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.matcha.models.entities.RefreshToken;
import ru.matcha.models.entities.User;
import ru.matcha.models.responces.jwt.TokenResponse;

import java.time.Instant;

@Mapper
public interface TokenMapper {

    TokenResponse toRs(String accessToken, String refreshToken);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    RefreshToken toEntity(User user, String token, Instant expiryDate);
}
