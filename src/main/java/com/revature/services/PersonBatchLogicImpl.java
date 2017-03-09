package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.Batch;
import com.revature.domain.Person;
import com.revature.domain.PersonBatchJoin;
import com.revature.repositories.PersonBatchRepository;

@Service
@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
public class PersonBatchLogicImpl implements PersonBatchLogic{

	@Autowired
	PersonBatchRepository pBJ;
	
	@Override
	public Page<Person> getAllBatchMembers(Pageable pageable, Integer batchId) {
		List<PersonBatchJoin> list = pBJ.findAllByBatchId(batchId);
		List<Person> personList = new ArrayList<>();
		for(PersonBatchJoin temp:list){
			personList.add(temp.getPerson());
		}
		Page<Person> personPage = new PageImpl<Person>(personList);
		return personPage;
	}

	@Override
	public void createPersonBatch(PersonBatchJoin newPBJ) {
		pBJ.save(newPBJ);
	}

	@Override
	public void deletePersonBatchByBatch(Batch batch) {
		pBJ.deletePersonBatchByBatch(batch);
	}

	@Override
	public void deletePersonBatchByPerson(Person person) {
		pBJ.deletePersonBatchByPerson(person);
	}

	@Override
	public void updatePersonBatch(PersonBatchJoin newPBJ) {
		pBJ.save(newPBJ);
	}

}
