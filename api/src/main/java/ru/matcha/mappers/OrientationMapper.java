package ru.matcha.mappers;

import org.mapstruct.Mapper;
import ru.matcha.models.entities.Orientation;

@Mapper
public interface OrientationMapper {

    default String toString(Orientation orientation) {
        return orientation != null ? orientation.getName().name(): null;
    }
}
