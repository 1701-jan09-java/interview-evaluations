package com.revature.services;


import java.util.List;

import com.revature.domain.Person;

public interface PersonLogic {


	List<Person> getPersonByFirstName(String firstName);
	
	List<Person> getPersonByLastName(String lastName);

	
	Person getPersonById(int id);
	
	List<Person> getPersonsByPersonRole(int personRole);
	List<Person> getAllTrainees();
	List<Person> getAllTrainers();

}
