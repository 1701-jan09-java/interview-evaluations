package com.revature.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

	Person getPersonById(int id);
	ArrayList<Person> findByLastName(String lastName);
	ArrayList<Person> findByFirstName(String firstName);
	ArrayList<Person> findByFirstNameAndLastName(String firstName, String lastName);
	ArrayList<Person> findByPersonRole(int personRole);
	
}
