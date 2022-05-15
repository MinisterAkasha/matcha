package ru.matcha.models.entities;

import lombok.*;
import ru.matcha.models.dto.GenderDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = {"id", "name"})
@RequiredArgsConstructor
@Entity(name = "t_gender")
public class Gender implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false, unique = true)
    private GenderDto name;
    @OneToMany(mappedBy = "gender")
    private Set<User> users;
}
