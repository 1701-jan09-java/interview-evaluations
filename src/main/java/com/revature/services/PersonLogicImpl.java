package com.revature.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.Person;
import com.revature.domain.PersonRepository;

@Service
@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
public class PersonLogicImpl implements PersonLogic {
	
	@Autowired
	private PersonRepository dao;

	@Override
	public Person getPersonById(int id) {
		
		Person person = dao.findOne(id);
		System.out.println(person);
		
		return person;
	}

	@Override
	public ArrayList<Person> getPersonsByPersonRole(int personRole) {
		
		ArrayList<Person> persons = dao.findAllByPersonRole(personRole);
		System.out.println(persons);
		
		return persons;
	}

	@Override
	public ArrayList<Person> getAllTrainees() {
		
		ArrayList<Person> personTrainees = dao.findAllByPersonRole(1);
		System.out.println(personTrainees);
		
		return personTrainees;
	}

	@Override
	public ArrayList<Person> getAllTrainers() {
		
		ArrayList<Person> personTrainers = dao.findAllByPersonRole(2);
		System.out.println(personTrainers);
		
		return personTrainers;
	}

}
