package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Batch;
import com.revature.domain.Person;
import com.revature.services.BatchLogic;
import com.revature.services.PersonBatchLogic;

@RestController
@RequestMapping(value = "/api/v1/")
public class BatchController {
	
	@Autowired
	private BatchLogic batchLogic;
	
	@Autowired
	private PersonBatchLogic personBatchLogic;
	
	@RequestMapping(method = RequestMethod.GET, value = "/batch/{id}")
	
		public ResponseEntity<Batch> getBatch(@PathVariable("id") String id){
			
			try{
				int value = Integer.parseInt(id);
				return ResponseEntity.ok(batchLogic.getBatchById(value));
				
			} catch(NumberFormatException e){
				
				return ResponseEntity.ok(batchLogic.getBatchByName(id));
			}
			
		} 
		
	
	@RequestMapping(method = RequestMethod.GET, value = "/batch/{id}/members")
	
		public ResponseEntity<Page<Person>> getAllBatchMembers(Pageable pageable, @PathVariable("id") int id){
		
			return ResponseEntity.ok(personBatchLogic.getAllBatchMembers(pageable, id));
		}
	
	
	
}
