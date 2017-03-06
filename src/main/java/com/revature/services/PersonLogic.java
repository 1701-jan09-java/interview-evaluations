package com.revature.services;


import java.util.ArrayList;

import com.revature.domain.Person;

public interface PersonLogic {


	ArrayList<Person> getPersonByFirstName(String firstName);
	
	ArrayList<Person> getPersonByLastName(String lastName);

	
	Person getPersonById(int id);
	
	ArrayList<Person> getPersonsByPersonRole(int personRole);
	ArrayList<Person> getAllTrainees();
	ArrayList<Person> getAllTrainers();

}
