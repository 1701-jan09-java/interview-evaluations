package com.revature.services;

import java.util.ArrayList;

import com.revature.domain.Person;

public interface PersonLogic {
	
	Person getPersonById(int id);
	
	ArrayList<Person> getPersonsByPersonRole(int personRole);
	ArrayList<Person> getAllTrainees();
	ArrayList<Person> getAllTrainers();

}
