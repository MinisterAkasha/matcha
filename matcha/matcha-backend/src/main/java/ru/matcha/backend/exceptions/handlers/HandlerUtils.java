package ru.matcha.backend.exceptions.handlers;

import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.matcha.api.models.responses.exeption.ErrorResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class HandlerUtils {

    private final ObjectWriter objectWriter;

    public void handle(HttpServletResponse response, String msg, int code) throws IOException {
        response.setStatus(code);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().print(
                objectWriter.writeValueAsString(
                        ErrorResponse.builder().message(msg).errorCode(code).build()
                )
        );
    }
}
