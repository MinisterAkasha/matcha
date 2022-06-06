package ru.matcha.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.matcha.backend.config.jwt.JwtConverter;
import ru.matcha.backend.config.jwt.JwtTokenFilter;
import ru.matcha.backend.exceptions.handlers.JwtExceptionHandler;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private static final String[] WHITE_LIST = {
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/users/get/all",
            "/auth/**",
            "/test/all"
    };

    private final AuthenticationConfiguration authenticationConfiguration;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    private final JwtExceptionHandler jwtExceptionHandler;
    private final JwtConverter jwtConverter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                    .authorizeRequests()
                        .antMatchers("/users/get/current").authenticated()
                        .antMatchers(WHITE_LIST).permitAll()
                        .antMatchers("/test/user").hasAuthority("USER")
                        .antMatchers("/test/admin").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                .and()
                    .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler)
                    .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                    .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
//                    .authenticationManager(authenticationManager())
                    .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    private JwtTokenFilter jwtTokenFilter() throws Exception {
        return new JwtTokenFilter(jwtConverter, authenticationEntryPoint, jwtExceptionHandler, authenticationManager());
    }
}
