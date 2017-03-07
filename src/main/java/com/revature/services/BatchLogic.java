package com.revature.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.revature.domain.Batch;

public interface BatchLogic {
	
	Batch getBatchByName(String batchName);
	Batch getBatchById(int batchId);
	void deleteBatch(Batch batchName);
	Page<Batch> getAllBatches(Pageable pageable);
	void createBatch(Batch batchName);
	void updateBatch(Batch batchName);
	

}
