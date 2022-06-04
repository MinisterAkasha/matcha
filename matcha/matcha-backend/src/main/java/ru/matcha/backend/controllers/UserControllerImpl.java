package ru.matcha.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import ru.matcha.api.controllers.UserController;
import ru.matcha.backend.dto.UserDetailsImpl;
import ru.matcha.api.models.responses.CurrentUserResponse;
import ru.matcha.backend.services.UserService;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<CurrentUserResponse> currentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(userService.getCurrentUser((UserDetailsImpl) authentication.getPrincipal()));
    }
}
