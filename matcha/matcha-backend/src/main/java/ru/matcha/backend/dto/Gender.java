package ru.matcha.backend.dto;

import lombok.Value;
import ru.matcha.datasource.entities.enums.GenderDto;

@Value
public class Gender {

    Long id;
    GenderDto name;
}
