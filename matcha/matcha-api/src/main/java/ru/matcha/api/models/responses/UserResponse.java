package ru.matcha.api.models.responses;

import lombok.Value;

import java.time.LocalDate;
import java.util.Set;

@Value
public class UserResponse {
    String id;
    String username;
    String email;
    String gender;
    String orientation;
    Set<String> preferences;
    LocalDate birthday;
    String description;
    String longitude;
    String latitude;
}
