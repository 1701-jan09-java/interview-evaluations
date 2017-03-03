package com.revature.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

	List<Person> findAllByLastName(String lastName);
	List<Person> findAllByFirstName(String firstName);
	List<Person> findAllByFirstNameAndLastName(String firstName, String lastName);
	
	//@Query("SELECT ie_person.p_firstname, ie_person.p_lastname, ie_person.p_id FROM ie_person INNER JOIN ie_eval ON ie_person.p_id=ie_eval.e_pid_trainee WHERE ie_eval.e_batch=?1")
	//List<Person> findAllByBatchId(int id);
	
	
	@Query("SELECT Person.firstName, Person.lastName, Person.id FROM Person INNER JOIN Eval ON Person.id=Eval.trainee INNER JOIN Person ON Eval.batch=Batch.id where name=?1")
	List<Person> findAllByBatchName(String batchName);
}
