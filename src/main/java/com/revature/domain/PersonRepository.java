package com.revature.domain;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

	ArrayList<Person> findAllByLastName(String lastName);
	ArrayList<Person> findAllByFirstName(String firstName);
	ArrayList<Person> findAllByFirstNameAndLastName(String firstName, String lastName);
	ArrayList<Person> findAllByPersonRole(int personRole);
	
	//@Query("SELECT ie_person.p_firstname, ie_person.p_lastname, ie_person.p_id FROM ie_person INNER JOIN ie_eval ON ie_person.p_id=ie_eval.e_pid_trainee WHERE ie_eval.e_batch=?1")
	//List<Person> findAllByBatchId(int id);
	
	//@Query("SELECT ie_person.p_firstname, ie_person.p_lastname, ie_person.p_id FROM ie_person INNER JOIN ie_eval ON ie_person.p_id=ie_eval.e_pid_trainee INNER JOIN ie_person ON ie_eval.e_batch=ie_batch.b_id WHERE b_name=?1")
	//List<Person> findAllByBatchName(String batchName);
}
