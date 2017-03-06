package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.revature.repositories.PersonRepository;

@EnableJpaRepositories(basePackageClasses=PersonRepository.class)
@SpringBootApplication
public class InterviewEvaluationsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(InterviewEvaluationsApplication.class, args);
		
	}
	
}
