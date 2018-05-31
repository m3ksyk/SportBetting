package pl.coderslab.bets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.bets.entity.Sport;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {
    Sport findByNameEquals(String name);
}
