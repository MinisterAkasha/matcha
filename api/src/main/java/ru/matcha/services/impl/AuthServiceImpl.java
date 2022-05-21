package ru.matcha.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matcha.config.jwt.JwtUtils;
import ru.matcha.exceptions.EmailAlreadyExistsException;
import ru.matcha.exceptions.LogOutException;
import ru.matcha.mappers.TokenMapper;
import ru.matcha.mappers.UserMapper;
import ru.matcha.models.entities.RefreshToken;
import ru.matcha.models.entities.Role;
import ru.matcha.models.entities.User;
import ru.matcha.models.dto.RoleDto;
import ru.matcha.models.requests.LoginRequest;
import ru.matcha.models.requests.SignupRequest;
import ru.matcha.models.responces.jwt.TokenResponse;
import ru.matcha.repositories.UserRepository;
import ru.matcha.services.AuthService;
import ru.matcha.services.RefreshTokenService;

import javax.security.auth.login.CredentialException;
import java.util.Collections;

import static ru.matcha.constraints.ExceptionConstraint.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;
    private final TokenMapper tokenMapper;

    @Override
    @Transactional
    public TokenResponse registerUser(SignupRequest signUpRequest) throws EmailAlreadyExistsException, CredentialException {
        if (userRepository.existsByEmail(signUpRequest.getEmail()).equals(true)) {
            throw new EmailAlreadyExistsException(String.format(EMAIL_ALREADY_EXISTS, signUpRequest.getEmail()));
        }

        userRepository.save(
                userMapper.toEntity(signUpRequest, encoder.encode(signUpRequest.getPassword()),
                        true,
                        Collections.singleton(new Role(2L, RoleDto.USER)))
        );

        return authenticateUser(userMapper.toLoginRq(signUpRequest));
    }

    @Override
    @Transactional
    public TokenResponse authenticateUser(LoginRequest loginRequest) throws CredentialException {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication userDetails = authenticationManager.authenticate(authentication);
        User user = (User) userDetails.getPrincipal();

        if (!encoder.matches((String) authentication.getCredentials(), user.getPassword()))
            throw new CredentialException(PASSWORD_ERROR);

        String jwt = jwtUtils.generateJwtToken(user);

        refreshTokenService.deleteByUserId(user.getId());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
        SecurityContextHolder.getContext().setAuthentication(userDetails);
        return tokenMapper.toRs(jwt, refreshToken.getToken());
    }

    @Override
    @Transactional
    public void logout(User user) {
        if (user == null)
            throw new LogOutException(LOGOUT_ERROR);
        refreshTokenService.deleteByUserId(user.getId());
        SecurityContextHolder.clearContext();
    }
}
