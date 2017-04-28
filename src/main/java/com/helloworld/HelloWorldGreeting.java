package com.helloworld;

/**
 * Created by valerie on 4/28/17.
 */
public class HelloWorldGreeting {

    private String greeting;
    private String to;
    private String from;

    public HelloWorldGreeting(String greeting, String to, String from) {
        this.greeting = greeting;
        this.to = to;
        this.from = from;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "HelloWorldGreeting{" +
                "greeting='" + greeting + '\'' +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
