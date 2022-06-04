package ru.matcha.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import ru.matcha.api.models.responses.CurrentUserResponse;

public interface UserController {

    @GetMapping("/currentUser")
    ResponseEntity<CurrentUserResponse> currentUser();
}
