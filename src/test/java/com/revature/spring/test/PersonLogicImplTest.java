package com.revature.spring.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.common.base.Optional;
import com.revature.domain.Person;
import com.revature.services.PersonLogic;
import com.revature.services.PersonLogicImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
@WebAppConfiguration
//@ComponentScan(basePackages = {"com.revature"})
//@SpringBootTest
public class PersonLogicImplTest {
	
	
	
//	  @Configuration
//	  @ComponentScan(basePackages = {"~.src.main.java.com.revature"})
//	    static class ContextConfiguration {
//
//	        // this bean will be injected into the OrderServiceTest class
//	        @Bean
//	        public PersonLogic orderService() {
//	            PersonLogic pLogic = new PersonLogicImpl();
//	            // set properties, etc.
//	            return pLogic;
//	        }
//	    }
	
	  @Autowired
	  private TestEntityManager entityManager;
	
	
	
	
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
//	
//	@Test
//	public void testGetPersonByFirstName() {
//		System.out.println(pLogic.toString());
//		System.out.println("here");
//		List<Person> persons = pLogic.getPersonByFirstName("Efren");
//		System.out.println("testingg");
//		System.out.println(persons.toString());
//		assertEquals(persons.toString(), "Person [id=101, firstName=Efren, lastName=Olivas]" );
//				
//	}
//
	
	@Test
	public void testGetPersonByFirstName() {
		System.out.println("begin");
		entityManager.persist(new Person("Christ", "Matheny"));
		System.out.println("2");
		List<Person> person = pLogic.getPersonByFirstName("Christ");
		System.out.println("3");
		System.out.println(person.toString());		
	}
	
	
	
//	@Test
//	public void testGetAll(){
//		System.out.println("getall");
//		List<Person> persons = pLogic.
//	}
	
	
	@After
	public void tearDown() {
	    db.shutdown();
	}
}
