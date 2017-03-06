package com.revature.services;

import java.util.List;

import com.revature.domain.Batch;
import com.revature.domain.Person;

public interface PersonLogic {

	List<Person> getAllByLastName(String lastName);
	List<Person> getAllByFirstName(String firstName);
	List<Person> getByFirstNameAndLastName(String firstName, String lastName);
	void createPerson(Person person, Batch batch);
	void updatePerson(Person person);
	void deletePerson(Person person);
	
	List<Person> findAllPeople();
	
	
}
