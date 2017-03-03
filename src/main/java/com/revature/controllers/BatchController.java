package com.revature.controllers;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Batch;
import com.revature.services.BatchLogic;

@RestController
@RequestMapping(value = "/api/v1/")
public class BatchController {
	
	@Autowired
	private BatchLogic batchLogic;
	
	@RequestMapping(method = RequestMethod.GET, value = "/batch/{id}")
	
		public ResponseEntity<Batch> getBatch(@PathVariable("id") String id){
			
			try{
				int value = Integer.parseInt(id);
				return ResponseEntity.ok(batchLogic.getBatchById(value));
				
			} catch(NumberFormatException e){
				
				return ResponseEntity.ok(batchLogic.getBatchByName(id));
			}
			
		} 
		
	
	
	
}
