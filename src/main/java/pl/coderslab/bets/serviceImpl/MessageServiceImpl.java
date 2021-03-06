package pl.coderslab.bets.serviceImpl;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.Message;
import pl.coderslab.bets.entity.User;
import pl.coderslab.bets.repository.MessageRepository;
import pl.coderslab.bets.service.MessageService;
import pl.coderslab.bets.service.jms.Subscriber;

import javax.jms.*;
import java.io.Serializable;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    MessageRepository messageRepository;

    Subscriber subscriber;

    @Override
    public List<Message> findAllUserMessages(User user) {
        return messageRepository.findAllByRecipient(user);
    }


    @Override
    public void saveSimpleMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public void sendPointToPointMessage(String queueName, String msg) {
        jmsTemplate.convertAndSend(queueName, msg);
    }

    @Override
    public String receive(String queueName) {

        return String.valueOf(jmsTemplate.receiveAndConvert(queueName));
    }

    public String receiveFromTopic(String topicName) {
        try {
            subscriber.getMessage(5000);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        String messageStr = "";
        final javax.jms.Message message = jmsTemplate.receive();
        try {

            messageStr = ((TextMessage) message).getText();

        } catch (JMSException e) {
            e.printStackTrace();
        }
        return messageStr;
    }

    @Override
    public void publishMessage(String topicName, String msg) {
        final boolean pubSubDomain = jmsTemplate.isPubSubDomain();
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.convertAndSend(topicName, msg);
        jmsTemplate.setPubSubDomain(pubSubDomain);
    }


}
