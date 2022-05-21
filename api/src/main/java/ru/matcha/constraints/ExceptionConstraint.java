package ru.matcha.constraints;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionConstraint {

    public static final String EMAIL_ALREADY_EXISTS = "Пользователь с email %s уже зарегистрирован.";
    public static final String LOGOUT_ERROR = "Ошибка при выходе";
    public static final String PASSWORD_ERROR = "Неверный пароль";
    public static final String USER_NOT_FOUND = "Пользователь c email %s не найден";
}
