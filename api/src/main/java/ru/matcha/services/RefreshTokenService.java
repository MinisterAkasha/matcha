package ru.matcha.services;

import ru.matcha.models.entities.RefreshToken;
import ru.matcha.models.responces.jwt.TokenResponse;

import javax.transaction.Transactional;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(Long userId);

    @Transactional
    void deleteByUserId(Long userId);

    TokenResponse updateToken(String requestRefreshToken);
}
