package ru.matcha.backend.mappers;

import org.mapstruct.Mapper;
import ru.matcha.backend.dto.Orientation;

@Mapper
public interface OrientationMapper {

    default String toString(Orientation orientation) {
        return orientation != null ? orientation.getName().name() : null;
    }

    Orientation toDto(ru.matcha.datasource.entities.Orientation orientationEntity);

    ru.matcha.datasource.entities.Orientation toEntity(Orientation orientationDto);
}
