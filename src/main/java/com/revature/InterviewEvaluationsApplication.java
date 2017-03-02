package com.revature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.Bean;

import com.revature.domain.Person;
import com.revature.domain.PersonRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses=PersonRepository.class)
public class InterviewEvaluationsApplication {

	private static final Logger log = LoggerFactory.getLogger(InterviewEvaluationsApplication.class); 
	
	public static void main(String[] args) {
		SpringApplication.run(InterviewEvaluationsApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(PersonRepository repository) {
		return(args) -> {
			
			// save some persons
			repository.save(new Person("Jon"));
			repository.save(new Person("Shaun"));
			repository.save(new Person("Jen"));
			
			// fetch all persons
			log.info("Persons found with findAll():");
			log.info("-------------------------------");
			for (Person person : repository.findAll()) {
				log.info(person.toString());
			}
			log.info("");
			
			// fetch person by id
			Person person = repository.findOne(1);
			log.info("Person found with findOne(1):");
			log.info("--------------------------------");
			log.info(person.toString());
			log.info("");
			
			
			// fetch person by name
			log.info("Person found with findByName('Shaun'):");
			log.info("--------------------------------------------");
			for(Person shaun : repository.findByName("Shaun")) {
				log.info(shaun.toString());
			}
			log.info("");
		};
	}
}
