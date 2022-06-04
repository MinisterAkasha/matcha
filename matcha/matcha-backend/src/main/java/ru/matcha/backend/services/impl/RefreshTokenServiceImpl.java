package ru.matcha.backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.matcha.backend.dto.UserDetailsImpl;
import ru.matcha.backend.exceptions.LogOutException;
import ru.matcha.backend.exceptions.TokenRefreshException;
import ru.matcha.backend.mappers.TokenMapper;
import ru.matcha.api.models.responses.jwt.TokenResponse;
import ru.matcha.backend.services.RestService;
import ru.matcha.datasource.repositories.RefreshTokenRepository;
import ru.matcha.datasource.repositories.UserRepository;
import ru.matcha.backend.services.RefreshTokenService;
import ru.matcha.datasource.entities.RefreshToken;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

import static ru.matcha.backend.constraints.LogConstraint.*;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${token.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final TokenMapper tokenMapper;
    private final RestService restService;

    @Override
    public RefreshToken createRefreshToken(UserDetailsImpl user) {
        return refreshTokenRepository.save(
                tokenMapper.toEntity(user,
                UUID.randomUUID().toString(),
                Instant.now().plusMillis(refreshTokenDurationMs))
        );
    }

    @Override
    @Transactional
    public void deleteByUserId(Long userId) {
        refreshTokenRepository.deleteByUser(userRepository.findById(userId).orElseThrow(() -> new LogOutException(LOGOUT_ERROR)));
    }

    @Override
    public TokenResponse updateToken(String requestRefreshToken) {
        return refreshTokenRepository.findByToken(requestRefreshToken)
                .map(this::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> tokenMapper.toRs(true, restService.postAccess("/generateToken", user.getEmail(), String.class).getBody(), requestRefreshToken))
                .orElseThrow(() -> new TokenRefreshException(REFRESH_TOKEN_NOT_FOUND));
    }

    private RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(REFRESH_TOKEN_EXPIRED);
        }

        return token;
    }
}
