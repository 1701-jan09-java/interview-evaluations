package com.revature.services;

import java.util.List;

import com.revature.domain.Person;

public interface PersonLogic {

	List<Person> getPersonByFirstName(String firstName);
	
	List<Person> getPersonByLastName(String lastName);
	
	
}
