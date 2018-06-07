package pl.coderslab.bets.service.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.bets.service.MessageService;

import javax.jms.Message;
import javax.jms.MessageListener;

public class ConsumerService extends Thread {

    @Autowired
    MessageService messageService;

    @Override
    public void run(){
        while(true){
//            //odczytaj wiadomosci z kolejki, zapisz wiadomości uzytkownikom
           messageService.receiveFromTopic("newGame");
//            consumer.setMessageListener(myListener);
        }
    }
}
