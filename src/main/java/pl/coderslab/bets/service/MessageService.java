package pl.coderslab.bets.service;

import pl.coderslab.bets.entity.Message;
import pl.coderslab.bets.entity.User;

import java.util.List;

public interface MessageService {
    void saveSimpleMessage(Message message);

    List<Message> findAllUserMessages(User user);

    void sendPointToPointMessage(String queueName, String msg);

    String receive(String queueName);

    void publishMessage(String topicName, String msg);

    public String receiveFromTopic(String topicName);

}
