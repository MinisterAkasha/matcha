package ru.matcha.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.matcha.exceptions.EmailAlreadyExistsException;
import ru.matcha.exceptions.LogOutException;
import ru.matcha.models.requests.LogOutRequest;
import ru.matcha.models.requests.LoginRequest;
import ru.matcha.models.requests.SignupRequest;
import ru.matcha.models.requests.TokenRefreshRequest;
import ru.matcha.models.responces.UserJwtResponse;
import ru.matcha.models.responces.UserResponse;
import ru.matcha.models.responces.jwt.TokenRefreshResponse;
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
    public ResponseEntity<UserJwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws EmailAlreadyExistsException {

        return ResponseEntity.ok(authService.registerUser(signUpRequest));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {

        return ResponseEntity.ok(refreshTokenService.updateToken(request.getRefreshToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) throws LogOutException {

        refreshTokenService.deleteByUserId(logOutRequest.getUserId());
        return ResponseEntity.ok().build();
    }

}