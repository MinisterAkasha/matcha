package ru.matcha.models.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import ru.matcha.models.dto.RoleDto;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of = {"id", "name"})
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "t_role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false, unique = true)
    private RoleDto name;

    @Override
    public String getAuthority() {
        return name.name();
    }
}