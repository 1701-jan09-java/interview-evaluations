package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.domain.Person;
import com.revature.domain.PersonRepository;

public class PersonService {

	@Autowired 
	private PersonRepository repository;
	
	public void addPerson(Person person) {		
				
		repository.save(person);
		
	}
	/*
	public static void main(String[] args) {
		
//		PersonService test = new PersonService();
//		
//		Person person = new Person("Sharang", "Wakankar");
//
//		test.addPerson(person);
		
	}
	*/
}
