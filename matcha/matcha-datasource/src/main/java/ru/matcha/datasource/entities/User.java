package ru.matcha.datasource.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = {"email"})
@RequiredArgsConstructor
@Entity(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "t_user_role_link",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities = new HashSet<>();
    private boolean active;
    private boolean enabled;
    @ManyToOne
    private Gender gender;
    @ManyToOne
    private Orientation orientation;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "t_user_preference_link",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "gender_id"))
    private Set<Gender> preferences = new HashSet<>();
    private LocalDate birthday;
    private String description;
    private String longitude;
    private String latitude;
}
