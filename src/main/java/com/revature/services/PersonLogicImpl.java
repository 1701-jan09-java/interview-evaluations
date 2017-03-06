package com.revature.services;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.Person;
import com.revature.repositories.PersonRepository;


@Service
@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
public class PersonLogicImpl implements PersonLogic {


	@Autowired
	private PersonRepository dao;
	
	@Override
	public List<Person> getPersonByFirstName(String firstName) {
		
		return  dao.findByFirstName(firstName);

	}

	@Override
	public List<Person> getPersonByLastName(String lastName) {
		
		return dao.findByLastName(lastName);
	}
	


	@Override
	public List<Person> getPersonsByPersonRole(int personRole) {
		
		return dao.findByPersonRole(personRole);
	
	}

	@Override
	public List<Person> getAllTrainees() {
		
		return dao.findByPersonRole(1);

	}

	@Override
	public List<Person> getAllTrainers() {
		
		return dao.findByPersonRole(2);

	}

	@Override
	public Person getPersonById(int id) {
		return dao.getOne(id);
	}

}
