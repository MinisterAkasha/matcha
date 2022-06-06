package ru.matcha.backend.exceptions.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import ru.matcha.backend.exceptions.JwtException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class JwtExceptionHandler implements ResponseErrorHandler {

    private final HandlerUtils handler;

    public void handle(HttpServletResponse response, JwtException jwtException) throws IOException {
        handler.handle(response, jwtException.getMessage(), HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatus status = response.getStatusCode();
        return status.is4xxClientError() || status.is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        throw new JwtException(response.getStatusCode().value(), toString(response.getBody()));
    }

    private String toString(InputStream inputStream) {
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
