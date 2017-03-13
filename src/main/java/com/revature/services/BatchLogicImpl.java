package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.Batch;
import com.revature.domain.Person;
import com.revature.repositories.BatchRepository;

@Service
@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
public class BatchLogicImpl implements BatchLogic {

	@Autowired
	private BatchRepository dao;
	
	@Autowired
	private PersonLogic personLogic;
	
	@Override
	public Batch getBatchByName(String batchName) {
		Batch batch = dao.findByNameIgnoreCase(batchName);
		return batch;
	}

	@Override
	public Batch getBatchById(int batchId) {
		
		//ToDo: add validation check
		Batch batch = dao.findOne(batchId);
		return batch;
		}

	@Override
	public String deleteBatch(Integer id) {
		Batch batch = getBatchById(id);
		dao.delete(batch);
		return "Batch: " + id + " - DELETED";
	}
	

	@Override
	public Page<Batch> getAllBatches(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public Batch createBatch(Batch batchName) {
		dao.save(batchName);
		return batchName;
	}

	@Override
	public Batch updateBatch(Batch batchName, Integer id) {
		//Todo: validation
		Batch batch = dao.findOne(id);
		
		if(batchName.getName() != null || batchName.getName().equals("")){
			batch.setName(batchName.getName());
		}
		
		if(batchName.getPersons() != null){
			
		}
			
			
		dao.save(batchName);
		return batchName;
	}

	@Override
	public Page<Person> getAllPeopleByBatchId(Pageable pageable, Integer id) {
		 Batch batch = dao.findOne(id);
		 List<Person> personList = batch.getPersons();
		Page<Person> personPage = new PageImpl<Person>(personList, pageable, personList.size());
		return personPage;
	}

}
