package ru.matcha.datasource.entities;

import lombok.*;
import ru.matcha.datasource.entities.enums.RoleDto;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of = {"id", "name"})
@RequiredArgsConstructor
@Entity(name = "t_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false, unique = true)
    private RoleDto name;

}
