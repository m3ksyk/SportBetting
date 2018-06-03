package pl.coderslab.bets.entity;

import lombok.Data;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn   //spr czy nie zakrzaczy
    private Sport sport;

    private Timestamp start;
    private Timestamp end;

    @ManyToOne
    @JoinColumn
    private Team homeTeam;

    @ManyToOne
    @JoinColumn
    private Team awayTeam;

    @ManyToOne
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

    @OneToMany
    @JoinColumn
    private List<Bet> bets;
}
