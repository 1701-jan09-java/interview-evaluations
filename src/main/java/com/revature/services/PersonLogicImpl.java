package com.revature.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.Person;
import com.revature.domain.PersonRole;
import com.revature.repositories.PersonRepository;
import com.revature.repositories.PersonRoleRepository;


@Service
@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
public class PersonLogicImpl implements PersonLogic {

	@Autowired
	private PersonRepository dao;
	
	@Autowired
	private PersonRoleRepository daoRole;
	
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
		
		Person p = null;
		
		try {
			
			p = dao.findOne(id);
			
		} catch (EntityNotFoundException e) {
			
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
		
		List<Integer> roleIds = new ArrayList<>();
		List<PersonRole> roleList = daoRole.findAll();
		System.out.println(roleList);
				
		Iterator<PersonRole> iterator = roleList.iterator();
		
		while(iterator.hasNext()) {
			PersonRole role = iterator.next();
			roleIds.add(role.getId());
		}
		
		System.out.println(roleIds);
		
		
		if (person.getFirstName() == null) {
			throw new ConstraintViolationException("Missing required field firstName (String)", null);
		}
		
		if (person.getLastName() == null) {
			throw new ConstraintViolationException("Missing required field lastName (String)", null);
		}
		
		boolean isValid = false;
		

		for (Integer roll : roleIds) {
				
			if (person.getPersonRole().getId() == roll) {
					
				isValid = true;
			} 
				
		}	
		
		if (!isValid) {
			
			throw new ConstraintViolationException("Invalid field personRole (PersonRole)", null);
		}		
				
		dao.save(person);
		return person;
	}

	@Override
	public Page<Person> getAllPersonsByPersonRole(Pageable pageable, PersonRole personRole) {
		return dao.findAllByPersonRole(pageable, personRole);
	}

}
