package com.revature.services;

import com.revature.domain.Batch;
import com.revature.domain.Person;
import com.revature.repositories.BatchRepository;
import com.revature.validation.JsonValidation;
import com.revature.validation.exceptions.NotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
public class BatchLogicImpl implements BatchLogic {

	@Autowired
	private BatchRepository dao;
	
	@Autowired
	private PersonLogic personLogic;
	
	@Autowired
	private JsonValidation validation;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Batch getBatchByName(String batchName) {
		Batch batch = dao.findByNameIgnoreCase(batchName);
		
		if (batch == null) {
			throw new NotFoundException("Batch with name " + batchName + " not found");
		}
		
		return batch;
	}

	@Override
	public Batch getBatchById(int batchId) {
		Batch batch = dao.findOne(batchId);
		
		if (batch == null) {
			throw new NotFoundException("Batch with id " + batchId + " not found");
		}
		
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
	public Batch createBatch(Batch batch) {
		validation.validateBatchFields(batch);
		dao.save(batch);
		return batch;
	}

	@Override
	public Batch updateBatch(Batch batchName, Integer id) {
		
		Batch batch = getBatchById(id);
		
		if(batchName.getName() != null || batchName.getName().equals("")){
			batch.setName(batchName.getName());
		}
		
		if(batchName.getPersons() != null){
			// add the things
		}
		
		
		dao.saveAndFlush(batch);
		entityManager.refresh(batch);
		return batch;
	}

	@Override
	public Page<Person> getAllPeopleByBatchId(Pageable pageable, Integer id) {
		
		Batch batch = getBatchById(id);
		
		List<Person> personList = batch.getPersons();
		Page<Person> personPage = new PageImpl<Person>(personList, pageable, personList.size());
		return personPage;
	}

}
