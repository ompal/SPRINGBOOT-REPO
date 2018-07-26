package com.javasampleapproach.rabbitmq.web;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.rabbitmq.producer.Producer;

@RestController
public class WebController {
	
	@Autowired
	Producer producer;
	 
	
	@RequestMapping("/send/header")
	public String sendTopicMsg(){
		 
		/**
		 *  message 1
		 *  
		 *  This message will be delivered to 2 queues
		 *  {
		 *  	jsa.queue.logs.all-sys-error
		 *  	jsa.queue.logs.any-app-error
		 *  }
		 */
		String systemErrorLog = "2017-10-10 10:57:51.10 ERROR in ch.qos.logback.core.joran.spi.Interpreter@4:71 - no applicable action for [springProperty], current ElementPath is [[configuration][springProperty]]";
		Message sysErrMsg = MessageBuilder.withBody(systemErrorLog.getBytes())
											.setHeader("layer", "system")
											.setHeader("level", "error")
											.build();
		producer.produce(sysErrMsg);
		
		/**
		 *  message 2
		 *  
		 *  This message will be delivered to one queue: jsa.queue.logs.any-app-error
		 */
		String appErrorLog = "2017-10-10 10:57:51.112  ERROR java.lang.Exception: java.lang.Exception";
		Message appErrMsg = MessageBuilder.withBody(appErrorLog.getBytes())
											.setHeader("layer", "application")
											.setHeader("level", "error")
											.build();
		producer.produce(appErrMsg);
		
		/**
		 *  message 3
		 *  
		 *  => This message will be discarded because it does NOT match with critical conditions of 2 queues 
		 *  {
		 *  	jsa.queue.logs.all-sys-error
		 *  	jsa.queue.logs.any-app-error
		 *  }
		 */
		String sysInfoLog = "2014-03-05 10:58:51.1  INFO 45469 --- [main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/7.0.52";
		Message sysInfoMsg = MessageBuilder.withBody(sysInfoLog.getBytes())
											.setHeader("layer", "system")
											.setHeader("level", "info")
											.build();
		producer.produce(sysInfoMsg);
		return "Done !";
	}
	 
}
