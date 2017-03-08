package com.revature.spring.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.domain.Person;
import com.revature.domain.PersonRole;
import com.revature.repositories.PersonRepository;

/**
 * This Test creates a Person instance and stores it in an embedded database, namely the com.h2database.
 * It then uses the PersonRepository methods to test the h2 database.
 * @authors efren, sharang
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonEntityTest {

    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Before
    public void setUp() {
    	PersonRole personRole = new PersonRole(1, "Trainee");
    	System.out.println("begin");
		entityManager.persist(new Person("Christ", "McFeeny", personRole));
    }
	
	@Test
	public void testFindPersonByFirstName() {
		
		List<Person> person = personRepository.findAllByFirstName("Christ");
		System.out.println(person.toString());
		assertEquals("[Person [id=1, firstName=Christ, lastName=McFeeny, personRole=1]]", person.toString());
	}
	
	@Test
	public void testFindPersonByLastName() {
		
		List<Person> person = personRepository.findAllByFirstName("McFeeny");
		System.out.println(person.toString());
		assertEquals("[Person [id=2, firstName=Christ, lastName=McFeeny, personRole=1]]", person.toString());
	}
	
	@Test
	public void testFindPersonByRole() {
		
		PersonRole personRole = new PersonRole(1, "Trainee");
		List<Person> person = personRepository.findAllByPersonRole(personRole);
		System.out.println(person.toString());
		assertEquals("[Person [id=3, firstName=Christ, lastName=McFeeny, personRole=1]]", person.toString());
	}

	
}
