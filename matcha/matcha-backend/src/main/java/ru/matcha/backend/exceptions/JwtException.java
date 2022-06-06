package ru.matcha.backend.exceptions;

import lombok.Getter;

@Getter
public class JwtException extends RuntimeException {

    private final int code;

    public JwtException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}
