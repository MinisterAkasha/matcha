package ru.matcha.services;

import ru.matcha.exceptions.EmailAlreadyExistsException;
import ru.matcha.models.requests.LoginRequest;
import ru.matcha.models.requests.SignupRequest;
import ru.matcha.models.responces.UserJwtResponse;
import ru.matcha.models.responces.UserResponse;

public interface AuthService {

    UserResponse registerUser(SignupRequest signUpRequest) throws EmailAlreadyExistsException;

    UserJwtResponse authenticateUser(LoginRequest loginRequest);
}
