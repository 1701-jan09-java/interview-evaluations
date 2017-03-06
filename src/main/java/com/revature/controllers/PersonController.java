package com.revature.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Person;
import com.revature.services.PersonLogic;

@RestController
@RequestMapping(value = "/api/v1/")
public class PersonController {
	
	@Autowired
	private PersonLogic personLogic;
	
	@RequestMapping(method = RequestMethod.GET, value = "persons/{id}")
	public ResponseEntity<Person> getPerson(@PathVariable("id") Integer id) {
		
		return ResponseEntity.ok(personLogic.getPersonById(id));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "trainees/")
	public ArrayList<Person> getTrainees() {
		
		return personLogic.getAllTrainees();

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "trainers/")
	public ArrayList<Person> getTrainers() {
		
		return personLogic.getAllTrainers();
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "trainers/{firstname}")
	public ArrayList<Person> getByFirstName(@PathVariable("firstname") String firstname) {
		
		return personLogic.getPersonByFirstName(firstname);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "trainers/{lastname}")
	public ArrayList<Person> getByLastName(@PathVariable("lastname") String lastname) {
		
		return personLogic.getPersonByFirstName(lastname);
		
	}

}
