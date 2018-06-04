package pl.coderslab.bets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.bets.entity.Bet;

import java.util.List;

@Repository
public interface BetRepository extends JpaRepository <Bet, Long> {
//    @Query("SELECT b FROM bet b WHERE b.user_id  = :id")
    @Query(value = "SELECT * FROM bet WHERE user_id='id'", nativeQuery =true)
    List<Bet> findAllById(long id);
}
