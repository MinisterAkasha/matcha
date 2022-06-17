package ru.matcha.api.models.requests;

import lombok.Value;

@Value
public class UserCriteriaRequest {

    Integer minAge;
    Integer maxAge;
    String gender;
}
