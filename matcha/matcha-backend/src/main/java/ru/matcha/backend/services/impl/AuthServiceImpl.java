package ru.matcha.backend.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matcha.backend.dto.RoleImpl;
import ru.matcha.backend.dto.UserDetailsImpl;
import ru.matcha.backend.services.RestService;
import ru.matcha.datasource.entities.RefreshToken;
import ru.matcha.datasource.entities.enums.RoleDto;
import ru.matcha.backend.exceptions.EmailAlreadyExistsException;
import ru.matcha.backend.exceptions.LogOutException;
import ru.matcha.backend.mappers.TokenMapper;
import ru.matcha.backend.mappers.UserMapper;
import ru.matcha.api.models.requests.LoginRequest;
import ru.matcha.api.models.requests.SignupRequest;
import ru.matcha.api.models.responses.jwt.TokenResponse;
import ru.matcha.datasource.repositories.UserRepository;
import ru.matcha.backend.services.AuthService;
import ru.matcha.backend.services.RefreshTokenService;

import javax.security.auth.login.CredentialException;
import java.util.Collections;

import static ru.matcha.backend.constraints.ExceptionConstraint.*;
import static ru.matcha.backend.constraints.LogConstraint.SUCCESS_LOGOUT;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final TokenMapper tokenMapper;
    private final RestService restService;

    @Override
    @Transactional
    public TokenResponse registerUser(SignupRequest signUpRequest) throws EmailAlreadyExistsException, CredentialException {
        if (userRepository.existsByEmail(signUpRequest.getEmail()).equals(true)) {
            throw new EmailAlreadyExistsException(String.format(EMAIL_ALREADY_EXISTS, signUpRequest.getEmail()));
        }

        userRepository.save(
                userMapper.toEntity(signUpRequest, encoder.encode(signUpRequest.getPassword()),
                        true,
                        Collections.singleton(new RoleImpl(2L, RoleDto.USER)))
        );

        return authenticateUser(userMapper.toLoginRq(signUpRequest));
    }

    @Override
    @Transactional
    public TokenResponse authenticateUser(LoginRequest loginRequest) throws CredentialException {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication userDetails = authenticationManager.authenticate(authentication);
        UserDetailsImpl user = (UserDetailsImpl) userDetails.getPrincipal();

        if (!encoder.matches((String) authentication.getCredentials(), user.getPassword()))
            throw new CredentialException(PASSWORD_ERROR);

        String jwt = restService.postAccess("/generateJwt", user.getEmail(), String.class).getBody();

        refreshTokenService.deleteByUserId(user.getId());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
        SecurityContextHolder.getContext().setAuthentication(userDetails);
        return tokenMapper.toRs(true, jwt, refreshToken.getToken());
    }

    @Override
    @Transactional
    public void logout(UserDetailsImpl user) {
        if (user == null)
            throw new LogOutException(LOGOUT_ERROR);
        refreshTokenService.deleteByUserId(user.getId());
        SecurityContextHolder.clearContext();
        log.info(SUCCESS_LOGOUT, user.getEmail());
    }
}
