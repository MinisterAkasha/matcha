package ru.matcha.api.models.requests;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;

@Value
@Builder
@Jacksonized
public class TokenRefreshRequest {
    @NotBlank
    String refreshToken;
}
