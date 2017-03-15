package com.revature.controllers;

import com.revature.domain.Person;
import com.revature.services.PersonLogic;
import com.revature.services.PersonRoleLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/")
public class PersonController {
	
	@Autowired
	private PersonLogic personLogic;
	
	@Autowired
	private PersonRoleLogic pRLogic;
	
	@RequestMapping(method = RequestMethod.GET, value = "persons/{personId}")
		public ResponseEntity<Person> getPersonById(@PathVariable Integer personId ){
			return ResponseEntity.ok(personLogic.getPersonById(personId));
	}
		
	
	@RequestMapping(method = RequestMethod.GET, value = "persons")
	public ResponseEntity<Page<Person>> getPerson(Pageable pageable, @RequestParam(defaultValue="", required=false) String firstname,
			@RequestParam(defaultValue="", required=false) String lastname,
			@RequestParam(defaultValue="0", required=false) Integer role,
			@RequestParam(defaultValue="", required=false) String roleName){
		
		// both role and roleName are blank -> find by names only
		 if(role==0 && "".equals(roleName)){
			 			 
			 if(!"".equals(firstname) && "".equals(lastname)){
				 return ResponseEntity.ok(personLogic.getPersonByFirstName(pageable, firstname));
				 
			 } else if ("".equals(firstname) && !("".equals(lastname))){
				 return ResponseEntity.ok(personLogic.getPersonByLastName(pageable, lastname));
				 
			 } else if((!"".equals(firstname)) && !("".equals(lastname))) {
				 return ResponseEntity.ok(personLogic.getByFirstNameAndLastName(pageable, firstname, lastname));
			 } else{
				 return  ResponseEntity.ok(personLogic.getAllPersons(pageable));
			 } 
		 } 
		 
		 // role is left blank but roleName input is exists -> find by names and roleName
		 else if(role==0 && !"".equals(roleName)){
			 
 			 
			 if(!"".equals(firstname) && "".equals(lastname)){
				 return ResponseEntity.ok(personLogic.getPersonsByFirstnameAndPersonRole(pageable, firstname, pRLogic.getRoleByTitle(roleName)));
				 
			 } else if ("".equals(firstname) && !("".equals(lastname))){
				 return ResponseEntity.ok(personLogic.getPersonsByLastnameAndPersonRole(pageable, lastname, pRLogic.getRoleByTitle(roleName)));
				 
			 } else if((!"".equals(firstname)) && !("".equals(lastname))) {
				 return ResponseEntity.ok(personLogic.getPersonsByFirstnameAndLastnameAndPersonRole(pageable, firstname, lastname, pRLogic.getRoleByTitle(roleName)));
			 } else{
				 return  ResponseEntity.ok(personLogic.getAllPersonsByPersonRole(pageable, pRLogic.getRoleByTitle(roleName)));
			 } 
		 } 
		 
		// roleName is left blank but role input is exists -> find by names and role
		 else if(role!=0 && "".equals(roleName)){
			 
 			 
			 if(!"".equals(firstname) && "".equals(lastname)){
				 return ResponseEntity.ok(personLogic.getPersonsByFirstnameAndPersonRole(pageable, firstname, pRLogic.findRoleById(role)));
				 
			 } else if ("".equals(firstname) && !("".equals(lastname))){
				 return ResponseEntity.ok(personLogic.getPersonsByLastnameAndPersonRole(pageable, lastname, pRLogic.findRoleById(role)));
				 
			 } else if((!"".equals(firstname)) && !("".equals(lastname))) {
				 return ResponseEntity.ok(personLogic.getPersonsByFirstnameAndLastnameAndPersonRole(pageable, firstname, lastname, pRLogic.findRoleById(role)));
			 } else{
				 return  ResponseEntity.ok(personLogic.getAllPersonsByPersonRole(pageable, pRLogic.findRoleById(role)));
			 } 
		 } 
		 
		 // else-block will use role id if both inputted role and roleName correspond to each other
		 else{
			 
			  if ("".equals(firstname) && !("".equals(lastname))){
				 return ResponseEntity.ok(personLogic.getPersonsByLastnameAndPersonRole(pageable, lastname,  pRLogic.getRoleByTitleAndId(roleName, role)));
				 
			 } else if((!"".equals(firstname)) && !("".equals(lastname))) {
				 return ResponseEntity.ok(personLogic.getPersonsByFirstnameAndLastnameAndPersonRole(pageable, firstname, lastname, pRLogic.getRoleByTitleAndId(roleName, role)));
			 } else{
				 return  ResponseEntity.ok(personLogic.getAllPersonsByPersonRole(pageable, pRLogic.getRoleByTitleAndId(roleName, role)));
			 } 
			 
		 }	 
		 
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "persons")
	public ResponseEntity<Person> createPerson(@RequestBody Person newPerson){
		return ResponseEntity.ok(personLogic.createPerson(newPerson));
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "persons/{personId}")
	public ResponseEntity<Person> modifyPerson(@RequestBody Person updatedPerson, @PathVariable Integer personId){
		updatedPerson.setId(personId);
		return ResponseEntity.ok(personLogic.updatePerson(updatedPerson));
				
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "persons/{personId}")
	public ResponseEntity<Person> deletePerson(@PathVariable Integer personId){
	
			Person pers = personLogic.getPersonById(personId);
			personLogic.deletePerson(pers);
			return ResponseEntity.ok(pers);	
	}
}
