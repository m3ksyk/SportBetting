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

    @ManyToOne
    @JoinColumn
    private User user; //user making the bet

    @ManyToOne
    @JoinColumn
    private Game game; //the game bet is made on

    private BigDecimal amount;

    private BigDecimal rate; //rate is gotten from game team odd

    @OneToOne
    @JoinColumn
    private Team bettingTeam;
    //change to string?

    private boolean win;

    private boolean groupBet;

    private BigDecimal amountWon;

    private BigDecimal calculateWonAmount(BigDecimal amount, BigDecimal rate, boolean win){
        if (win){
            return amount.multiply(rate);
        }else{
            return BigDecimal.valueOf(0);
        }
    }

    private boolean paidOut;


}
