package ru.matcha.backend.services;

import org.springframework.http.ResponseEntity;

public interface RestService {

    <T, P> ResponseEntity<P> postAccess(String uri, T request, Class<P> responseType);
}
