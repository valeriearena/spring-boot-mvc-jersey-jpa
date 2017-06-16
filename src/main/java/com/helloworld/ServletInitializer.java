package com.helloworld;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * ServletInitializer extends SpringBootServletInitializer
 * SpringBootServletInitializer implements WebApplicationInitializer
 *
 * How does Spring integrate with Servlet 3.x?
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
 *
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HelloWorldApplication.class);
	}

}
