package com.bionime.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.bionime.demo","com.bionime.model","com.bionime.controller","com.bionime.service","com.bionime.repository"})
@EntityScan({"com.bionime.demo","com.bionime.model","com.bionime.controller","com.bionime.service","com.bionime.repository"})
public class BionimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BionimeApplication.class, args);
	}

}
