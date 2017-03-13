package com.revature.controllers;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Batch;
import com.revature.domain.Person;
import com.revature.services.BatchLogic;
import com.revature.services.PersonLogic;

@RestController
@RequestMapping(value = "/api/v1/")
public class BatchController {
	
	@Autowired
	private BatchLogic batchLogic;
	
	@Autowired
	private PersonLogic personLogic;
	
	@RequestMapping(method = RequestMethod.GET, value = "/batches/{id}")
	public ResponseEntity<Batch> getBatch(@PathVariable("id") String id){
			
		try{
			int value = Integer.parseInt(id);
			Batch batch = batchLogic.getBatchById(value);
			if(batch != null){
				return ResponseEntity.ok(batchLogic.getBatchById(value));
			} else{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}	
		} catch(NumberFormatException e){
			Batch batch = batchLogic.getBatchByName(id);
			if(batch != null){
				return ResponseEntity.ok(batchLogic.getBatchByName(id));
			} else{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		}	
	} 
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/batches/{id}")
	public ResponseEntity<String> deleteBatch(@PathVariable("id") Integer id){
		Batch batch = batchLogic.getBatchById(id);
		if(batch == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Batch " + id + " was not found");
		} else{
			batchLogic.deleteBatch(batch);
			return ResponseEntity.ok("Batch " + id + " was deleted successfully.");
		}
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/batches/{id}")
	public ResponseEntity<Batch> updateBatch(@PathVariable Integer id, @RequestBody Batch newBatch){
		Batch batch = batchLogic.getBatchById(id);
		if(batch != null){
			batchLogic.updateBatch(newBatch);
			return ResponseEntity.ok(batch);
		} else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/batches")
	public ResponseEntity<Batch> createBatch(@RequestBody Batch newBatch){
		//ToDo: Call validation methods
		Batch batch = batchLogic.getBatchById(newBatch.getId());
		if(newBatch.getId() != null && newBatch.getId() != 0){
			throw new ConstraintViolationException("", null);
		} else if(batch != null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(batch);
		} else{
			batchLogic.createBatch(newBatch);
			return ResponseEntity.ok(newBatch);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/batches/{id}/members")
	public ResponseEntity<Page<Person>> getAllBatchMembers(@PageableDefault(size=10) 
		@SortDefaults({@SortDefault(sort="lastName"), @SortDefault(sort="firstName")}) 
		Pageable pageable, @PathVariable("id") Integer id){
		return ResponseEntity.ok(batchLogic.getAllPeopleByBatchId(pageable, id));
	}
		
	
}
