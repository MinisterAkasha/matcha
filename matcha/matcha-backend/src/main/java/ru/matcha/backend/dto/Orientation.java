package ru.matcha.backend.dto;

import lombok.Value;
import ru.matcha.datasource.entities.enums.OrientationDto;

@Value
public class Orientation {

    Long id;
    OrientationDto name;
}
