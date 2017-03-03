package com.revature.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

	List<Person> findByFirstName(String firstName);
	List<Person> findByLastName(String lastName);
	
}
