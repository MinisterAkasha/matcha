package ru.matcha.backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.matcha.api.models.requests.PagebleRequest;
import ru.matcha.api.models.requests.UserCriteriaRequest;
import ru.matcha.api.models.responses.UserList;
import ru.matcha.backend.mappers.UserMapper;
import ru.matcha.backend.services.FeedService;
import ru.matcha.datasource.entities.Gender;
import ru.matcha.datasource.entities.User;
import ru.matcha.datasource.entities.enums.GenderDto;
import ru.matcha.datasource.repositories.UserRepository;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserList getFeed(UserCriteriaRequest request, PagebleRequest page) {
        userRepository.findAll(specificationUser(request));
        return userMapper.toDto(userRepository.findAll(specificationUser(request), PageRequest.of(page.getOffset(), page.getLimit())));
    }

    private Specification<User> specificationUser(UserCriteriaRequest request) {

        return (root, query, builder) -> {
            if (Objects.isNull(request.getMinAge()) && Objects.isNull(request.getMaxAge()) && Objects.isNull(request.getGender()))
                return builder.and();

            LocalDate today = LocalDate.now();
            Predicate p = builder.and();
            if (Objects.nonNull(request.getMinAge())) {
                p = builder.and(p, builder.and(builder.lessThanOrEqualTo(root.get("birthday"), today.minusYears(request.getMinAge()))));
            }
            if (Objects.nonNull(request.getMaxAge()))
                p = builder.and(p, builder.greaterThanOrEqualTo(root.get("birthday"), today.minusYears(request.getMaxAge())));
            if (Objects.nonNull(request.getGender()) && (request.getGender().equals(GenderDto.MALE.name()) || request.getGender().equals(GenderDto.FEMALE.name()))) {
                Join<Gender, User> genderUser = root.join("gender");
                p = builder.and(p, builder.and(builder.equal(genderUser.get("name"), request.getGender())));
            }
            return p;
        };
    }
}
