package ru.matcha.api.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.matcha.api.models.requests.PagebleRequest;
import ru.matcha.api.models.requests.UserCriteriaRequest;
import ru.matcha.api.models.responses.UserList;

import javax.validation.Valid;

@Validated
@RequestMapping("/feed")
public interface FeedController {

    @GetMapping("/get/all")
    UserList getAll(UserCriteriaRequest request, @Valid PagebleRequest page);
}
