package ru.matcha.backend.dto;

import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Set;

@Value
public class UserDetailsImpl implements UserDetails {

    Long id;
    String username;
    String email;
    String password;
    Set<RoleImpl> authorities;
    boolean active;
    boolean enabled;
    Gender gender;
    Orientation orientation;
    Set<Gender> preferences;
    LocalDate birthday;
    String description;
    String longitude;
    String latitude;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
