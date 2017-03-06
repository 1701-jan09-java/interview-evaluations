package com.revature.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name="ie_question_eval")
public class QuestionEval {

	@Id
	@Column(name="qe_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="questionEvalSeq")
	@SequenceGenerator(allocationSize=1, name="questionEvalSeq", sequenceName="question_eval_seq")
	private Integer id;
	
	@Column(name="qe_communication_score")
	private Integer communicationScore;
	
	@Column(name="qe_knowledge_score")
	private Integer knowledgeScore;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="qe_eid")
	private Eval eval;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="qe_qpid")
	private QuestionPool questionPool;
	
	public QuestionEval(){}

	public QuestionEval(Integer communicationScore, Integer knowledgeScore, Eval eval,
			QuestionPool questionPool) {
		super();
		this.communicationScore = communicationScore;
		this.knowledgeScore = knowledgeScore;
		this.eval = eval;
		this.questionPool = questionPool;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCommunicationScore() {
		return communicationScore;
	}

	public void setCommunicationScore(Integer communicationScore) {
		this.communicationScore = communicationScore;
	}

	public Integer getKnowledgeScore() {
		return knowledgeScore;
	}

	public void setKnowledgeScore(Integer knowledgeScore) {
		this.knowledgeScore = knowledgeScore;
	}

	@JsonIgnore
	public Eval getEval() {
		return eval;
	}

	public void setEval(Eval eval) {
		this.eval = eval;
	}

	public QuestionPool getQuestionPool() {
		return questionPool;
	}

	public void setQuestionPool(QuestionPool questionPool) {
		this.questionPool = questionPool;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((communicationScore == null) ? 0 : communicationScore.hashCode());
		result = prime * result + ((eval == null) ? 0 : eval.getId().hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((knowledgeScore == null) ? 0 : knowledgeScore.hashCode());
		result = prime * result + ((questionPool == null) ? 0 : questionPool.hashCode());
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
		QuestionEval other = (QuestionEval) obj;
		if (communicationScore == null) {
			if (other.communicationScore != null)
				return false;
		} else if (!communicationScore.equals(other.communicationScore))
			return false;
		if (eval == null) {
			if (other.eval != null)
				return false;
		} else if (!eval.equals(other.eval))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (knowledgeScore == null) {
			if (other.knowledgeScore != null)
				return false;
		} else if (!knowledgeScore.equals(other.knowledgeScore))
			return false;
		if (questionPool == null) {
			if (other.questionPool != null)
				return false;
		} else if (!questionPool.equals(other.questionPool))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QuestionEval [id=" + id + ", communicationScore=" + communicationScore + ", knowledgeScore="
				+ knowledgeScore + ", questionPool=" + questionPool + "]";
	}
	
	
}
