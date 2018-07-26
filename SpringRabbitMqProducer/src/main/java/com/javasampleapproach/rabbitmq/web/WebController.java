package com.javasampleapproach.rabbitmq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.rabbitmq.producer.Producer;

@RestController
public class WebController {
	
	@Autowired
	Producer producer;
	
	int i = 0;
	
	@RequestMapping("/send/direct")
	public String sendDirectMsg(@RequestParam("msg")String msg){
		i = i+1;
		producer.produceDirectMsg(msg+"-"+i);
		return "Done";
	}
	
	
	/*@RequestMapping("/send/fanout")
	public String sendFanoutMsg(@RequestParam("msg")String msg){
		producer.produceFanoutMsg(msg);
		return "Done";
	}*/
	 
}
