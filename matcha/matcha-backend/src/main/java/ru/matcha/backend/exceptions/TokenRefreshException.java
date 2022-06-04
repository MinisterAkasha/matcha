package ru.matcha.backend.exceptions;

public class TokenRefreshException extends RuntimeException {

    public TokenRefreshException(String msg) {
        super(msg);
    }
}
