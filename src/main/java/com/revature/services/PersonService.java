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
	
	@Autowired
	private PersonBatchLogic personBatchLogic;

	@Override
	public List<Person> getAllByLastName(String lastName) {
		return repository.findAllByLastName(lastName);
	}

	@Override
	public List<Person> getAllByFirstName(String firstName) {
		return repository.findAllByFirstName(firstName);
	}

	@Override
	public List<Person> getByFirstNameAndLastName(String firstName, String lastName) {
		return repository.findAllByFirstNameAndLastName(firstName, lastName);
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
		repository.save(person);
	}

	@Override
	public void deletePerson(Person person) {
		repository.delete(person);
	}

	@Override
	public List<Person> findAllPeople() {
		return repository.findAll();
	}
}
