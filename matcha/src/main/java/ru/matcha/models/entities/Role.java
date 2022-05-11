package ru.matcha.models.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of = {"name"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRole name;

    @Override
    public String getAuthority() {
        return name.name();
    }
}
