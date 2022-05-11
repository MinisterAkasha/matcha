package ru.matcha.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.matcha.config.jwt.JwtUtils;
import ru.matcha.exceptions.EmailAlreadyExistsException;
import ru.matcha.mappers.UserMapper;
import ru.matcha.models.entities.RefreshToken;
import ru.matcha.models.entities.Role;
import ru.matcha.models.entities.User;
import ru.matcha.models.entities.UserRole;
import ru.matcha.models.requests.LoginRequest;
import ru.matcha.models.requests.SignupRequest;
import ru.matcha.models.responces.UserResponse;
import ru.matcha.models.responces.UserJwtResponse;
import ru.matcha.repositories.UserRepository;
import ru.matcha.services.AuthService;
import ru.matcha.services.RefreshTokenService;

import java.util.Collections;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final String EMAIL_ALREADY_EXISTS = "Пользователь с email %s уже зарегистрирован.";

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;

    @Override
    public UserResponse registerUser(SignupRequest signUpRequest) throws EmailAlreadyExistsException {
        if (userRepository.existsByEmail(signUpRequest.getEmail()).equals(true)) {
            throw new EmailAlreadyExistsException(String.format(EMAIL_ALREADY_EXISTS, signUpRequest.getEmail()));
        }

        User user = userRepository.save(
                userMapper.toEntity(signUpRequest, encoder.encode(signUpRequest.getPassword()),
                        true,
                        Collections.singleton(new Role(2L, UserRole.USER)))
        );

        return userMapper.toUserRs(user);
    }

    @Override
    public UserJwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication userDetails = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(userDetails);
        User user = (User) userDetails.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(user);

        refreshTokenService.deleteByUserId(user.getId());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

        return userMapper.toUserJwtRs(user, jwt, refreshToken.getToken());
    }
}
