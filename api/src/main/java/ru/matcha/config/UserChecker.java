package ru.matcha.config;

import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

import static ru.matcha.config.jwt.JwtLogConstraint.ACCOUNT_DISABLED;

@Component
public class UserChecker implements UserDetailsChecker {

    @Override
    public void check(UserDetails toCheck) {
        if (!toCheck.isEnabled())
            throw new LockedException(ACCOUNT_DISABLED);
    }
}
