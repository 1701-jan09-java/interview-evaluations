package com.revature.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table(name="ie_question_comment")
public class QuestionComment implements Serializable {

	@Id
	@Column(name="qc_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="questionCommentSeq")
	@SequenceGenerator(allocationSize=1, name="questionCommentSeq", sequenceName="question_comment_seq")
	private Integer id;
	
	@Column(name="qc_comment_text")
	private String commentText;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="qc_eid")
	private QuestionEval questionEval;

	public QuestionComment(){}

	public QuestionComment(String commentText, QuestionEval eval) {
		super();
		this.commentText = commentText;
		this.questionEval = eval;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	@JsonIgnore
	public QuestionEval getQuestionEval() {
		return questionEval;
	}

	public void setQuestionEval(QuestionEval questionEval) {
		this.questionEval = questionEval;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentText == null) ? 0 : commentText.hashCode());
//		result = prime * result + ((eval == null) ? 0 : eval.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		QuestionComment other = (QuestionComment) obj;
		if (commentText == null) {
			if (other.commentText != null)
				return false;
		} else if (!commentText.equals(other.commentText))
			return false;
		if (questionEval == null) {
			if (other.questionEval != null)
				return false;
		} else if (!questionEval.equals(other.questionEval))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QuestionComment [id=" + id + ", commentText=" + commentText + "]";
	}
	
	
}
