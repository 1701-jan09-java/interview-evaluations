package com.revature.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.domain.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person,Integer> {

	List<Person> findByLastName(String lastName);
	
}
