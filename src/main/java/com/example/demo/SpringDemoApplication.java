package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
@RestController
public class SpringDemoApplication {

	private final AtomicLong counter = new AtomicLong();

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

	/*Static text response*/
	@GetMapping("/ping")
	public String pong(){
		return "pong";
	}

	/*Dynamic text response*/
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
		return String.format("Hello %s!", name);
	}

	/*JSON Response*/
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format("Hello, %s!", name));
	}
}
