package ru.matcha.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matcha.mappers.UserMapper;
import ru.matcha.models.entities.User;
import ru.matcha.models.responces.CurrentUserResponse;
import ru.matcha.repositories.UserRepository;
import ru.matcha.services.UserService;

import static ru.matcha.constraints.ExceptionConstraint.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    @Override
    public CurrentUserResponse getCurrentUser(User user) {
        return userMapper.toUserRs(user);
    }
}
