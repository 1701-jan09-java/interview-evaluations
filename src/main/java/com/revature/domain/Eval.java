package com.revature.domain;

import java.sql.Date;

import javax.persistence.*;

import org.springframework.data.annotation.Id;

@Entity
@Table(name="public.ie_eval")
public class Eval {
	
	@Id
	@Column(name="e_id")
	private Integer id;
	
	@Column(name="e_week")
	private Integer week;
	
	@Column(name="e_date")
	private Date date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="e_eval_type")
	private EvalType evalType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="e_pid_trainee")
	private Person trainee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="e_batch")
	private Batch batch;

	public Eval() {}
	
	public Eval(Integer id, Integer week, Date date, EvalType evalType, Person trainee, Batch batch) {
		super();
		this.id = id;
		this.week = week;
		this.date = date;
		this.evalType = evalType;
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

	public EvalType getEvalType() {
		return evalType;
	}

	public Person getTrainee() {
		return trainee;
	}

	public Batch getBatch() {
		return batch;
	}
	
	
}
