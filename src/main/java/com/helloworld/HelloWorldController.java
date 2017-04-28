package com.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HelloWorldController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

	@Value("${logging.level.org.springframework.web}")
	private String loggingLevelWeb;

	@Value("${logging.level.org.springframework.boot}")
	private String loggingLevelBoot;

	@Value("${helloworld.name}")
	private String helloWorldName;

	@Value("${helloworld.description}")
	private String helloWorldDescription;

	@Autowired
	private HelloWorldProperties helloWorldProperties;

	private static List<HelloWorldGreeting> greetings = new ArrayList<HelloWorldGreeting>();

	static  {
		greetings.add(new HelloWorldGreeting("Hello to Spring Boot","Spring Boot", "Val"));
		greetings.add(new HelloWorldGreeting("Hello to Spring 4","Spring 4", "Val"));
		greetings.add(new HelloWorldGreeting("Hello to Everyone","Everyone", "Val"));
		greetings.add(new HelloWorldGreeting("Hello World","World", "Val"));
	}



	@RequestMapping("/helloworld")
	public String rest(){

		LOGGER.info("************** loggingLevelWeb={}",loggingLevelWeb);
		LOGGER.info("************** loggingLevelBoot={}",loggingLevelBoot);


		LOGGER.info("************** helloWorldName={}",helloWorldProperties.getName());
		LOGGER.info("************** helloWorldDescription={}",helloWorldProperties.getDescription());

		return "Hello World!!!";
	}

	@RequestMapping("/helloworld/all")
	public List<HelloWorldGreeting> getAll(){
		return greetings;
	}


	@RequestMapping("/helloworld/findBy/from/{from}")
	public List<HelloWorldGreeting> findByFrom(@PathVariable String from){
		return greetings
				.stream()
				.filter(greeting -> greeting.getFrom().equalsIgnoreCase(from))
				.collect(Collectors.toList());
	}

	@RequestMapping("/helloworld/findBy/to/{to}")
	public List<HelloWorldGreeting> findByTo(@PathVariable String to){
		return greetings
				.stream()
				.filter(greeting -> greeting.getTo().equalsIgnoreCase(to))
				.collect(Collectors.toList());
	}

	public static List<HelloWorldGreeting> getGreetings() {
		return greetings;
	}
}
