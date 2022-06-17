package ru.matcha.backend.constraints;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogConstraint {
    public static final String REFRESH_TOKEN_NOT_FOUND = "Токен не найден. Пожалуйста, повторите вход";
    public static final String REFRESH_TOKEN_EXPIRED = "Срок действия токена обновления истек. Пожалуйста, повторите вход";
    public static final String LOGOUT_ERROR = "Ошибка при выходе";
    public static final String SUCCESS_AUTHENTICATE = "Пользователь {} авторизировался в системе";
    public static final String PASSWORD_ERROR = "Введен неверный пароль для пользователя {]";
    public static final String SUCCESS_LOGOUT = "Пользователь {} вышел из системы";
}
