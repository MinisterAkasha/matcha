package ru.matcha.exceptions.handlers;

import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ru.matcha.models.responces.ResponseMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationExceptionHandler implements AuthenticationEntryPoint {

    private final ObjectWriter objectWriter;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().print(
                objectWriter.writeValueAsString(
                        ResponseMessage.builder()
                                .message(authException.getMessage())
                                .errorCode(HttpServletResponse.SC_UNAUTHORIZED)
                                .build()
                )
        );
    }
}
