package ru.matcha.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.matcha.exceptions.EmailAlreadyExistsException;
import ru.matcha.exceptions.LogOutException;
import ru.matcha.models.entities.User;
import ru.matcha.models.requests.LoginRequest;
import ru.matcha.models.requests.SignupRequest;
import ru.matcha.models.requests.TokenRefreshRequest;
import ru.matcha.models.responces.UserResponse;
import ru.matcha.models.responces.jwt.TokenResponse;
import ru.matcha.services.AuthService;
import ru.matcha.services.RefreshTokenService;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signin")
    public ResponseEntity<TokenResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<TokenResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws EmailAlreadyExistsException {

        return ResponseEntity.ok(authService.registerUser(signUpRequest));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<TokenResponse> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {

        return ResponseEntity.ok(refreshTokenService.updateToken(request.getRefreshToken()));
    }

    @GetMapping("/currentUser")
    public ResponseEntity<UserResponse> currentUser(Authentication authentication) {

        return ResponseEntity.ok(authService.getCurrentUser((User) authentication.getPrincipal()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser(Authentication authentication) throws LogOutException {

        authService.logout((User) authentication.getPrincipal());
        return ResponseEntity.ok().build();
    }

}