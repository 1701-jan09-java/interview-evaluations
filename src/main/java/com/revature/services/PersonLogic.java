package com.revature.services;

import java.util.List;

import com.revature.domain.Batch;
import com.revature.domain.Person;
import com.revature.domain.PersonRole;

public interface PersonLogic {

	List<Person> getByFirstNameAndLastName(String firstName, String lastName);
	void createPerson(Person person, Batch batch);
	void deletePerson(Person person);
	List<Person> getAllPersons();
	List<Person> getPersonByFirstName(String firstName);
	List<Person> getPersonByLastName(String lastName);
	Person getPersonById(int id);
	Person updatePerson(Person p, String firstname, String lastname, PersonRole personRole, Batch batch);
	List<Person> getAllPersonsByPersonRole(PersonRole personRole);
	List<Person> getAllTrainees();
	List<Person> getAllTrainers();

}
