package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.domain.Batch;
import com.revature.domain.Person;
import com.revature.domain.PersonBatchJoin;

@Repository
public interface PersonBatchRepository extends JpaRepository<PersonBatchJoin, Integer>{

	List<PersonBatchJoin> findAllByBatchId(Integer batchId);
	void deletePersonBatchByBatch(Batch batch);
	void deletePersonBatchByPerson(Person person);
	

}
