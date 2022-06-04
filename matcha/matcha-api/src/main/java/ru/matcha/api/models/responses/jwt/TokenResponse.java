package ru.matcha.api.models.responses.jwt;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TokenResponse {
    boolean success;
    String accessToken;
    String refreshToken;
}
