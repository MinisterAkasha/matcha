package ru.matcha.exceptions.handlers;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.matcha.exceptions.EmailAlreadyExistsException;
import ru.matcha.exceptions.TokenRefreshException;
import ru.matcha.models.responces.exeption.ErrorResponse;
import ru.matcha.models.responces.exeption.SuccessResponse;

import javax.security.auth.login.LoginException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerBindException(BindException ex) {
        return ErrorResponse.builder()
                .message(ex.getBindingResult().getFieldErrors())
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(TokenRefreshException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handlerTokenRefreshException(TokenRefreshException ex) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .errorCode(HttpStatus.FORBIDDEN.value())
                .build();
    }

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handlerInvalidJwtTokenException(JwtException ex) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .errorCode(HttpStatus.FORBIDDEN.value())
                .build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handlerUsernameNotFoundException(UsernameNotFoundException ex) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .errorCode(HttpStatus.UNAUTHORIZED.value())
                .build();
    }

    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse handlerLoginException(LoginException ex) {
        return SuccessResponse.builder()
                .message(ex.getMessage())
                .success(false)
                .build();
    }
}
