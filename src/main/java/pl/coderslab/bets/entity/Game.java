package pl.coderslab.bets.entity;

import lombok.Data;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Class Game represents sports events
 */

@Data
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn
    private Sport sport;

    private Timestamp start;
    private Timestamp end;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Team awayTeam;

    @OneToOne
    @JoinColumn
    private Team winner;

    private boolean drawn;

    private int homeTeamScore;

    private int awayTeamScore;

    private double homeTeamWinOdd;

    private double homeTeamWinOrDrawOdd;

    private double awayTeamWinOdd;

    private double awayTeamWinOrDrawOdd;

    private double DrawOdd;

    private String status;

    private boolean betsPaidOut;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private List<Bet> bets;

    @Override
    public String toString() {
        return  homeTeam.getName() +
                " vs " + awayTeam.getName();
    }
}
