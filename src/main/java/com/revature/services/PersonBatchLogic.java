package com.revature.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.revature.domain.Person;
import com.revature.domain.PersonBatchJoin;

public interface PersonBatchLogic {

	Page<Person> getAllBatchMembers(Pageable pageable, Integer batchId);
	void createPersonBatchEntry(PersonBatchJoin newPBJ);
	void deletePersonBatchEntry(PersonBatchJoin oldPBJ);
}
