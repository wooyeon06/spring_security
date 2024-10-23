package com.example.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com.example.springsecurity"})
@SpringBootApplication()
public class SpringsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}

}
