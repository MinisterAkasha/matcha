package ru.matcha.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.matcha.api.controllers.FeedController;
import ru.matcha.api.models.requests.PagebleRequest;
import ru.matcha.api.models.requests.UserCriteriaRequest;
import ru.matcha.api.models.responses.UserList;
import ru.matcha.backend.services.FeedService;

@RestController
@RequiredArgsConstructor
public class FeedControllerImpl implements FeedController {

    private final FeedService feedService;

    @Override
    public UserList getAll(UserCriteriaRequest request, PagebleRequest page) {
        return feedService.getFeed(request, page);
    }
}
