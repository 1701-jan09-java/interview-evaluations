package com.revature.services;

import com.revature.domain.QuestionComment;
import com.revature.domain.QuestionEval;

public interface QuestionEvalLogic {
	

	QuestionEval createQuestionEval(QuestionEval eval, Integer evalId);

	QuestionEval getQuestionEvalById(Integer id);

	QuestionEval updateQuestionEval(QuestionEval currQuestion, Integer questionId);

	String deleteQuestionEval(Integer id);

    QuestionComment getCommentById(Integer id);
	
	QuestionComment createComment(QuestionComment comment, Integer questionId);

	QuestionComment updateComment(QuestionComment comment, Integer commentId);

	String deleteComment(Integer id);

}
