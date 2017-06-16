package com.helloworld;

import com.helloworld.resource.HelloWorldResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

/**
 * https://rterp.wordpress.com/2015/02/09/using-spring-boot-actuator-endpoints-and-jersey-web-services/
 */
@Configuration
@ApplicationPath("/jersey")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(HelloWorldResource.class);
    }
}