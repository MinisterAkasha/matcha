package ru.matcha.access.api.services.impl;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.matcha.access.api.services.JwtService;

import java.util.Date;

import static ru.matcha.access.api.constraints.LogConstraint.*;

@Log4j2
@Service
public class JwtServiceImpl implements JwtService {

    private static final String TOKEN_PREFIX = "Bearer ";

    @Value("${token.jwtSecret}")
    private String jwtSecret;
    @Value("${token.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Override
    public String generate(String email) {
        return Jwts.builder().setSubject(email).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    @Override
    public String getEmail(String jwt) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
    }

    @Override
    public void validate(String jwt) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
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

    public String parse(String jwt) {
        if (!StringUtils.startsWithIgnoreCase(jwt, TOKEN_PREFIX)) {
            return null;
        }

        return jwt.substring(TOKEN_PREFIX.length());
    }
}
