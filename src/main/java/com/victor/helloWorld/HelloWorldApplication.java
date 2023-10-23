package com.victor.helloWorld;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="*")
@EnableAutoConfiguration
@EnableConfigurationProperties
@SpringBootApplication
@ComponentScan(basePackages="com.victor.*")
public class HelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(HelloWorldApplication.class);
		Map<String,Object> map = new HashMap<>();
		map.put("server.port", "8090");
		map.put("server.servlet.context-path", "/");
		map.put("server.error.whitelabel.enabled", false);
		map.put("server.error.include-stacktrace", "never");
		app.setDefaultProperties(map);
		app.run(args);
	}

}
