package ru.matcha.access.api.services;

public interface JwtService {

    String generate(String email);

    String getEmail(String jwt);

    void validate(String jwt);

    String parse(String jwt);
}
