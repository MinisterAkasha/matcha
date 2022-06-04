package ru.matcha.backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.matcha.backend.services.RestService;

@Service
@RequiredArgsConstructor
public class RestServiceImpl implements RestService {

    @Value("${matcha.access.url}")
    private String urlAccessService;

    private final RestTemplate restTemplate;

    @Override
    public <T, P> ResponseEntity<P> postAccess(String uri, T request, Class<P> responseType) {
        return restTemplate.postForEntity(urlAccessService + uri, request, responseType);
    }
}
