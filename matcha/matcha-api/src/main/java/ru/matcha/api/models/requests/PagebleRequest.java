package ru.matcha.api.models.requests;

import lombok.Value;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Value
public class PagebleRequest {

    @Positive
    Integer limit;
    @PositiveOrZero
    Integer offset;
}
