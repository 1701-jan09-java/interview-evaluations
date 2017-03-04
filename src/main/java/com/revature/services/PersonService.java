package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.domain.Person;
import com.revature.domain.PersonRepository;

public class PersonService implements PersonLogic {

	@Autowired 
	private PersonRepository repository;

	@Override
	public List<Person> getAllByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getAllByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getByFirstNameAndLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createPerson(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePerson(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePerson(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Person> findAllPeople() {
		// TODO Auto-generated method stub
		return null;
	}
}
