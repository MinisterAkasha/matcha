package ru.matcha.datasource.entities;

import lombok.*;
import ru.matcha.datasource.entities.enums.OrientationDto;

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
    @Column(length = 20, nullable = false, unique = true)
    private OrientationDto name;
    @OneToMany(mappedBy = "orientation")
    private Set<User> users;
}