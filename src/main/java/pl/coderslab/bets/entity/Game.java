package pl.coderslab.bets.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn   //spr czy nie zakrzaczy
    private Sport sport;

    private Date start;
    private Date end;

    @OneToOne
    @JoinColumn
    private Team homeTeam;
    @OneToOne
    @JoinColumn
    private Team awayTeam;

    private int homeTeamScore;

    private int awayTeamScore;

    private int homeTeamOdd;

    private int awayTeamOdd;

    private String status;

}
