package pl.coderslab.bets.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User user; //user making the bet

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Game game; //the game bet is made on

    private BigDecimal amount;

    private BigDecimal rate; //rate is gotten from game team odd

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Team bettingTeam;

    private boolean willDraw;

    private boolean win;

    private boolean groupBet;

    private BigDecimal amountWon;

    private boolean paidOut;

}
