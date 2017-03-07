package com.revature.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.Person;
import com.revature.log.IntEvalLogger;
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
	public Person getPersonById(int id)  {
		
		Person p = null;
		
		try {
			 
			p = dao.getOne(id);
			IntEvalLogger.LOGGER.info("This is my P: "+p);
			 
		 } catch (EntityNotFoundException e) {		
			 
			 p = new Person("Persondoes","Notexist",0);
			 p.setId(0);
			 IntEvalLogger.LOGGER.info("setting P to new person");
			 return p;
		 }
		 return p;
	}

	@Override
	public List<Person> getAllPersons() {
		return dao.findAll();
	}

	@Override
	public void savePerson(Person p) {
		dao.save(p);
	}

	@Override
	public Person updatePerson(Person p, String firstname, String lastname, int role) {	
		
		if (!"".equals(firstname)) {
			
			 IntEvalLogger.LOGGER.info("changing first to " + firstname);
			 p.setFirstName(firstname);
			 
		} else {
			
			IntEvalLogger.LOGGER.info("no change to first " + p.getFirstName());
			p.setFirstName(p.getFirstName());
		}
		
		
		
		if (!"".equals(lastname)) {
			
			 IntEvalLogger.LOGGER.info("changing last to " + lastname);
			 p.setLastName(lastname);
			 
		} else {
			
			IntEvalLogger.LOGGER.info("no change to last " + p.getLastName());
			p.setLastName(p.getLastName());
		}
		
		
	   
		if (role != 0) {
			
			IntEvalLogger.LOGGER.info("changing role to " + role);
			p.setPersonRole(role);
			 
		} else {
			
			IntEvalLogger.LOGGER.info("no change to role " + p.getPersonRole());
			p.setPersonRole(p.getPersonRole());
			
		}	    
		
		dao.save(p);
		
		return p;
	}

	@Override
	public void deletePerson(Person p) {
		
		dao.delete(p.getId());
	}



}
