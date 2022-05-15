package ru.matcha.models.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@RequiredArgsConstructor
@Entity(name = "t_user_block")
public class UserBlock {

    @EmbeddedId
    private UserBlockId id;
}

@Embeddable
@EqualsAndHashCode(of = {"formUser", "toUser"})
class UserBlockId implements Serializable {
    @ManyToOne
    private User formUser;
    @ManyToOne
    private User toUser;
}
