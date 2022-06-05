package ru.matcha.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.matcha.api.models.responses.CurrentUserResponse;
import ru.matcha.api.models.responses.UserList;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Validated
@RequestMapping("/users")
public interface UserController {

    @GetMapping("/get/current")
    ResponseEntity<CurrentUserResponse> currentUser();

    @GetMapping("/get/all")
    ResponseEntity<UserList> getAll(@RequestParam @Valid @Positive int limit, @RequestParam @Valid @PositiveOrZero int offset);
}
