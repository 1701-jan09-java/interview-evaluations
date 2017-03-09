package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Batch;
import com.revature.domain.Person;
import com.revature.domain.PersonRole;
import com.revature.services.BatchLogic;
import com.revature.services.PersonLogic;
import com.revature.services.PersonRoleLogic;

@CrossOrigin(origins = "http://localhost:8080/api/v1/persons", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/")
public class PersonController {
	
	@Autowired
	private PersonLogic personLogic;
	
	@Autowired
	private PersonRoleLogic personRoleLogic;
	
	@Autowired
	private BatchLogic batchLogic;
		
	@RequestMapping(method = RequestMethod.GET, value = "persons")
	public ResponseEntity<Page<Person>> getPerson(Pageable pageable, @RequestParam(defaultValue="", required=false) String firstname,
			@RequestParam(defaultValue="", required=false) String lastname, 
			@RequestParam(defaultValue="0", required=false) Integer id,
			@RequestParam(defaultValue="0", required=false) Integer role){
		
		 if((id==0)&&(role==0)){
			 			 
			 if(!"".equals(firstname)){
				 return ResponseEntity.ok(personLogic.getPersonByFirstName(pageable, firstname));
				 
			 } else if (!"".equals(lastname)){
				 return ResponseEntity.ok(personLogic.getPersonByLastName(pageable, lastname));
				 
			 } else{
				 return  ResponseEntity.ok(personLogic.getAllPersons(pageable));
			 }
			 
		 } else if (id==0 && role!=0){
			 if(role==1){
				
					return ResponseEntity.ok(personLogic.getAllTrainees(pageable));
					
					} else if (role == 2) {
						return ResponseEntity.ok(personLogic.getAllTrainers(pageable));
						
					} else {
						return ResponseEntity.ok(null);
					}
		 }
		 
		 // search for person by id regardless if entered role is incorrect
		 else{
			 
			 List<Person> singlePerson = new ArrayList<>();
			 
			 singlePerson.add(personLogic.getPersonById(id));
			
			 return ResponseEntity.ok(singlePerson);
			 
		 }
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "persons")
	public ResponseEntity<Person> createPerson(@RequestParam(required=true) String firstname,
			@RequestParam(required=true) String lastname, 
			@RequestParam(required=true) Integer role,
			@RequestParam(defaultValue="0", required=false) Integer batchId){
			
			Batch batch = batchLogic.getBatchById(batchId);
			
			PersonRole pRole = personRoleLogic.findRoleById(role);
			Person person = new Person(firstname, lastname, pRole);
			
			personLogic.createPerson(person, batch);

			return ResponseEntity.ok(person);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "persons")
	public ResponseEntity<Person> modifyPerson(@RequestParam(required=true) Integer id,
			@RequestParam(defaultValue="", required=false) String firstname,
			@RequestParam(defaultValue="", required=false) String lastname, 
			@RequestParam(defaultValue="0", required=false) Integer role,
			@RequestParam(defaultValue="0", required=false) Integer batchId){
			
			Batch batch = batchLogic.getBatchById(batchId);
			
			Person person = personLogic.getPersonById(id);
			PersonRole pRole = personRoleLogic.findRoleById(role);
			
			person = personLogic.updatePerson(person, firstname, lastname, pRole, batch);

			return ResponseEntity.ok(person);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "persons")
	public ResponseEntity<String> deletePerson(int pId){
		
			Person pers = personLogic.getPersonById(pId);
			String message = pers.getFirstName() + " " + pers.getLastName() + " was deleted.";
			personLogic.deletePerson(pers);
			return ResponseEntity.ok(message);
	}
	
	
	

}
