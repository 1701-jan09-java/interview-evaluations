package com.revature.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.domain.Batch;
import com.revature.domain.Person;
import com.revature.domain.PersonRole;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

	Page<Person> findAllByPersonRole(Pageable pageable, PersonRole personrole);
	Page<Person> findAllByLastName(Pageable pageable, String lastName);
	Page<Person> findAllByFirstName(Pageable pageable, String firstName);
	Page<Person> findAllByFirstNameAndLastName(Pageable pageable, String firstName, String lastName);	

}
