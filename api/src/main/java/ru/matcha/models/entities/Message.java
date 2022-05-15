package ru.matcha.models.entities;

import lombok.*;

import javax.persistence.*;

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
}
