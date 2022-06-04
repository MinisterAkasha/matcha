package ru.matcha.backend.services;

import ru.matcha.backend.dto.UserDetailsImpl;
import ru.matcha.backend.exceptions.EmailAlreadyExistsException;
import ru.matcha.api.models.requests.LoginRequest;
import ru.matcha.api.models.requests.SignupRequest;
import ru.matcha.api.models.responses.jwt.TokenResponse;

import javax.security.auth.login.CredentialException;
import javax.transaction.Transactional;

public interface AuthService {

    TokenResponse registerUser(SignupRequest signUpRequest) throws EmailAlreadyExistsException, CredentialException;

    TokenResponse authenticateUser(LoginRequest loginRequest) throws CredentialException;

    @Transactional
    void logout(UserDetailsImpl user);
}
