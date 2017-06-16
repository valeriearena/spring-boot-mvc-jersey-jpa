package com.helloworld.resource;

import com.helloworld.properties.HelloWorldProperties;
import com.helloworld.representation.HelloWorldGreetingJaxb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by valerie on 6/16/17.
 */
@Component
@Path("/resource/helloworld")
public class HelloWorldResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldResource.class);

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

    @Autowired
    private CounterService counter;

    private static List<HelloWorldGreetingJaxb> greetings = new ArrayList<HelloWorldGreetingJaxb>();

    static  {
        greetings.add(new HelloWorldGreetingJaxb("Hello to Spring Boot","SpringBoot", "Valerie"));
        greetings.add(new HelloWorldGreetingJaxb("Hello to Spring 4","Spring4", "Val"));
        greetings.add(new HelloWorldGreetingJaxb("Hello to Everyone","Everyone", "Val"));
        greetings.add(new HelloWorldGreetingJaxb("Hello World","World", "Val"));
    }


    @GET
    @Path("/ping/{name}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String ping(@PathParam("name") String name) {

        counter.increment("counter.index.invoked");

        LOGGER.info("************** loggingLevelWeb={}",loggingLevelWeb);
        LOGGER.info("************** loggingLevelBoot={}",loggingLevelBoot);

        LOGGER.info("************** helloWorldName={}",helloWorldProperties.getName());
        LOGGER.info("************** helloWorldDescription={}",helloWorldProperties.getDescription());

        return "pong " + name;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HelloWorldGreetingJaxb> getAll(){
        return greetings;
    }


    @GET
    @Path("/findBy/from/{from}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HelloWorldGreetingJaxb> findByFrom(@PathParam("from") String from){
        return greetings
                .stream()
                .filter(greeting -> greeting.getFrom().equalsIgnoreCase(from))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/findBy/to/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HelloWorldGreetingJaxb> findByTo(@PathParam("to") String to){
        return greetings
                .stream()
                .filter(greeting -> greeting.getTo().equalsIgnoreCase(to))
                .collect(Collectors.toList());
    }

    public static List<HelloWorldGreetingJaxb> getGreetings() {
        return greetings;
    }
}
