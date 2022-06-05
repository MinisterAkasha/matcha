package ru.matcha.backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matcha.api.models.responses.UserList;
import ru.matcha.backend.dto.UserDetailsImpl;
import ru.matcha.backend.mappers.UserMapper;
import ru.matcha.api.models.responses.CurrentUserResponse;
import ru.matcha.datasource.repositories.UserRepository;
import ru.matcha.backend.services.UserService;

import static ru.matcha.backend.constraints.ExceptionConstraint.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userMapper.toDto(userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email))));
    }

    @Override
    public CurrentUserResponse getCurrentUser(UserDetailsImpl user) {
        return userMapper.toUserRs(user);
    }

    @Override
    public UserList getAll(int limit, int offset) {
        return userMapper.toDto(userRepository.findAll(PageRequest.of(offset, limit)));
    }
}
