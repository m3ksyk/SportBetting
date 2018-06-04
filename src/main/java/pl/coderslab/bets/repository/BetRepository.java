package pl.coderslab.bets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.bets.entity.Bet;

import java.util.List;

@Repository
public interface BetRepository extends JpaRepository <Bet, Long> {
    @Query("SELECT b FROM Bet b Join b.user u WHERE u.id  = :id")
    List<Bet> findAllByUserId(@Param("id") long id);
}
