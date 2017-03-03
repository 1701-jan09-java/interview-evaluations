package com.revature.services;

import java.util.ArrayList;

import com.revature.domain.Batch;

public interface BatchLogic {
	
	Batch getBatchByName(String batchName);
	Batch getBatchById(int batchId);
	void deleteBatch(Batch batchName);
	ArrayList<Batch> getAllBatches();
	void createBatch(Batch batchName);
	void updateBatch(Batch batchName);
	

}
