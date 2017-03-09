package com.revature.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="ie_batch")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Batch implements Serializable  {

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="batch_sequence")
	@SequenceGenerator(allocationSize=1, name="batch_sequence", sequenceName="batch_seq")
	@Column(name="b_id")
	private int id;
	
	@Column(name="b_name")
	private String name;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="ie_person_batch", joinColumns=@JoinColumn(name="batch_id"), inverseJoinColumns=@JoinColumn(name="person_id"))
	private List<Person> persons;

	public Batch() {}
		
	public Batch(String name) {
		super();
		this.name = name;
	}
	
	public List<Person> getPersons(){
		return persons;
	}
	
	public void setPersons(List<Person> people){
		this.persons = people;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Batch other = (Batch) obj;
		if (id != other.id)
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
		return "Batch [id=" + id + ", name=" + name+ "]";
	}


}
