package ru.matcha.services;

import ru.matcha.exceptions.EmailAlreadyExistsException;
import ru.matcha.models.entities.User;
import ru.matcha.models.requests.LoginRequest;
import ru.matcha.models.requests.SignupRequest;
import ru.matcha.models.responces.CurrentUserResponse;
import ru.matcha.models.responces.jwt.TokenResponse;

import javax.security.auth.login.CredentialException;
import javax.transaction.Transactional;

public interface AuthService {

    TokenResponse registerUser(SignupRequest signUpRequest) throws EmailAlreadyExistsException, CredentialException;

    TokenResponse authenticateUser(LoginRequest loginRequest) throws CredentialException;

    @Transactional
    void logout(User user);

    CurrentUserResponse getCurrentUser(User user);
}
