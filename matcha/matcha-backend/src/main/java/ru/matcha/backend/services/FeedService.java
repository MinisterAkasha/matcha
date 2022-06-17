package ru.matcha.backend.services;

import ru.matcha.api.models.requests.PagebleRequest;
import ru.matcha.api.models.requests.UserCriteriaRequest;
import ru.matcha.api.models.responses.UserList;

public interface FeedService {

    UserList getFeed(UserCriteriaRequest request, PagebleRequest page);
}
