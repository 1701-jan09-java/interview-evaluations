package com.revature.spring.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.domain.Person;
import com.revature.domain.PersonRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LatSTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PersonRepository personRepository;
	
	
	@Test
	public void testGetPersonByFirstName() {
		System.out.println("begin");
		entityManager.persist(new Person("Christ", "Matheny"));
		System.out.println("2");
		List<Person> person = personRepository.findByFirstName("Christ");
		System.out.println("3");
		System.out.println(person.toString());
		assertEquals("[Person [id=1, firstName=Christ, lastName=Matheny]]", person.toString());
	}
}
