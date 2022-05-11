package ru.matcha.models.responces.jwt;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TokenRefreshResponse {
    String accessToken;
    String refreshToken;
    String tokenType = "Bearer";
}
