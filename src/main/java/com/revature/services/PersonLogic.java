package com.revature.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.revature.domain.Person;
import com.revature.domain.PersonRole;

public interface PersonLogic {

	Page<Person> getByFirstNameAndLastName(Pageable pageable, String firstName, String lastName);

	Person createPerson(Person person);

	void deletePerson(Person person);
	Person updatePerson(Person p); 
	Page<Person> getAllPersons(Pageable pageable);
	Page<Person> getPersonByFirstName(Pageable pageable, String firstName);
	Page<Person> getPersonByLastName(Pageable pageable, String lastName);
	Person getPersonById(int id);
	Page<Person> getAllPersonsByPersonRole(Pageable pageable, PersonRole personRole);
	Page<Person> getPersonsByFirstnameAndPersonRole(Pageable pageable, String firstname, PersonRole personRole);
	Page<Person> getPersonsByLastnameAndPersonRole(Pageable pageable, String firstname, PersonRole personRole);
	Page<Person> getPersonsByFirstnameAndLastnameAndPersonRole(Pageable pageable, String firstname, String lastname, PersonRole personRole);
}
