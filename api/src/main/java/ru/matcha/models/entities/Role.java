package ru.matcha.models.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import ru.matcha.models.dto.RoleDto;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of = {"name"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_role",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "name")})
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleDto name;

    @Override
    public String getAuthority() {
        return name.name();
    }
}
