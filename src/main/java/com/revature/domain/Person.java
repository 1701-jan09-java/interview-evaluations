package com.revature.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ie_person")
public class Person {

	@Id
	@Column(name = "p_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="perSeq")
	@SequenceGenerator(allocationSize=1, name="perSeq", sequenceName="person_seq")
	private int id;

	@Column(name = "p_firstname", nullable = false)
	private String firstName;
	
	@Column(name = "p_lastname", nullable = false)
	private String lastName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ie_person_role")
	private int personRole;
	
	public Person() {}

	public Person(String firstName, String lastName, int personRole) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.personRole = personRole;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", personRole=" + personRole + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getPersonRole() {
		return personRole;
	}
	
	public void setPersonRole(int personRole) {
		this.personRole = personRole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + personRole;
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
		Person other = (Person) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (personRole != other.personRole)
			return false;
		return true;
	}

	
		
}
