package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

//	@Value("${management.security.enabled}")
//	private Boolean securityEnabled;
//
//	@Value("${endpoints.health.sensitive}")
//	private Boolean healthSenisitve;
//
//	@Value("${logging.level.org.springframework.web}")
//	private String loggingLevelWeb;
//
//	@Value("${logging.level.org.springframework.boot}")
//	private String loggingLevelBoot;


	@RequestMapping("/hello")
	public String rest(){
		return "Hello World";
	}
}
