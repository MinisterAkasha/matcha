package ru.matcha.backend.exceptions;

public class JwtException extends RuntimeException {

    public JwtException(String msg) {
        super(msg);
    }
}
