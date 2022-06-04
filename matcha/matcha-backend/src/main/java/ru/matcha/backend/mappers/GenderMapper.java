package ru.matcha.backend.mappers;

import org.mapstruct.Mapper;
import ru.matcha.backend.dto.Gender;

@Mapper
public interface GenderMapper {

    default String toString(Gender gender) {
        return gender != null ? gender.getName().name() : null;
    }

    Gender toDto(ru.matcha.datasource.entities.Gender genderEntity);

    ru.matcha.datasource.entities.Gender toEntity(Gender genderDto);
}
