package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Person;
import com.revature.services.PersonLogic;

@RestController
@RequestMapping(value = "/api/v1/")
public class PersonController {
	
	@Autowired
	private PersonLogic personLogic;
	
//	@RequestMapping(method = RequestMethod.GET, value = "person")
//	public ResponseEntity<List<Person>> getAllPerson() {
//		
//		return ResponseEntity.ok(personLogic.getAllPersons());
//	}
	
	@RequestMapping(method = RequestMethod.GET, value = "person")
	public ResponseEntity<List<Person>> getPerson(@RequestParam(defaultValue="", required=false) String firstname,
			@RequestParam(defaultValue="", required=false) String lastname, 
			@RequestParam(defaultValue="0", required=false) Integer id,
			@RequestParam(defaultValue="0", required=false) Integer role){
		
		 if((id==0)&&(role==0)){
			 			 
			 if(!firstname.equals("")){
				 return ResponseEntity.ok(personLogic.getPersonByFirstName(firstname));
				 
			 } else if (!lastname.equals("")){
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
		

	
//	@RequestMapping(method = RequestMethod.GET, value = "person/role")
//	public List<Person> getPersonByRole(@RequestParam("id") Integer role) {
//		if(role==1){
//			
//		return personLogic.getAllTrainees();
//		
//		} else if(role == 2) {
//			return personLogic.getAllTrainers();
//		} else{
//			return null;
//		}
//	}
	
	
}
