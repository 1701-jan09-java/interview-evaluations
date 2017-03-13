package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin
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
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}	
		} catch(NumberFormatException e){
			Batch batch = batchLogic.getBatchByName(id);
			if(batch != null){
				return ResponseEntity.ok(batchLogic.getBatchByName(id));
			} else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}	
	} 
	
	@RequestMapping(method = RequestMethod.PUT, value = "/batches")
	public ResponseEntity<Batch> updateBatch(@RequestBody Batch newBatch){
		Batch batch = batchLogic.getBatchById(newBatch.getId());
		if(batch != null){
			batchLogic.updateBatch(newBatch);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/batches/{id}")
	public ResponseEntity<Batch> deleteBatch(@PathVariable("id") int id){
		Batch batch = batchLogic.getBatchById(id);
		if(batch != null){
			batchLogic.deleteBatch(batch);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/batches")
	public ResponseEntity<Batch> createBatch(@RequestBody Batch newBatch){
		Batch batch = batchLogic.getBatchByName(newBatch.getName());
		if(batch != null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else{
			batchLogic.createBatch(newBatch);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/batches/{id}/members")
	public ResponseEntity<Page<Person>> getAllBatchMembers(@PageableDefault(size=10) 
		@SortDefaults({@SortDefault(sort="lastName"), @SortDefault(sort="firstName")}) 
		Pageable pageable, @PathVariable("id") int id){
		return ResponseEntity.ok(batchLogic.getAllPeopleByBatchId(pageable, id));
	}
		
	
}
