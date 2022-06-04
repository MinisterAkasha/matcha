package ru.matcha.datasource.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@RequiredArgsConstructor
@Entity(name = "t_history")
public class History {

    @EmbeddedId
    private HistoryId id;
}

@Embeddable
@EqualsAndHashCode(of = {"formUser", "toUser"})
class HistoryId implements Serializable {
    @ManyToOne
    private User formUser;
    @ManyToOne
    private User toUser;
}
