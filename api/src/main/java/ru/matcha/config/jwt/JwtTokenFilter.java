package ru.matcha.config.jwt;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.matcha.config.jwt.JwtLogConstraint.SET_AUTHENTICATION_ERROR;

@Log4j2
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtConverter jwtConverter;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    private final AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        UsernamePasswordAuthenticationToken authRq = jwtConverter.convert(request);
        try {
            if (authRq == null) {
                filterChain.doFilter(request, response);
                return;
            }
            if (authenticationIsRequired()) {
                Authentication authResult = authenticationManager.authenticate(authRq);
                SecurityContextHolder.getContext().setAuthentication(authResult);
            }
        } catch (AuthenticationException e) {
            log.error(SET_AUTHENTICATION_ERROR, e.getMessage());
            SecurityContextHolder.clearContext();
            authenticationEntryPoint.commence(request, response, e);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean authenticationIsRequired() {
        Authentication existsAuth = SecurityContextHolder.getContext().getAuthentication();
        return existsAuth == null || !existsAuth.isAuthenticated();
    }
}
