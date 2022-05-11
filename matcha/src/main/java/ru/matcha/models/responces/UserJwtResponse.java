package ru.matcha.models.responces;

import lombok.Value;

import java.util.Set;

@Value
public class UserJwtResponse {
    String accessToken;
    String typeType = "Bearer";
    String refreshToken;
    Long id;
    String username;
    String email;
    Set<String> roles;
    boolean enabled;
    boolean active;
}
