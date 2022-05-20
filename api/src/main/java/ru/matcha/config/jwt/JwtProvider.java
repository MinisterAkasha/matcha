package ru.matcha.config.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.matcha.config.UserChecker;

import static ru.matcha.config.jwt.JwtLogConstraint.*;

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final UserChecker userChecker;

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        UserDetails user = userDetailsService.loadUserByUsername(email);
        if (user == null) {
            log.info(String.format(USER_NOT_FOUND, email));
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND, email));
        }
        try {
            userChecker.check(user);
            log.info(SUCCESS_AUTHENTICATE, email);
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        } catch (AuthenticationException ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
