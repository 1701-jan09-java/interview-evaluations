package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.domain.Person;
import com.revature.repositories.PersonRepository;

public interface PersonLogic {

	List<Person> getPersonByFirstName(String firstName);
	
	List<Person> getPersonByLastName(String lastName);
	
	
}
