package ru.matcha.datasource.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@RequiredArgsConstructor
@Entity(name = "t_chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @OneToMany(mappedBy = "chat")
    List<Message> messages;
}
