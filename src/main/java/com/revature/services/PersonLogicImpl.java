package com.revature.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.Batch;
import com.revature.domain.Person;
import com.revature.domain.PersonRole;
import com.revature.log.IntEvalLogger;
import com.revature.repositories.PersonRepository;


@Service
@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
public class PersonLogicImpl implements PersonLogic {

	@Autowired
	private PersonRepository dao;
	
	@Override
	public Page<Person> getPersonByFirstName(Pageable pageable, String firstName) {
		
		return dao.findAllByFirstNameIgnoreCase(pageable, firstName);

	}

	@Override
	public Page<Person> getPersonByLastName(Pageable pageable, String lastName) {
		
		return dao.findAllByLastNameIgnoreCase(pageable, lastName);
	}

	@Override
	public Page<Person> getAllTrainees(Pageable pageable) {
		PersonRole personRole = new PersonRole(1, "Trainee");
		return dao.findAllByPersonRole(pageable, personRole);

	}

	@Override
	public Page<Person> getAllTrainers(Pageable pageable) {
		PersonRole personRole = new PersonRole(2, "Trainer");
		return dao.findAllByPersonRole(pageable, personRole);

	}

	@Override
	public Person getPersonById(int id)  {
		
		BasicConfigurator.configure();
		
		Person p = null;
		
		try {
			 
			p = dao.getOne(id);
			IntEvalLogger.LOGGER.info("This is my P: "+p);
			 
		 } catch (EntityNotFoundException e) {		
			 PersonRole newPersonRole = new PersonRole(0, "Not a role");
			 p = new Person("Persondoes","Notexist", newPersonRole);
			 p.setId(0);
			 IntEvalLogger.LOGGER.info("setting P to new person");
			 return p;
		 }
		 return p;
	}

	@Override
	public Page<Person> getAllPersons(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public Person updatePerson(Person p) {
		dao.save(p);
		return p;
	}
	


	@Override
	public void deletePerson(Person p) {
		dao.delete(p.getId());
	}

	@Override
	public Page<Person> getByFirstNameAndLastName(Pageable pageable, String firstName, String lastName) {
		return dao.findAllByFirstNameAndLastNameIgnoreCase(pageable, firstName, lastName);
	}

	@Override

	public Person createPerson(Person person) {

		dao.save(person);
		return person;
	}

	@Override
	public Page<Person> getAllPersonsByPersonRole(Pageable pageable, PersonRole personRole) {
		return dao.findAllByPersonRole(pageable, personRole);
	}

}
