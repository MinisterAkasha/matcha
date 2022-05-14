package ru.matcha.config.jwt;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.matcha.models.entities.User;
import io.jsonwebtoken.*;

import java.util.Date;

@Log4j2
@Component
public class JwtUtils {

    @Value("${token.jwtSecret}")
    private String jwtSecret;
    @Value("${token.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(User userPrincipal) {
        return generateTokenFromEmail(userPrincipal.getEmail());
    }

    public String generateTokenFromEmail(String email) {
        return Jwts.builder().setSubject(email).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
