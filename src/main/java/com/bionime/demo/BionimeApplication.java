package com.bionime.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.bionime.demo"," ","com.bionime.controller","com.bionime.service","com.bionime.exception","com.bionime.repository"})
@EntityScan({"com.bionime.demo","com.bionime.entity","com.bionime.controller","com.bionime.service","com.bionime.exception","com.bionime.repository"})
@EnableJpaRepositories("com.bionime.repository")
public class BionimeApplication {
	public static void main(String[] args) {
		SpringApplication.run(BionimeApplication.class, args);
	}
}
