package com.revature.services;

import com.revature.domain.QuestionPool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionLogic {
	
	Page<QuestionPool> getAllQuestions(Pageable pageable, String subject);
	
	Page<QuestionPool> searchQuestions(Pageable pageable, String text, String subject);
	
	QuestionPool getQuestionById(Integer id);
	
	QuestionPool createQuestion(QuestionPool question);

	QuestionPool updateQuestion(QuestionPool question, Integer id);

    QuestionPool updateQuestionUsed(Integer id);

	String deleteQuestion(Integer id);

}
