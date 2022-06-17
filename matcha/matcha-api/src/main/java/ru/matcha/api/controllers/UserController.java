package ru.matcha.api.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.matcha.api.models.requests.PagebleRequest;
import ru.matcha.api.models.responses.CurrentUserResponse;
import ru.matcha.api.models.responses.UserList;

import javax.validation.Valid;

@Validated
@RequestMapping("/users")
public interface UserController {

    @GetMapping("/get/current")
    CurrentUserResponse currentUser();

    @GetMapping("/get/all")
    UserList getAll(@Valid PagebleRequest page);
}