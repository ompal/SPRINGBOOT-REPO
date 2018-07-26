package com.javasampleapproach.rabbitmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${jsa.rabbitmq.exchange.direct}")
	private String exchangeDirect;
	
	/*@Value("${jsa.rabbitmq.exchange.fanout}")
	private String exchangeFanout;*/
	 
	
	@Value("${jsa.rabbitmq.routingkey}")
	private String routingKey; 
	
	public void produceDirectMsg(String msg){
		amqpTemplate.convertAndSend(exchangeDirect, routingKey, msg);
		System.out.println("Send Direct msg = " + msg);
	}
	
	
	/*public void produceFanoutMsg(String msg){
		amqpTemplate.convertAndSend(exchangeFanout, "", msg);
		System.out.println("Send Fanout msg = " + msg);
	}*/
	 
	 
}

