package com.revature.services;

import java.util.List;

import com.revature.domain.Person;
import com.revature.domain.PersonBatchJoin;

public interface PersonBatchLogic {

	List<Person> getAllBatchMembers(Integer batchId);
	void createPersonBatchEntry(PersonBatchJoin newPBJ);
	void deletePersonBatchEntry(PersonBatchJoin oldPBJ);
}
