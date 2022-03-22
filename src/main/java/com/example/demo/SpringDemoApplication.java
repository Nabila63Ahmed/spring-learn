package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
@RestController
public class SpringDemoApplication {

	private final AtomicLong idCounter = new AtomicLong();
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
		return new Greeting(idCounter.incrementAndGet(), String.format("Hello, %s!", name));
	}

	/*Increment counter*/
	@GetMapping("/counter")
	public long incrementCounter(@RequestParam(value = "increment", defaultValue = "1") int increment) {
		return counter.addAndGet(increment);
	}

	/*Post Request: Body automatically type-casted*/
	@PostMapping("/postGreeting")
	public Greeting postGreeting(@RequestBody Greeting greeting) {
		System.out.print(greeting.getContent());
		return greeting;
	}

	/*Post Request: List in body, choose an item and greet with*/
	@PostMapping("/postGreetings")
	public String postGreetings(@RequestParam(value = "index", defaultValue = "0") int index, @RequestBody Greeting[] greetings) {
		String name = greetings!=null & index < greetings.length? greetings[index].getContent(): "World";
		return String.format("Hello %s!", name);
	}
}
