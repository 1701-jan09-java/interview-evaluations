package com.revature.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="ie_question_pool")
public class QuestionPool implements Serializable {

	@Id
	@Column(name="qp_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="questionPoolSeq")
	@SequenceGenerator(allocationSize=1, name="questionPoolSeq", sequenceName="question_pool_seq")
	private Integer id;

	@Column(name="qp_question_text")
	private String questionText;

	@Column(name="qp_max_communication_score")
	private Integer maxCommunicationScore;

	@Column(name="qp_max_knowledge_score")
	private Integer maxKnowledgeScore;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="qp_sid")
	private Subject subject;

	@Column(name="qp_count")
	private Integer useCount;

	@Column(name="qp_last_date_used")
	private Date dateLastUsed;
	
	public QuestionPool(){}

	public QuestionPool(String questionText, Integer maxCommunicationScore, Integer maxKnowledgeScore,
			Subject subject, Integer useCount, Date dateLastUsed) {
		super();
		this.questionText = questionText;
		this.maxCommunicationScore = maxCommunicationScore;
		this.maxKnowledgeScore = maxKnowledgeScore;
		this.subject = subject;
		this.useCount = useCount;
		this.dateLastUsed = dateLastUsed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Integer getMaxCommunicationScore() {
		return maxCommunicationScore;
	}

	public void setMaxCommunicationScore(Integer maxCommunicationScore) {
		this.maxCommunicationScore = maxCommunicationScore;
	}

	public Integer getMaxKnowledgeScore() {
		return maxKnowledgeScore;
	}

	public void setMaxKnowledgeScore(Integer maxKnowledgeScore) {
		this.maxKnowledgeScore = maxKnowledgeScore;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}

	public Date getDateLastUsed() {
		return dateLastUsed;
	}

	public void setDateLastUsed(Date dateLastUsed) {
		this.dateLastUsed = dateLastUsed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateLastUsed == null) ? 0 : dateLastUsed.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((maxCommunicationScore == null) ? 0 : maxCommunicationScore.hashCode());
		result = prime * result + ((maxKnowledgeScore == null) ? 0 : maxKnowledgeScore.hashCode());
		result = prime * result + ((questionText == null) ? 0 : questionText.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((useCount == null) ? 0 : useCount.hashCode());
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
		QuestionPool other = (QuestionPool) obj;
		if (dateLastUsed == null) {
			if (other.dateLastUsed != null)
				return false;
		} else if (!dateLastUsed.equals(other.dateLastUsed))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (maxCommunicationScore == null) {
			if (other.maxCommunicationScore != null)
				return false;
		} else if (!maxCommunicationScore.equals(other.maxCommunicationScore))
			return false;
		if (maxKnowledgeScore == null) {
			if (other.maxKnowledgeScore != null)
				return false;
		} else if (!maxKnowledgeScore.equals(other.maxKnowledgeScore))
			return false;
		if (questionText == null) {
			if (other.questionText != null)
				return false;
		} else if (!questionText.equals(other.questionText))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (useCount == null) {
			if (other.useCount != null)
				return false;
		} else if (!useCount.equals(other.useCount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QuestionPool [id=" + id + ", questionText=" + questionText + ", maxCommunicationScore="
				+ maxCommunicationScore + ", maxKnowledgeScore=" + maxKnowledgeScore + ", subject=" + subject
				+ ", useCount=" + useCount + ", dateLastUsed=" + dateLastUsed + "]";
	}
		
}
