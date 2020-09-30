package com.qa.demo;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class FirstApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstApiApplication.class, args);
	}

}
