package pl.coderslab.bets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.bets.entity.Message;
import pl.coderslab.bets.entity.User;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository <Message, Long> {
    List<Message> findAllByRecipient(User user);
}
