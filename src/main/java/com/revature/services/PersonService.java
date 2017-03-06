package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.domain.Batch;
import com.revature.domain.Person;
import com.revature.domain.PersonBatchJoin;
import com.revature.repositories.PersonRepository;

public class PersonService implements PersonLogic {

	@Autowired 
	private PersonRepository repository;
	private PersonBatchLogic personBatchLogic;

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
	public void createPerson(Person person, Batch batch) {
		if(person.getPersonRole().getId() == 1){
			PersonBatchJoin personBatchJoin = new PersonBatchJoin(person, batch);
			personBatchLogic.createPersonBatchEntry(personBatchJoin);
		}
		repository.save(person);
		
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
