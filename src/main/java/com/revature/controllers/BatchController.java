package com.revature.controllers;

import com.revature.domain.Batch;
import com.revature.services.BatchLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/")
public class BatchController {
	
	@Autowired
	private BatchLogic batchLogic;
	
	@RequestMapping(method = RequestMethod.GET, value = "batches")
	public ResponseEntity<Page<Batch>> getBatches(Pageable pageable) {
		return ResponseEntity.ok(batchLogic.getAllBatches(pageable));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/batches/{batchId}")
	public ResponseEntity<Batch> getBatch(@PathVariable String batchId){

		Batch batch;

		try{
			int value = Integer.parseInt(batchId);
			batch = batchLogic.getBatchById(value);
		} catch(NumberFormatException e){
			batch = batchLogic.getBatchByName(batchId);
		}

		return ResponseEntity.ok(batch);
	} 
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/batches/{batchId}")
	public ResponseEntity<String> deleteBatch(@PathVariable Integer batchId){
		return ResponseEntity.ok(batchLogic.deleteBatch(batchId));
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/batches/{batchId}")
	public ResponseEntity<Batch> updateBatch(@PathVariable Integer batchId, @RequestBody Batch newBatch){
		return ResponseEntity.ok(batchLogic.updateBatch(newBatch, batchId));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/batches")
	public ResponseEntity<Batch> createBatch(@RequestBody Batch newBatch){
		return ResponseEntity.ok(batchLogic.createBatch(newBatch));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/batches/{batchId}/members")
	public ResponseEntity<Batch> addMembersToBatch(@PathVariable Integer batchId, @RequestBody Integer[] personIds){
		return ResponseEntity.ok(batchLogic.addPersonsToBatch(batchId, personIds));
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/batches/{batchId}/members")
	public ResponseEntity<Batch> removeMembersFromBatch(@PathVariable Integer batchId, @RequestBody Integer[] personIds){
		return ResponseEntity.ok(batchLogic.removePersonsFromBatch(batchId, personIds));
	}
		
	
}
