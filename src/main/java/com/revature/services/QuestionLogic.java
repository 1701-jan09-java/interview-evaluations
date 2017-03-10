package com.revature.services;

import com.revature.domain.QuestionComment;
import com.revature.domain.QuestionEval;
import com.revature.domain.QuestionPool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionLogic {
	
	Page<QuestionPool> getAllQuestions(Pageable pageable, String subject);
	
	Page<QuestionPool> searchQuestions(Pageable pageable, String text, String subject);
	
	QuestionPool getQuestionById(Integer id);
	
	QuestionPool createQuestion(QuestionPool question);

	QuestionPool updateQuestion(QuestionPool question);

	QuestionPool deleteQuestion(int id);

	QuestionComment getCommentById(Integer id);

	QuestionComment createComment(QuestionComment comment);
	
	QuestionComment createComment(QuestionComment comment, Integer questionId);

	QuestionComment updateComment(QuestionComment comment);

	QuestionComment deleteComment(int id);

	QuestionEval createQuestionEval(QuestionEval eval, Integer evalId);

	QuestionEval getQuestionEvalById(Integer id);

	QuestionEval updateQuestionEval(QuestionEval currQuestion);

	String deleteQuestionEval(int id);

}
