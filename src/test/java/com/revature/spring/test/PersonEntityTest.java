package com.revature.spring.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.domain.Person;
import com.revature.domain.PersonRole;
import com.revature.log.IntEvalLogger;
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
    	
    	BasicConfigurator.configure();
    	
    	IntEvalLogger.LOGGER.info("begin testing");
    	
    	PersonRole personRole = new PersonRole(1, "Trainee");    	
    	
    	entityManager.persist(personRole);
		entityManager.persist(new Person("Christ", "McFeeny", personRole));
    }
	
	@Test
	public void testFindPersonByFirstName() {
		
		BasicConfigurator.configure();
		
		List<Person> person = personRepository.findAllByFirstName("Christ");
		IntEvalLogger.LOGGER.info(person.toString());
		assertEquals("[Person [id=1, firstName=Christ, lastName=McFeeny, personRole=PersonRole [id=1, title=Trainee]]]", person.toString());
	}
	
	@Test
	public void testFindPersonByLastName() {
		
		BasicConfigurator.configure();
		
		List<Person> person = personRepository.findAllByLastName("McFeeny");
		IntEvalLogger.LOGGER.info(person.toString());
		assertEquals("[Person [id=2, firstName=Christ, lastName=McFeeny, personRole=PersonRole [id=1, title=Trainee]]]", person.toString());
	}
	
	@Test
	public void testFindPersonByRole() {
		
		BasicConfigurator.configure();
		
		PersonRole personRole = new PersonRole(1, "Trainee");
		List<Person> person = personRepository.findAllByPersonRole(personRole);
		IntEvalLogger.LOGGER.info(person.toString());
		assertEquals("[Person [id=3, firstName=Christ, lastName=McFeeny, personRole=PersonRole [id=1, title=Trainee]]]", person.toString());
	}

	
}
