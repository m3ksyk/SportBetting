package pl.coderslab.bets.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Class role represents the role of the user.
 * Class is used in api security system
 */

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;

    @Column(name = "role")
    private String name;

}
