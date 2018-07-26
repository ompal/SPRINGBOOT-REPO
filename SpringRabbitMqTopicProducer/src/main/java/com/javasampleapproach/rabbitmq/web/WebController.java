package com.javasampleapproach.rabbitmq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.rabbitmq.model.Log;
import com.javasampleapproach.rabbitmq.producer.Producer;

@RestController
public class WebController {
	
	@Autowired
	Producer producer;
	 
	
	@RequestMapping("/send/topic")
	public String sendTopicMsg(){
		/**
		 *  1
		 */
		String content = "2014-03-05 10:58:51.1  INFO 45469 --- [main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/7.0.52";
		String routingKey = "sys.dev.info";
		
		// send to RabbitMQ
		producer.produce(new Log(content, routingKey));
		
		/**
		 *  2
		 */
		content = "2017-10-10 10:57:51.10 ERROR in ch.qos.logback.core.joran.spi.Interpreter@4:71 - no applicable action for [springProperty], current ElementPath is [[configuration][springProperty]]";
		routingKey = "sys.test.error";
		
		// send to RabbitMQ
		producer.produce(new Log(content, routingKey));
		
		/**
		 *  3
		 */
		content = "2017-10-10 10:57:51.112  ERROR java.lang.Exception: java.lang.Exception";
		routingKey = "app.prod.error";
		
		// send to RabbitMQ
		producer.produce(new Log(content, routingKey));
		return "Done !";
	}
	 
}
