package pl.coderslab.bets.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class BettingGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String name;

    @OneToMany
    private List<Bet> bets;

    @OneToMany
    private Set<User> users;
    //pomyslec jak to zrobic i rozwinac


}
