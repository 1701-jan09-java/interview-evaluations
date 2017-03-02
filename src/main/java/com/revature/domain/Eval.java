package com.revature.domain;

import java.sql.Date;

import javax.persistence.*;

import org.springframework.data.annotation.Id;

@Entity
@Table(name="public.ie_eval")
public class Eval {
	
	@Id
	@Column
	private Integer id;
	@Column
	private Integer week;
	@Column
	private Date date;
	@Column
	private Integer interviewType;
	@Column
	private Integer trainee;
	@Column
	private Integer batch;
	
	public Eval(Integer id, Integer week, Date date, Integer interviewType, Integer trainee, Integer batch) {
		super();
		this.id = id;
		this.week = week;
		this.date = date;
		this.interviewType = interviewType;
		this.trainee = trainee;
		this.batch = batch;
	}

	public Integer getId() {
		return id;
	}

	public Integer getWeek() {
		return week;
	}

	public Date getDate() {
		return date;
	}

	public Integer getInterviewType() {
		return interviewType;
	}

	public Integer getTrainee() {
		return trainee;
	}

	public Integer getBatch() {
		return batch;
	}
	
	
}
