package pl.coderslab.bets.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Set;

@Data
@Entity
public class Team{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn
    private League league;

    @ManyToOne
    @JoinColumn
    private Sport sport;

    @OneToMany
    @JoinColumn
    private Set<Game> games;

    private int gamesWon;
    private int gamesLost;
    private int draws;
    private int goalsScored;
    private int goalsLost;

    private int tableStanding;

    //flag to check if team is currently in-game
    private boolean inGame;

    @Override
    public String toString() {
        return name;
    }
}

