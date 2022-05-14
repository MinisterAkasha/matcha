package ru.matcha.config.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static ru.matcha.config.jwt.JwtLogConstraint.*;

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtConverter implements AuthenticationConverter {

    private static final String TOKEN_PREFIX = "Bearer ";

    @Value("${token.jwtSecret}")
    private String jwtSecret;

    @Override
    public UsernamePasswordAuthenticationToken convert(HttpServletRequest request) {
        String jwt = parseJwt(request);
        if (jwt == null) {
            return null;
        }
        validateJwtToken(jwt);
        return new UsernamePasswordAuthenticationToken(getEmailFromJwtToken(jwt), "");
    }

    private String parseJwt(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.startsWithIgnoreCase(token, TOKEN_PREFIX)) {
            return null;
        }

        return token.substring(TOKEN_PREFIX.length());
    }

    private String getEmailFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    private void validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
        } catch (SignatureException e) {
            log.error(INVALID_JWT_SIGNATURE, e.getMessage());
            throw e;
        } catch (MalformedJwtException e) {
            log.error(INVALID_JWT_TOKEN, e.getMessage());
            throw e;
        } catch (ExpiredJwtException e) {
            log.error(JWT_TOKEN_EXPIRED, e.getMessage());
            throw e;
        } catch (UnsupportedJwtException e) {
            log.error(JWT_TOKEN_UNSUPPORTED, e.getMessage());
            throw e;
        } catch (IllegalArgumentException e) {
            log.error(JWT_CLAIMS, e.getMessage());
            throw e;
        }
    }
}
