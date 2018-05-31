package pl.coderslab.bets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.bets.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
