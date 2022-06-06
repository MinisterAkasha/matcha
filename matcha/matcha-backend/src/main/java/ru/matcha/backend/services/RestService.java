package ru.matcha.backend.services;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface RestService {

    <T, P> ResponseEntity<P> postAccess(String uri, T request, Class<P> responseType);

    <T> ResponseEntity<T> getAccess(String uri, Map<String, ?> request, Class<T> responseType);
}
