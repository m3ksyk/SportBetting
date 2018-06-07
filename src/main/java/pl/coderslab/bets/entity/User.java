package pl.coderslab.bets.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * Class represents application users
 */

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(unique = true)
    @NotBlank(message = "field cannot be empty. Also, must be a unique username")
    private String username;

    @NotNull
    @Email(message = "must be in correct format")
    @NotBlank(message = "field must be filled")
    @Column(unique = true)
    private String email;

    @NotNull
    @NotBlank(message = "field must be filled")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Bet> bets;

    @CreditCardNumber
    private String creditCardNum;

    @NotNull
    private BigDecimal wallet;

    @OneToOne(fetch = FetchType.LAZY)
    private BettingGroup bettingGroup;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Message> messages;

    @ManyToMany
    @JoinColumn
    private List<Team> subscriptions;

    public List<Team> addSubscription(Team team){
        List<Team> subscriptions = this.getSubscriptions();
        subscriptions.add(team);
        return subscriptions;
    }

    public String toString() {
        return this.getUsername();
    }

    public List<Team> removeSubscription(Team team) {
        List<Team> subscriptions = this.getSubscriptions();
        subscriptions.remove(team);
        return subscriptions;
    }
}
