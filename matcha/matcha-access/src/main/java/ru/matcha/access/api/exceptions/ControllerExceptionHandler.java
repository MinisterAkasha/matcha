package ru.matcha.access.api.exceptions;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.matcha.access.api.models.responses.exeption.ErrorResponse;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handlerJwtException(JwtException ex) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .errorCode(HttpStatus.UNAUTHORIZED.value())
                .build();
    }

}
