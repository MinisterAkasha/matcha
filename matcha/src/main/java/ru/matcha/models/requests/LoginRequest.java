package ru.matcha.models.requests;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;

@Value
@Builder
@Jacksonized
public class LoginRequest {
    @NotBlank
    String email;
    @NotBlank
    String password;
}
