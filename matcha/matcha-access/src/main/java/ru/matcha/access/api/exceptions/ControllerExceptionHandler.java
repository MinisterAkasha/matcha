package ru.matcha.access.api.exceptions;

import io.jsonwebtoken.JwtException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;

@Log4j2
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handlerJwtException(JwtException ex) {
        log.warn(ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(ServletException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handlerServletException(ServletException ex) {
        log.warn(ex.getMessage());
        return ex.getMessage();
    }
}
