package ru.matcha.backend.config.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;
import ru.matcha.backend.services.RestService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtConverter implements AuthenticationConverter {

    private final RestService restService;

    @Override
    public UsernamePasswordAuthenticationToken convert(HttpServletRequest request) {
        String jwt = restService.postAccess("/parse", request.getHeader(HttpHeaders.AUTHORIZATION), String.class).getBody();
        if (jwt == null) {
            return null;
        }

        restService.postAccess("/validate", jwt, Void.class);
        return new UsernamePasswordAuthenticationToken(restService.getAccess("/get/email?jwt={jwt}", Map.of("jwt", jwt), String.class).getBody(), "");
    }

}
