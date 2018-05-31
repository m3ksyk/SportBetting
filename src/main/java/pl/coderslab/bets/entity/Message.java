package pl.coderslab.bets.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String sender;

    @ManyToOne
    User recipient;

    private String text;

    private boolean read;
}
