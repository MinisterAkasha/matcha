package ru.matcha.api.controllers;

import org.springframework.validation.annotation.Validated;
import ru.matcha.api.models.requests.SignupRequest;
import ru.matcha.api.models.requests.LoginRequest;
import ru.matcha.api.models.requests.TokenRefreshRequest;
import ru.matcha.api.models.responses.jwt.TokenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.auth.login.CredentialException;
import javax.validation.Valid;

@Validated
@RequestMapping("/auth")
public interface AuthController {

    @PostMapping("/signin")
    TokenResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws CredentialException;

    @PostMapping("/signup")
    TokenResponse registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws CredentialException;

    @PostMapping("/refreshToken")
    TokenResponse refreshToken(@Valid @RequestBody TokenRefreshRequest request);

    @PostMapping("/logout")
    void logoutUser();
}
