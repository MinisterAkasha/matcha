package ru.matcha.api.models.responses;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class UserList {

    List<UserResponse> content;
    boolean hasPrevious;
    boolean hasNext;
}
