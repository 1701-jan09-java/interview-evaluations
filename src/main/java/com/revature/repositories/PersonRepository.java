package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

	Person getPersonById(int id);
	
//	List<Person> findAllPersons();
	List<Person> findByLastName(String lastName);
	List<Person> findByFirstName(String firstName);
	List<Person> findByFirstNameAndLastName(String firstName, String lastName);
	List<Person> findByPersonRole(int personRole);
	
}
