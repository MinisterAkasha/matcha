package ru.matcha.constraints;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogConstraint {
    public static final String INVALID_JWT_SIGNATURE = "Invalid JWT signature: {}";
    public static final String INVALID_JWT_TOKEN = "Invalid JWT token: {}";
    public static final String JWT_TOKEN_EXPIRED = "JWT token is expired: {}";
    public static final String JWT_TOKEN_UNSUPPORTED = "JWT token is unsupported: {}";
    public static final String JWT_CLAIMS = "JWT claims string is empty: {}";
    public static final String REFRESH_TOKEN_NOT_FOUND = "Токен не найден. Пожалуйста, повторите вход";
    public static final String REFRESH_TOKEN_EXPIRED = "Срок действия токена обновления истек. Пожалуйста, повторите вход";
    public static final String LOGOUT_ERROR = "Ошибка при выходе";
    public static final String SUCCESS_AUTHENTICATE = "Пользователь {} авторизировался в системе";
    public static final String USER_NOT_FOUND = "Пользователь c email {} не найден";
}
