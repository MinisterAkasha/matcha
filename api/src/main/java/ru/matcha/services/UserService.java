package ru.matcha.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.matcha.models.entities.User;
import ru.matcha.models.responces.CurrentUserResponse;

public interface UserService extends UserDetailsService {

    CurrentUserResponse getCurrentUser(User user);
}
