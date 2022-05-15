package ru.matcha.models.entities;

import lombok.*;
import ru.matcha.models.dto.OrientationDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = {"id", "name"})
@RequiredArgsConstructor
@Entity(name = "t_orientation")
public class Orientation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false, unique = true)
    private OrientationDto name;
    @OneToMany(mappedBy = "orientation")
    private Set<User> users;
}