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
import ru.matcha.models.responces.ResponseMessage;

import javax.security.auth.login.LoginException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage handlerBindException(BindException ex) {
        return ResponseMessage.builder()
                .message(ex.getBindingResult().getFieldErrors())
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage handlerEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return ResponseMessage.builder()
                .message(ex.getMessage())
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(TokenRefreshException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseMessage handlerTokenRefreshException(TokenRefreshException ex) {
        return ResponseMessage.builder()
                .message(ex.getMessage())
                .errorCode(HttpStatus.FORBIDDEN.value())
                .build();
    }

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseMessage handlerInvalidJwtTokenException(JwtException ex) {
        return ResponseMessage.builder()
                .message(ex.getMessage())
                .errorCode(HttpStatus.FORBIDDEN.value())
                .build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseMessage handlerUsernameNotFoundException(UsernameNotFoundException ex) {
        return ResponseMessage.builder()
                .message(ex.getMessage())
                .errorCode(HttpStatus.UNAUTHORIZED.value())
                .build();
    }

    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseMessage handlerLoginException(LoginException ex) {
        return ResponseMessage.builder()
                .message(ex.getMessage())
                .errorCode(HttpStatus.UNAUTHORIZED.value())
                .build();
    }
}
