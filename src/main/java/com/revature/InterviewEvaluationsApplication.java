package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.revature.domain.PersonRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses=PersonRepository.class)
public class InterviewEvaluationsApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(InterviewEvaluationsApplication.class, args);
	}
	
}
