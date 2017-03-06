package com.revature.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Person;
import com.revature.repositories.PersonRepository;

@RestController
@RequestMapping("/api/v1/")
public class TestController {
	
	@Autowired
	private PersonRepository repository;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Dog>> getAllDogs() {
		return ResponseEntity.ok(Arrays.asList(new Dog("scout", "lab"),new Dog("lucky", "lab")));
	}
	
	@RequestMapping(value="{name}", method=RequestMethod.GET)
	public ResponseEntity<Dog> getDog(@PathVariable("name") String name) {
		return ResponseEntity.ok(new Dog(name, "lab"));
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
		return ResponseEntity.ok(dog);
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT)
	public ResponseEntity<Dog> updateDog(@RequestBody Dog dog) {
		return ResponseEntity.ok(dog);
	}
	
	@RequestMapping(value="{name}", method=RequestMethod.DELETE)
	public ResponseEntity<Dog> deleteDog(@PathVariable("name") String name) {
		return ResponseEntity.ok(new Dog(name, "lab"));
	}
	
	@RequestMapping(value="person", method=RequestMethod.GET)
	public ResponseEntity<Iterable<Person>> getAllPersons() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	//this one
	@RequestMapping(value="person/{name}", method=RequestMethod.GET)
	public ResponseEntity<List<Person>> getPerson(@PathVariable("name") String name) {
		return ResponseEntity.ok(repository.findByFirstName(name));
	}
	
	@RequestMapping(value="person", method=RequestMethod.POST)
	public ResponseEntity<Person> creatPerson() {
		Person person = new Person("Efren", "Olivas");
		return ResponseEntity.ok(repository.save(person));
	}
	
	
	
}


class Dog {
	private String name;
	private String breed;
	
	public Dog(){}

	public Dog(String name, String breed) {
		super();
		this.name = name;
		this.breed = breed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breed == null) ? 0 : breed.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dog other = (Dog) obj;
		if (breed == null) {
			if (other.breed != null)
				return false;
		} else if (!breed.equals(other.breed))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Dog [name=" + name + ", breed=" + breed + "]";
	}
	
}
