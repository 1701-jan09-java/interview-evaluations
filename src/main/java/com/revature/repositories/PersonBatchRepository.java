package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.domain.PersonBatchJoin;

public interface PersonBatchRepository extends JpaRepository<PersonBatchJoin, Integer>{

	List<Person> findPerson
}
