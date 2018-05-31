package pl.coderslab.bets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.bets.entity.Bet;

@Repository
public interface BetRepository extends JpaRepository <Bet, Long> {
}
