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
	private PersonRepository personRepository;
	
	@Override
	public List<Person> getPersonByFirstName(String firstName) {
		
		List<Person> persons = personRepository.findByFirstName(firstName);
		
		return persons;
	}

	@Override
	public List<Person> getPersonByLastName(String lastName) {
		
		List<Person> persons = personRepository.findByLastName(lastName);
		
		return persons;
	}
	
public static void main(String[] args) {
	PersonLogic pLog = new PersonLogicImpl();
	Person pers = (Person) pLog.getPersonByFirstName("Efren");
	System.out.println(pLog);
	System.out.println(pers);
}

}
