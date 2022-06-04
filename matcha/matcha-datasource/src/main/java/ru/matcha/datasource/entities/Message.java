package ru.matcha.datasource.entities;

import lombok.*;
import ru.matcha.datasource.entities.enums.MessageStatus;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(of = {"id", "content"})
@RequiredArgsConstructor
@Entity(name = "t_message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Chat chat;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User recipient;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Date timestamp;
    @Column(nullable = false)
    private MessageStatus status;
}
