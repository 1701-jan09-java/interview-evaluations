package com.revature.services;


import com.revature.domain.Person;
import com.revature.domain.PersonRole;
import com.revature.repositories.PersonRepository;
import com.revature.validation.JsonValidation;
import com.revature.validation.exceptions.NotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
public class PersonLogicImpl implements PersonLogic {

	@Autowired
	private PersonRepository dao;
	
	@Autowired
    private JsonValidation validation;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Page<Person> getPersonByFirstName(Pageable pageable, String firstName) {
		
		return dao.findAllByFirstNameIgnoreCase(pageable, firstName);

	}

	@Override
	public Page<Person> getPersonByLastName(Pageable pageable, String lastName) {
		
		return dao.findAllByLastNameIgnoreCase(pageable, lastName);
	}

	@Override
	public Person getPersonById(int id)  {
		Person person = dao.findOne(id);
		
		if (person == null) {
            throw new NotFoundException("Person with id " + id + " does not exist");
        }
		
		return person;
	}

	@Override
	public Page<Person> getAllPersons(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public Person updatePerson(Person p) {
		Person updatedPerson = getPersonById(p.getId());
		if (p.getFirstName() != null) {
			updatedPerson.setFirstName(p.getFirstName());
		}
		if (p.getLastName() != null) {
			updatedPerson.setLastName(p.getLastName());
		}
		if (p.getPersonRole() != null) {
			validation.validatePersonRoleExists(p.getPersonRole().getId());
			updatedPerson.setPersonRole(p.getPersonRole());
		}
		dao.saveAndFlush(updatedPerson);
		entityManager.refresh(updatedPerson);
		return updatedPerson;
	}
	


	@Override
	public void deletePerson(Person p) {
		dao.delete(p.getId());
	}

	@Override
	public Page<Person> getByFirstNameAndLastName(Pageable pageable, String firstName, String lastName) {
		return dao.findAllByFirstNameAndLastNameAllIgnoreCase(pageable, firstName, lastName);
	}

	@Override
	public Person createPerson(Person person) {
		validation.validateIdNotSpecified(person.getId());
		validation.validatePersonFields(person);				
		dao.saveAndFlush(person);
		entityManager.refresh(person);
		return person;
	}

	@Override
	public Page<Person> getAllPersonsByPersonRole(Pageable pageable, PersonRole personRole) {
		
		validation.validatePersonRole(personRole);	
		return dao.findAllByPersonRole(pageable, personRole);
	}

	@Override
	public Page<Person> getPersonsByFirstnameAndPersonRole(Pageable pageable, String firstname, PersonRole personRole) {
		return dao.findAllByFirstNameIgnoreCaseAndPersonRole(pageable, firstname, personRole);
	}

	@Override
	public Page<Person> getPersonsByLastnameAndPersonRole(Pageable pageable, String lastname, PersonRole personRole) {
		return dao.findAllByLastNameIgnoreCaseAndPersonRole(pageable, lastname, personRole);
	}

	@Override
	public Page<Person> getPersonsByFirstnameAndLastnameAndPersonRole(Pageable pageable, String firstname,
			String lastname, PersonRole personRole) {
		return dao.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndPersonRole(pageable, firstname, lastname, personRole);
	}

}
