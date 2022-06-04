package ru.matcha.backend.services;

import ru.matcha.backend.dto.UserDetailsImpl;
import ru.matcha.datasource.entities.RefreshToken;
import ru.matcha.api.models.responses.jwt.TokenResponse;

import javax.transaction.Transactional;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(UserDetailsImpl user);

    @Transactional
    void deleteByUserId(Long userId);

    TokenResponse updateToken(String requestRefreshToken);
}
