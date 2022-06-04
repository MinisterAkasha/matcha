package ru.matcha.datasource.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@RequiredArgsConstructor
@Entity(name = "t_like")
public class Like {

    @EmbeddedId
    private LikeId id;
}

@Embeddable
@EqualsAndHashCode(of = {"formUser", "toUser"})
class LikeId implements Serializable {
    @ManyToOne
    private User formUser;
    @ManyToOne
    private User toUser;
}
