package com.helloworld;

import com.helloworld.resource.HelloWorldResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

/**
 * JerseyConfig extends ResourceConfig
 * ResourceConfig extends javax.ws.rs.core.Application
 *
 * NOTE: WebApplicationInitializer & ResourceConfig are both automatically detected
 * 		and are the key to how Servlet 3.x containers automcatically register services.
 *
 * How does Jersey integrate with Servlet 3.x (Tomcat 7 & 8)?
 * Implementations of ResourceConfig will be detected automatically
 * by JerseyServletContainerInitializer, which itself is bootstrapped automatically
 * by any Servlet 3.0 container because
 * JerseyServletContainerInitializer implements ServletContainerInitializer.
 *
 * JerseyServletContainerInitializer will be loaded and instantiated and
 * have its onStartup(java.util.Set<java.lang.Class<?>>, javax.servlet.ServletContext) method
 * invoked by any Servlet 3.0-compliant container during container startup
 * assuming that the jersey-container-servlet module JAR is present on the classpath.
 * This occurs by detecting the jersey-container-servlet module's
 * META-INF/services/javax.servlet.ServletContainerInitializer service provider configuration file.
 *
 * When Servlet 3.0-compliant container starts up, it scans jars for
 * META-INF/services/javax.servlet.ServletContainerInitializer​.
 * When the container finds the file, it reads the path, instantiates the implementation,
 * and invokes the onStartup method.
 * onStartup dynamically create an instance of org.glassfish.jersey.servlet.ServletContainer
 * by passing ResourceConfig to the constructor. ServletContainer is then added it to the ServletContext.
 *
 * Jersey application will take over all URLS at the root, thus masking
 * the Spring Boot actuator endpoints, even though the application itself is not using that mapping.
 * See the following for configuring Jersey with Spring Boot
 * https://rterp.wordpress.com/2015/02/09/using-spring-boot-actuator-endpoints-and-jersey-web-services/
 */

//@Configuration classes are typically bootstrapped using
// AnnotationConfigApplicationContext which registers annotated classes.
// (aka JavaConfig)
@Configuration
@ApplicationPath("/jersey")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(HelloWorldResource.class);
    }
}