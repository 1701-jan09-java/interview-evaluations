package com.revature.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

	List<Person> findAllByLastName(String lastName);
	List<Person> findAllByFirstName(String firstName);
	List<Person> findAllByFirstNameAndLastName(String firstName, String lastName);
	Person save(Person person);
	void delete(Person person);
	
	//@Query("FROM Person Bob JOIN Eval Frank ON Bob.id=Frank.batch.id WHERE Bob.id=?1")
	//List<Person> findAllPersonByEvalByBatchById(Integer id);
	
	
	//@Query("SELECT Person.firstName, Person.lastName, Person.id FROM Person INNER JOIN Eval ON Person.id=Eval.Trainee.id WHERE Eval.batch.id=?1")
	//List<Person> findAllByBatchId(Integer id);
	
	
	@Query("SELECT Person.firstName, Person.lastName, Person.id FROM Person INNER JOIN Eval ON Person.id=Eval.trainee INNER JOIN Person ON Eval.batch=Batch.id where name=?1")
	List<Person> findAllByBatchName(String batchName);
}
