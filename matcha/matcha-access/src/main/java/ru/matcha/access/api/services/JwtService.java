package ru.matcha.access.api.services;

public interface JwtService {

    String generateJwt(String email);

    String getEmail(String jwt);

    void validateJwt(String jwt);
}
