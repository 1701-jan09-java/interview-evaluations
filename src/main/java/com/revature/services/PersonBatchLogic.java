package com.revature.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.revature.domain.Batch;
import com.revature.domain.Person;
import com.revature.domain.PersonBatchJoin;

public interface PersonBatchLogic {

	Page<Person> getAllBatchMembers(Pageable pageable, Integer batchId);
	void createPersonBatch(PersonBatchJoin newPBJ);
	void updatePersonBatch(PersonBatchJoin newPBJ);
	void deletePersonBatchByBatch(Batch batch);
	void deletePersonBatchByPerson(Person person);
}
