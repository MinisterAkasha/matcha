package ru.matcha.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.matcha.models.entities.User;
import ru.matcha.models.responces.CurrentUserResponse;
import ru.matcha.services.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/currentUser")
    public ResponseEntity<CurrentUserResponse> currentUser(Authentication authentication) {

        return ResponseEntity.ok(userService.getCurrentUser((User) authentication.getPrincipal()));
    }
}
