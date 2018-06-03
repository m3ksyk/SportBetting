package pl.coderslab.bets.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String name;

    @OneToMany
    @JoinColumn
    private Set<League> leagues;

    @OneToMany
    @JoinColumn
    private Set<Team> teams;

    @Override
    public String toString() {
        return  name;
    }
}
