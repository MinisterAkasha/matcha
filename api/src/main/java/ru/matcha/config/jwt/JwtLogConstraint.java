package ru.matcha.config.jwt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtLogConstraint {
    public static final String INVALID_JWT_SIGNATURE = "Invalid JWT signature: {}";
    public static final String INVALID_JWT_TOKEN = "Invalid JWT token: {}";
    public static final String JWT_TOKEN_EXPIRED = "JWT token is expired: {}";
    public static final String JWT_TOKEN_UNSUPPORTED = "JWT token is unsupported: {}";
    public static final String JWT_CLAIMS = "JWT claims string is empty: {}";
    public static final String SET_AUTHENTICATION_ERROR = "Cannot set user authentication: {}";
    public static final String ACCOUNT_DISABLED = "Пользователь заблокирован";
    public static final String SUCCESS_AUTHENTICATE = "Пользователь {} авторизировался в системе";
    public static final String USER_NOT_FOUND = "Пользователь %s не найден";
}
