package pl.coderslab.bets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.bets.entity.League;

@Repository
public interface LeagueRepository extends JpaRepository <League, Long> {
    League findByNameEquals(String name);
}
