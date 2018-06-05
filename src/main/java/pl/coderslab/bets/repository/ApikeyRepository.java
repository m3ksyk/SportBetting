package pl.coderslab.bets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.bets.entity.Apikey;

@Repository
public interface ApikeyRepository extends JpaRepository<Apikey, Long> {
    Apikey findByValueEquals(String value);
}
