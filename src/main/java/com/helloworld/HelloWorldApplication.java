package com.helloworld;

import com.helloworld.persistence.repository.HelloWorldGreetingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloWorldApplication{

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}

	@Bean
	CommandLineRunner findAll(HelloWorldGreetingRepository repo){
		return args ->{
			LOGGER.info("> Greetings in Database: ");
			repo.findAll().forEach(helloWorldGreetingEntity -> LOGGER.info(helloWorldGreetingEntity.toString()));
		};
	}

}
