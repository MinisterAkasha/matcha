package ru.matcha.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import ru.matcha.api.controllers.AuthController;
import ru.matcha.backend.dto.UserDetailsImpl;
import ru.matcha.api.models.requests.LoginRequest;
import ru.matcha.api.models.requests.SignupRequest;
import ru.matcha.api.models.requests.TokenRefreshRequest;
import ru.matcha.api.models.responses.jwt.TokenResponse;
import ru.matcha.backend.services.AuthService;
import ru.matcha.backend.services.RefreshTokenService;

import javax.security.auth.login.CredentialException;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public TokenResponse authenticateUser(LoginRequest loginRequest) throws CredentialException {

        return authService.authenticateUser(loginRequest);
    }

    @Override
    public TokenResponse registerUser(SignupRequest signUpRequest) throws CredentialException {

        return authService.registerUser(signUpRequest);
    }

    @Override
    public TokenResponse refreshToken(TokenRefreshRequest request) {

        return refreshTokenService.updateToken(request.getRefreshToken());
    }

    @Override
    public void logoutUser() {

        authService.logout((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}