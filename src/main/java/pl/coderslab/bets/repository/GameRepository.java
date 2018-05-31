package pl.coderslab.bets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.bets.entity.Game;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface GameRepository  extends JpaRepository <Game, Long> {

    List<Game> findAllByStatusEquals(String status);

    List<Game> findAllByEndAfterAndStatusEquals(Timestamp timestamp, String status);
    List<Game> findAllByStartAfterAndStatusEquals(Timestamp timestamp, String status);
}
