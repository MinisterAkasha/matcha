package ru.matcha.models.responces;

import lombok.Value;

import java.util.Set;

@Value
public class UserResponse {
    String id;
    String username;
    String email;
    Set<String> roles;
    boolean enabled;
    boolean active;
}
