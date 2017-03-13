package com.revature.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="ie_question_eval")
public class QuestionEval implements Serializable {

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
	
	@OneToMany(mappedBy="questionEval", cascade = CascadeType.ALL)
	private List<QuestionComment> comments;
	
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

	public List<QuestionComment> getComments() {
		return comments;
	}

	public void setComments(List<QuestionComment> comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 41 * hash + Objects.hashCode(this.id);
		hash = 41 * hash + Objects.hashCode(this.communicationScore);
		hash = 41 * hash + Objects.hashCode(this.knowledgeScore);
		hash = 41 * hash + Objects.hashCode(this.eval);
		hash = 41 * hash + Objects.hashCode(this.questionPool);
		hash = 41 * hash + Objects.hashCode(this.comments);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final QuestionEval other = (QuestionEval) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (!Objects.equals(this.communicationScore, other.communicationScore)) {
			return false;
		}
		if (!Objects.equals(this.knowledgeScore, other.knowledgeScore)) {
			return false;
		}
		if (!Objects.equals(this.eval, other.eval)) {
			return false;
		}
		if (!Objects.equals(this.questionPool, other.questionPool)) {
			return false;
		}
		if (!Objects.equals(this.comments, other.comments)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "QuestionEval{" + "id=" + id + ", communicationScore=" + communicationScore + ", knowledgeScore=" + knowledgeScore + ", questionPool=" + questionPool + ", comments=" + comments + '}';
	}	
	
}
