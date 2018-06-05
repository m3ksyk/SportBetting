package pl.coderslab.bets.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Apikey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String value;
}
