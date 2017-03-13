package com.revature.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name="ie_eval")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Eval implements Serializable{
	
	@Id
	@Column(name="e_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="evalSeq")
	@SequenceGenerator(allocationSize=1, name="evalSeq", sequenceName="eval_seq")
	private Integer id;
	
	@Column(name="e_week")
	private Integer week;
	
	@Column(name="e_date")
	private Date date;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="e_eval_type")
	private EvalType evalType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="e_pid_trainee")
	private Person trainee;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="e_batch")
	private Batch batch;
	
	@OneToMany(mappedBy="eval", cascade = CascadeType.ALL)
	private List<QuestionEval> questions;
	
	@OneToMany(mappedBy="eval", cascade = CascadeType.ALL)
	private List<EvalComment> comments;
	
	@Transient
	private int evalKnowledgeScore;
	
	@Transient
	private int evalMaxKnowledgeScore;
	
	@Transient
	private int evalCommunicationScore;
	
	@Transient
	private int evalMaxCommunicationScore;
	
	public Eval() {/*empty constructor needed*/}

	public Eval(Integer week, Date date, EvalType evalType, Person trainee, Batch batch,
			List<QuestionEval> questions, List<EvalComment> comments) {
		super();
		this.week = week;
		this.date = date;
		this.evalType = evalType;
		this.trainee = trainee;
		this.batch = batch;
		this.questions = questions;
		this.comments = comments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public EvalType getEvalType() {
		return evalType;
	}

	public void setEvalType(EvalType evalType) {
		this.evalType = evalType;
	}

	public Person getTrainee() {
		return trainee;
	}

	public void setTrainee(Person trainee) {
		this.trainee = trainee;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public List<QuestionEval> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionEval> questions) {
		this.questions = questions;
	}

	public List<EvalComment> getComments() {
		return comments;
	}

	public void setComments(List<EvalComment> comments) {
		this.comments = comments;
	}

	public int getEvalKnowledgeScore() {
		return evalKnowledgeScore;
	}

	public void setEvalKnowledgeScore(int evalKnowledgeScore) {
		this.evalKnowledgeScore = evalKnowledgeScore;
	}

	public int getEvalMaxKnowledgeScore() {
		return evalMaxKnowledgeScore;
	}

	public void setEvalMaxKnowledgeScore(int evalMaxKnowledgeScore) {
		this.evalMaxKnowledgeScore = evalMaxKnowledgeScore;
	}

	public int getEvalCommunicationScore() {
		return evalCommunicationScore;
	}

	public void setEvalCommunicationScore(int evalCommunicationScore) {
		this.evalCommunicationScore = evalCommunicationScore;
	}

	public int getEvalMaxCommunicationScore() {
		return evalMaxCommunicationScore;
	}

	public void setEvalMaxCommunicationScore(int evalMaxCommunicationScore) {
		this.evalMaxCommunicationScore = evalMaxCommunicationScore;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batch == null) ? 0 : batch.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((evalType == null) ? 0 : evalType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((questions == null) ? 0 : questions.hashCode());
		result = prime * result + ((trainee == null) ? 0 : trainee.hashCode());
		result = prime * result + ((week == null) ? 0 : week.hashCode());
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
		Eval other = (Eval) obj;
		if (batch == null) {
			if (other.batch != null)
				return false;
		} else if (!batch.equals(other.batch))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (evalType == null) {
			if (other.evalType != null)
				return false;
		} else if (!evalType.equals(other.evalType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		if (trainee == null) {
			if (other.trainee != null)
				return false;
		} else if (!trainee.equals(other.trainee))
			return false;
		if (week == null) {
			if (other.week != null)
				return false;
		} else if (!week.equals(other.week))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Eval [id=" + id + ", week=" + week + ", date=" + date + ", evalType=" + evalType + ", trainee="
				+ trainee + ", batch=" + batch + ", questions=" + questions + ", comments=" + comments + "]";
	}
	
	
}
