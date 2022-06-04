package ru.matcha.backend.config.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.matcha.backend.services.RestService;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtConverter implements AuthenticationConverter {

    private static final String TOKEN_PREFIX = "Bearer ";

    private final RestService restService;

    @Override
    public UsernamePasswordAuthenticationToken convert(HttpServletRequest request) {
        String jwt = parseJwt(request);
        if (jwt == null) {
            return null;
        }
        restService.postAccess("/validateJwt", jwt, Void.class);
        return new UsernamePasswordAuthenticationToken(restService.postAccess("/getEmail", jwt, String.class).getBody(), "");
    }

    private String parseJwt(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.startsWithIgnoreCase(token, TOKEN_PREFIX)) {
            return null;
        }

        return token.substring(TOKEN_PREFIX.length());
    }

}
