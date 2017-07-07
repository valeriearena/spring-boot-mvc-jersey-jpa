package com.helloworld;

import com.helloworld.persistence.repository.HelloWorldGreetingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * SpringInitializer extends SpringBootServletInitializer
 * SpringBootServletInitializer implements WebApplicationInitializer
 *
 * NOTE: WebApplicationInitializer & ResourceConfig are both automatically detected
 * 		and are the key to how Servlet 3.x containers automcatically register services.
 *
 * How does Spring integrate with Servlet 3.x (Tomcat 7 & 8)?
 * Implementations of WebApplicationInitializer will be detected automatically
 * by SpringServletContainerInitializer, which itself is bootstrapped automatically
 * by any Servlet 3.0 container because
 * SpringServletContainerInitializer implements ServletContainerInitializer.
 *
 * SpringServletContainerInitializer will be loaded and instantiated and
 * have its onStartup(java.util.Set<java.lang.Class<?>>, javax.servlet.ServletContext) method
 * invoked by any Servlet 3.0-compliant container during container startup
 * assuming that the spring-web module JAR is present on the classpath.
 * This occurs by detecting the spring-web module's
 * META-INF/services/javax.servlet.ServletContainerInitializer service provider configuration file.
 *
 * When Servlet 3.0-compliant container starts up, it scans jars for
 * META-INF/services/javax.servlet.ServletContainerInitializer​.
 * When the container finds the file, it reads the path, instantiates the implementation,
 * and invokes the onStartup method.
 *
 *
 * See its SpringServletContainerInitializer Javadoc for details on this bootstrapping mechanism.
 */
@SpringBootApplication
public class SpringInitializer extends SpringBootServletInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringInitializer.class);

	@Profile("dev")
	@Bean
	CommandLineRunner findAll(HelloWorldGreetingRepository repo){
		return args ->{
			LOGGER.info("> Greetings in Database: ");
			repo.findAll().forEach(helloWorldGreetingEntity -> LOGGER.info(helloWorldGreetingEntity.toString()));
		};
	}

	//The configure method inherited from SpringBootServletInitializer initializes an application context but only when deployed as a war file.
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringInitializer.class);
	}

	/*
	The main method initializes an application context via a factory method inside SpringApplication when run with embedded container.
	public static void main(String[] args) {
		SpringApplication.run(SpringInitializer.class, args);
	}
	*/




}
