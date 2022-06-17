package ru.matcha.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import ru.matcha.api.controllers.UserController;
import ru.matcha.api.models.requests.PagebleRequest;
import ru.matcha.backend.dto.UserDetailsImpl;
import ru.matcha.api.models.responses.CurrentUserResponse;
import ru.matcha.api.models.responses.UserList;
import ru.matcha.backend.services.UserService;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public CurrentUserResponse currentUser() {

        return userService.getCurrentUser((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Override
    public UserList getAll(PagebleRequest page) {
        return userService.getAll(page);
    }
}
