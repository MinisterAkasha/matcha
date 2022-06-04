package ru.matcha.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import ru.matcha.backend.exceptions.handlers.JwtExceptionHandler;

@Configuration
@RequiredArgsConstructor
public class RestConfig {

    private final JwtExceptionHandler jwtExceptionHandler;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .errorHandler(jwtExceptionHandler).build();
    }

}
