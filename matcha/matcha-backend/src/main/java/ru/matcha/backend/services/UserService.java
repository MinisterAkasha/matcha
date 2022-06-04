package ru.matcha.backend.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.matcha.backend.dto.UserDetailsImpl;
import ru.matcha.api.models.responses.CurrentUserResponse;

public interface UserService extends UserDetailsService {

    CurrentUserResponse getCurrentUser(UserDetailsImpl user);
}
