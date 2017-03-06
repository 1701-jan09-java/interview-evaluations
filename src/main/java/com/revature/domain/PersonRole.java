package com.revature.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ie_person_role")
public class PersonRole {
	
	@Id
	@Column(name="pr_id")
	private int id;
	
	@Column(name = "pr_title")
	private String title;

}
