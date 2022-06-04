package ru.matcha.access.api.constraints;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogConstraint {
    public static final String INVALID_JWT_SIGNATURE = "Invalid JWT signature: {}";
    public static final String INVALID_JWT_TOKEN = "Invalid JWT token: {}";
    public static final String JWT_TOKEN_EXPIRED = "JWT token is expired: {}";
    public static final String JWT_TOKEN_UNSUPPORTED = "JWT token is unsupported: {}";
    public static final String JWT_CLAIMS = "JWT claims string is empty: {}";
}
