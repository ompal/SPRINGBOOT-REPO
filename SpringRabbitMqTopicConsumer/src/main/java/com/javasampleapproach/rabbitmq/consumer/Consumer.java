package com.javasampleapproach.rabbitmq.consumer;
 
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.javasampleapproach.rabbitmq.model.Log;


@Component
public class Consumer {
	
	@RabbitListener(queues="${jsa.rabbitmq.queue}", containerFactory="jsaFactory")
    public void recievedMessage(Log logs) {
        System.out.println("Recieved Message: " + logs);
    }
}