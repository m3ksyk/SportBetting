package pl.coderslab.bets.entity;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Class Team represents sports teams that take part in the event
 */

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

    @ManyToMany
    @JoinColumn
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> subscribers;

    public List<User> addSubscriber(User user){
        List<User> subscribers = this.getSubscribers();
        subscribers.add(user);
        return subscribers;
    }

    public List<User> removeSubscriber(User user) {
        List<User> subscribers = this.getSubscribers();
        subscribers.remove(user);
        return subscribers;
    }

    @Override
    public String toString() {
        return name;
    }

}

