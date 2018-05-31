package pl.coderslab.bets.entity;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private String cryptSalt;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany
    private List<Bet> bets;

    private String creditCardNum;

    @NotNull
    private BigDecimal wallet;

    @OneToOne
    private BettingGroup bettingGroup;

    @OneToMany
    private List<Message> messages;
    //add subscriptions and favs if other funcs are go

//    public void setCryptSalt(String cryptSalt) {
//        this.cryptSalt = BCrypt.gensalt();
//    }
//
//    public void setPassword(String password) {
//        this.password = BCrypt.hashpw(password, cryptSalt);
//    }
}
