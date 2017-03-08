package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.domain.Person;
import com.revature.domain.PersonRole;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

	List<Person> findAllByPersonRole(PersonRole personrole);
	List<Person> findAllByLastName(String lastName);
	List<Person> findAllByFirstName(String firstName);
	List<Person> findAllByFirstNameAndLastName(String firstName, String lastName);	

}
