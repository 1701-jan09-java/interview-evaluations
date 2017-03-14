package com.revature.services;

import com.revature.domain.Batch;
import com.revature.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BatchLogic {
	
	Batch getBatchByName(String batchName);
	Batch getBatchById(int batchId);
	String deleteBatch(Integer id);
	Batch removePersonsFromBatch(Integer batchId, Integer[] personIds);
	Page<Batch> getAllBatches(Pageable pageable);
	Batch createBatch(Batch batchName);
	Batch addPersonsToBatch(Integer batchId, Integer[] personIds);
	Batch updateBatch(Batch batchName, Integer id);
	Page<Person> getAllPeopleByBatchId(Pageable pageable, Integer Id);

}
