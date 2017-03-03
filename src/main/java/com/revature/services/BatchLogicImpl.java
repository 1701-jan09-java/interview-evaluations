package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.Batch;
import com.revature.repositories.BatchRepository;

@Service
@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
public class BatchLogicImpl implements BatchLogic {

	@Autowired
	private BatchRepository dao;
	
	@Override
	public Batch getBatchByName(String batchName) {
		Batch batch = dao.findBatchByName(batchName);
		System.out.println(batch);
		return batch;
	}

	@Override
	public Batch getBatchById(int batchId) {
		Batch batch = dao.findBatchById(batchId);
		System.out.print(batch);
		return batch;
		}

	@Override
	public void deleteBatch(Batch batchName) {
		dao.delete(batchName);	
	}
	

	@Override
	public ArrayList<Batch> getAllBatches() {
		ArrayList<Batch> allBatches = dao.findAll();
		System.out.println(allBatches);
		return allBatches;
	}

	@Override
	public void createBatch(Batch batchName) {
		dao.save(batchName);
		
	}

	@Override
	public void updateBatch(Batch batchName) {
		dao.save(batchName);
		
	}

}
