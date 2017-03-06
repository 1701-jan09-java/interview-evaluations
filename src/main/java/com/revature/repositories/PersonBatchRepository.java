package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.domain.Person;
import com.revature.domain.PersonBatchJoin;

public interface PersonBatchRepository extends JpaRepository<PersonBatchJoin, Integer>{

	List<Person> findAllPersonByBatchId(Integer batchId);

}
