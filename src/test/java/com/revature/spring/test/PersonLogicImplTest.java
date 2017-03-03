package com.revature.spring.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import com.revature.domain.Person;
import com.revature.services.PersonLogic;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class PersonLogicImplTest {
	
	private EmbeddedDatabase db;
	
	@Before
	 public void setUp() {
  
        db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .addScripts("create-person.sql", "insert-person-data.sql")
                .build();
    }
	
	@Autowired
	private PersonLogic pLogic;
	
	@Test 
	public void testTest() {
		
		assertTrue(true);
	}
	
	@Test
	public void testGetPersonByFirstName() {
		
		System.out.println("here");
		List<Person> persons = pLogic.getPersonByFirstName("Chris");
		System.out.println("testingg");
		System.out.println(persons.toString());
		assertEquals(persons.toString(), "Person [id=103, firstName=Chris, lastName=Matheny]" );
				
	}
	
	@After
	public void tearDown() {
	    db.shutdown();
	}
}
