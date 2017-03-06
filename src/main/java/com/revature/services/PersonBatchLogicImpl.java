package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.Person;
import com.revature.domain.PersonBatchJoin;
import com.revature.repositories.PersonBatchRepository;

@Service
@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
public class PersonBatchLogicImpl implements PersonBatchLogic{

	@Autowired
	PersonBatchRepository pBJ;
	
	@Override
	public List<Person> getAllBatchMembers(Integer batchId) {
		List<PersonBatchJoin> list = pBJ.findAllByBatchId(batchId);
		List<Person> personList = new ArrayList();
		for(PersonBatchJoin temp:list){
			personList.add(temp.getPerson());
			
		}
		return personList;
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
