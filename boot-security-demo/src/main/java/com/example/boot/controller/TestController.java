package com.example.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/index")
	public String test() {
		return "Hello World!";
	}
	
	
	@GetMapping("/user") 
	public String user() {
		return "Hello user!";
	}
	
	@GetMapping("/hi") 
	public String hi() {
		return "hi user!";
	}
}
