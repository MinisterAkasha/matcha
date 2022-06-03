package ru.matcha.models.responces.exeption;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse {
    boolean success;
    String message;
}
