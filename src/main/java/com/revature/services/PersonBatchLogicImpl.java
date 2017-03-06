package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.domain.Person;
import com.revature.domain.PersonBatchJoin;
import com.revature.repositories.PersonBatchRepository;

public class PersonBatchLogicImpl implements PersonBatchLogic{

	@Autowired
	PersonBatchRepository pBJ;
	
	@Override
	public List<Person> getAllBatchMembers(Integer batchId) {
		return pBJ.findAllPersonByBatchId(batchId);
	}

	@Override
	public void createPersonBatchEntry(PersonBatchJoin newPBJ) {
		pBJ.save(newPBJ);
	}

	@Override
	public void deletePersonBatchEntry(PersonBatchJoin oldPBJ) {
		pBJ.delete(oldPBJ);
	}

}
