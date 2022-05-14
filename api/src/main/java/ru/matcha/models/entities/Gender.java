package ru.matcha.models.entities;

import lombok.*;
import ru.matcha.models.dto.GenderDto;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = {"name"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_gender",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "name")})
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private GenderDto name;
    @OneToMany(mappedBy = "gender")
    private Set<User> users;
}
