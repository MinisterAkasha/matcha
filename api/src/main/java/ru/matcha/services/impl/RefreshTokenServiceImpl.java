package ru.matcha.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.matcha.config.jwt.JwtUtils;
import ru.matcha.exceptions.LogOutException;
import ru.matcha.exceptions.TokenRefreshException;
import ru.matcha.mappers.TokenMapper;
import ru.matcha.models.entities.RefreshToken;
import ru.matcha.models.responces.jwt.TokenResponse;
import ru.matcha.repositories.RefreshTokenRepository;
import ru.matcha.repositories.UserRepository;
import ru.matcha.services.RefreshTokenService;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private static final String REFRESH_TOKEN_NOT_FOUND = "Токен не найден. Пожалуйста, повторите вход";
    private static final String REFRESH_TOKEN_EXPIRED = "Срок действия токена обновления истек. Пожалуйста, повторите вход";
    private static final String LOGOUT_ERROR = "Ошибка при выходе";

    @Value("${token.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final TokenMapper tokenMapper;

    @Override
    public RefreshToken createRefreshToken(Long userId) {
        return refreshTokenRepository.save(
                tokenMapper.toEntity(userRepository.findById(userId).orElse(null),
                UUID.randomUUID().toString(),
                Instant.now().plusMillis(refreshTokenDurationMs))
        );
    }

    private RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(REFRESH_TOKEN_EXPIRED);
        }

        return token;
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
                .map(user -> tokenMapper.toRs(jwtUtils.generateTokenFromEmail(user.getEmail()), requestRefreshToken))
                .orElseThrow(() -> new TokenRefreshException(REFRESH_TOKEN_NOT_FOUND));
    }
}
