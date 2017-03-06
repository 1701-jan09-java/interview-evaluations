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
@Table(name="ie_person_batch")
public class PersonBatchJoin {

	@Id
	@Column(name="join_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="perBatSeq")
	@SequenceGenerator(allocationSize=1, name="perBatSeq", sequenceName="person_batch_seq")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "batch_id", nullable = false)
	private Batch batch;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public PersonBatchJoin(){}
	
	public PersonBatchJoin(int id, Person person, Batch batch) {
		super();
		this.id = id;
		this.person = person;
		this.batch = batch;
	}

	@Override
	public String toString() {
		return "PersonBatchJoin [id=" + id + ", person=" + person + ", batch=" + batch + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batch == null) ? 0 : batch.hashCode());
		result = prime * result + id;
		result = prime * result + ((person == null) ? 0 : person.hashCode());
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
		PersonBatchJoin other = (PersonBatchJoin) obj;
		if (batch == null) {
			if (other.batch != null)
				return false;
		} else if (!batch.equals(other.batch))
			return false;
		if (id != other.id)
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		return true;
	}
	
}
