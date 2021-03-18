package org.sid.curso_spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CursoSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
	}
	@Bean
	public CommandLineRunner initApp(){
		return args -> {
			//todo
		};
	}	
}
