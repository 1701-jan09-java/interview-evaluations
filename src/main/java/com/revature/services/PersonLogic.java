package com.revature.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.revature.domain.Batch;
import com.revature.domain.Person;
import com.revature.domain.PersonRole;

public interface PersonLogic {

	Page<Person> getByFirstNameAndLastName(Pageable pageable, String firstName, String lastName);
	void createPerson(Person person);
	void deletePerson(Person person);
	Page<Person> getAllPersons(Pageable pageable);
	Page<Person> getPersonByFirstName(Pageable pageable, String firstName);
	Page<Person> getPersonByLastName(Pageable pageable, String lastName);
	Person getPersonById(int id);
	Person updatePerson(Person p, String firstname, String lastname, PersonRole personRole, Batch batch);
	Page<Person> getAllPersonsByPersonRole(Pageable pageable, PersonRole personRole);
	Page<Person> getAllTrainees(Pageable pageable);
	Page<Person> getAllTrainers(Pageable pageable);

}
