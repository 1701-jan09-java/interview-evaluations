package com.revature.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.revature.domain.Batch;
import com.revature.domain.Person;

public interface BatchLogic {
	
	Batch getBatchByName(String batchName);
	Batch getBatchById(int batchId);
	String deleteBatch(Integer id);
	Page<Batch> getAllBatches(Pageable pageable);
	Batch createBatch(Batch batchName);
	Batch updateBatch(Batch batchName, Integer id);
	Page<Person> getAllPeopleByBatchId(Pageable pageable, Integer Id);

}
