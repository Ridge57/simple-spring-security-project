package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FullAutoApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(FullAutoApplication.class, args);
	}

}

