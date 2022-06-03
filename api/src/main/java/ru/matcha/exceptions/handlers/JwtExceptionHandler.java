package ru.matcha.exceptions.handlers;

import com.fasterxml.jackson.databind.ObjectWriter;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.matcha.models.responces.exeption.ErrorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class JwtExceptionHandler {

    private final ObjectWriter objectWriter;

    public void handle(HttpServletRequest request, HttpServletResponse response, JwtException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().print(
                objectWriter.writeValueAsString(
                        ErrorResponse.builder()
                                .message(authException.getMessage())
                                .errorCode(HttpServletResponse.SC_UNAUTHORIZED)
                                .build()
                )
        );
    }
}
