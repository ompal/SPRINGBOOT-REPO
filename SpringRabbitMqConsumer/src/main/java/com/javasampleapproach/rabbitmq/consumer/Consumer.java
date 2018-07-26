package com.javasampleapproach.rabbitmq.consumer;
 
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@RabbitListener(queues = "${jsa.rabbitmq.direct.queue1}")
	public void recievedMessageQueue1(String msg) {
		System.out.println("Direct Queue 1 Recieved Msg : " + msg);
	}
	
	@RabbitListener(queues = "${jsa.rabbitmq.direct.queue2}")
	public void recievedMessageQueue2(String msg) {
		System.out.println("Direct Queue 2 Recieved Msg : " + msg);
	}
	
	
	@RabbitListener(queues = "${jsa.rabbitmq.direct.queue3}")
	public void recievedMessageQueue3(String msg) {
		System.out.println("Direct Queue 3 Recieved Msg : " + msg);
	}

	// Fanout queue

	/*@RabbitListener(queues = "${jsa.rabbitmq.fanout.queue1}")
	public void recievedFanoutMessageQueue1(String msg) {
		System.out.println("Queue 1 Recieved Fanout Msg : " + msg);
	}

	@RabbitListener(queues = "${jsa.rabbitmq.fanout.queue2}")
	public void recievedFanoutMessageQueue2(String msg) {
		System.out.println("Queue 2 Recieved Fanout Msg : " + msg);
	}

	@RabbitListener(queues = "${jsa.rabbitmq.fanout.queue3}")
	public void recievedFanoutMessageQueue3(String msg) {
		System.out.println("Queue 3 Recieved Fanout Msg : " + msg);
	}
*/
	  
}
