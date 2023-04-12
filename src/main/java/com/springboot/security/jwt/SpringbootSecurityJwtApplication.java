package com.springboot.security.jwt;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.springboot.security.jwt.entity.User;
import com.springboot.security.jwt.repository.UserRepository;

@SpringBootApplication
@EnableWebSecurity
public class SpringbootSecurityJwtApplication {

	@Autowired
	private UserRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootSecurityJwtApplication.class, args);
	}

	@PostConstruct   // -> this will load all the users into db before spring start further processing
	public void initUsers() {
		List<User> users = Stream.of(
				new User(101, "apigee",      "apigee",     "api@google.com"),
				new User(102, "kafka",       "kafka",      "kafka@google.com"),
				new User(103, "java",        "java",       "java@google.com"),
				new User(104, "springboot",  "springboot", "springboot@google.com"),
				new User(105, "angular",     "angular",    "angular@google.com")
				).collect(Collectors.toList());
		
		repository.saveAll(users);
	}
}
