package ru.matcha.mappers;

import org.mapstruct.Mapper;
import ru.matcha.models.entities.Gender;

@Mapper
public interface GenderMapper {

    default String toString(Gender gender) {
        return gender != null ? gender.getName().name() : null;
    }
}
