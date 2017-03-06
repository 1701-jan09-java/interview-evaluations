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
}
