package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Person;
import com.revature.log.IntEvalLogger;
import com.revature.services.PersonLogic;

@RestController
@RequestMapping(value = "/api/v1/")
public class PersonController {
	
	@Autowired
	private PersonLogic personLogic;
	
	@RequestMapping(method = RequestMethod.GET, value = "persons")
	public ResponseEntity<List<Person>> getPerson(@RequestParam(defaultValue="", required=false) String firstname,
			@RequestParam(defaultValue="", required=false) String lastname, 
			@RequestParam(defaultValue="0", required=false) Integer id,
			@RequestParam(defaultValue="0", required=false) Integer role){
		
		 if((id==0)&&(role==0)){
			 			 
			 if(!"".equals(firstname)){
				 return ResponseEntity.ok(personLogic.getPersonByFirstName(firstname));
				 
			 } else if (!"".equals(lastname)){
				 return ResponseEntity.ok(personLogic.getPersonByLastName(lastname));
				 
			 } else{
				 return  ResponseEntity.ok(personLogic.getAllPersons());
			 }
			 
		 } else if (id==0 && role!=0){
			 if(role==1){
				
					return ResponseEntity.ok(personLogic.getAllTrainees());
					
					} else if (role == 2) {
						return ResponseEntity.ok(personLogic.getAllTrainers());
						
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
			@RequestParam(required=true) Integer role){
		
			Person person = new Person(firstname, lastname, role);
			
			personLogic.savePerson(person);

			return ResponseEntity.ok(person);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "persons")
	public ResponseEntity<Person> modifyPerson(@RequestParam(required=true) Integer id,
			@RequestParam(defaultValue="", required=false) String firstname,
			@RequestParam(defaultValue="", required=false) String lastname, 
			@RequestParam(defaultValue="0", required=false) Integer role){
	
			Person person = personLogic.getPersonById(id);
			
			IntEvalLogger.LOGGER.info("first " + firstname + " last " + lastname + " role " + role);
			
			person = personLogic.updatePerson(person, firstname, lastname, role);

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
