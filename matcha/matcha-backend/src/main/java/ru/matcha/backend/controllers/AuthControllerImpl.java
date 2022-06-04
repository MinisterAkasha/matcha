package ru.matcha.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<TokenResponse> authenticateUser(LoginRequest loginRequest) throws CredentialException {

        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

    @Override
    public ResponseEntity<TokenResponse> registerUser(SignupRequest signUpRequest) throws CredentialException {

        return ResponseEntity.ok(authService.registerUser(signUpRequest));
    }

    @Override
    public ResponseEntity<TokenResponse> refreshToken(TokenRefreshRequest request) {

        return ResponseEntity.ok(refreshTokenService.updateToken(request.getRefreshToken()));
    }

    @Override
    public ResponseEntity<Void> logoutUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authService.logout((UserDetailsImpl) authentication.getPrincipal());
        return ResponseEntity.ok().build();
    }
}