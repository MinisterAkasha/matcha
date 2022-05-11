package ru.matcha.mappers;

import org.mapstruct.Mapper;
import ru.matcha.models.entities.RefreshToken;
import ru.matcha.models.entities.User;
import ru.matcha.models.responces.jwt.TokenRefreshResponse;

import java.time.Instant;

@Mapper
public interface TokenMapper {

    TokenRefreshResponse toRs(String accessToken, String refreshToken);

    RefreshToken toEntity(User user, String token, Instant expiryDate);
}
