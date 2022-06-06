package ru.matcha.backend.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;
import ru.matcha.backend.exceptions.EmailAlreadyExistsException;
import ru.matcha.backend.exceptions.JwtException;
import ru.matcha.backend.exceptions.TokenRefreshException;
import ru.matcha.api.models.responses.exeption.ErrorResponse;
import ru.matcha.api.models.responses.exeption.SuccessResponse;

import javax.security.auth.login.LoginException;
import javax.validation.ValidationException;

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

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerValidationException(ValidationException ex) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
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
                .errorCode(ex.getCode())
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

    @ExceptionHandler(RestClientResponseException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handlerRestClientException(RestClientResponseException ex) {
        return ErrorResponse.builder()
                .message(ex.getResponseBodyAsString())
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
