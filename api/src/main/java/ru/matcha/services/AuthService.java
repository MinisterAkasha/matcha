package ru.matcha.services;

import ru.matcha.exceptions.EmailAlreadyExistsException;
import ru.matcha.models.entities.User;
import ru.matcha.models.requests.LoginRequest;
import ru.matcha.models.requests.SignupRequest;
import ru.matcha.models.responces.UserResponse;
import ru.matcha.models.responces.jwt.TokenResponse;

import javax.transaction.Transactional;

public interface AuthService {

    TokenResponse registerUser(SignupRequest signUpRequest) throws EmailAlreadyExistsException;

    TokenResponse authenticateUser(LoginRequest loginRequest);

    @Transactional
    void logout(User user);

    UserResponse getCurrentUser(User user);
}
