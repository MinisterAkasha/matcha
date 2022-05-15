package ru.matcha.models.responces.jwt;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TokenResponse {
    String tokenType = "Bearer";
    String accessToken;
    String refreshToken;
}
