package pl.coderslab.bets.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.bets.entity.Team;

import java.util.List;

@Repository
public interface TeamRepository  extends JpaRepository <Team, Long> {

    @Query(value = "SELECT * FROM team ORDER BY table_standing DESC", nativeQuery = true)
    List<Team> findAllOrderByTableStandingDesc();

    List<Team> findAllByInGameFalse();
}
