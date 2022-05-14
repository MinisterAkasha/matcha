package ru.matcha.exceptions;

public class TokenRefreshException extends RuntimeException {

    public TokenRefreshException(String msg) {
        super(msg);
    }
}
