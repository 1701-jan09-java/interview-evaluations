package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.revature.domain.Batch;
import com.revature.domain.Person;
import com.revature.domain.PersonRole;
import com.revature.services.BatchLogic;
import com.revature.services.PersonLogic;
import com.revature.services.PersonRoleLogic;

@RestController
@RequestMapping(value = "/api/v1/")
public class PersonController {
	
	@Autowired
	private PersonLogic personLogic;
	
	@Autowired
	private PersonRoleLogic personRoleLogic;
	
	@Autowired
	private BatchLogic batchLogic;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "persons/{id}")
		public ResponseEntity<Person> getPersonById(@PathVariable("id") Integer id ){

			return ResponseEntity.ok(personLogic.getPersonById(id));
	}
		
	
	@RequestMapping(method = RequestMethod.GET, value = "persons")
	public ResponseEntity<Page<Person>> getPerson(Pageable pageable, @RequestParam(defaultValue="", required=false) String firstname,
			@RequestParam(defaultValue="", required=false) String lastname,
			@RequestParam(defaultValue="0", required=false) Integer role){
		
		 if(role==0){
			 			 
			 if(!"".equals(firstname)){
				 return ResponseEntity.ok(personLogic.getPersonByFirstName(pageable, firstname));
				 
			 } else if (!("".equals(lastname))){
				 return ResponseEntity.ok(personLogic.getPersonByLastName(pageable, lastname));
				 
			 } else{
				 return  ResponseEntity.ok(personLogic.getAllPersons(pageable));
			 }
			 
		 } else {
			 if(role==1){
				
					return ResponseEntity.ok(personLogic.getAllTrainees(pageable));
					
					} else if (role == 2) {
						return ResponseEntity.ok(personLogic.getAllTrainers(pageable));
						
					} else {
						return ResponseEntity.ok(null);
					}
		 }
		 
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "persons")
	public ResponseEntity<Person> createPerson(@RequestBody Person newPerson){
		System.out.println(newPerson);	
		personLogic.createPerson(newPerson);
		return ResponseEntity.ok(newPerson);
	}
	

	
	@RequestMapping(method = RequestMethod.PUT, value = "persons")
	public ResponseEntity<Person> modifyPerson(@RequestBody Person p){
	
		Person person = personLogic.updatePerson(p);
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
