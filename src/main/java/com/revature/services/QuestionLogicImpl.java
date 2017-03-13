package com.revature.services;

import com.revature.domain.QuestionPool;
import com.revature.repositories.QuestionRepository;
import com.revature.validation.JsonValidation;
import com.revature.validation.exceptions.NotFoundException;
import java.sql.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
public class QuestionLogicImpl implements QuestionLogic{
	
	@Autowired
	private QuestionRepository dao;

    @Autowired
    private JsonValidation validation;

    @PersistenceContext
    private EntityManager entityManager;

	
//CREATE-----------------------------------
	@Override
	@Transactional
	public QuestionPool createQuestion(QuestionPool question) {
		
		validation.validateQuestionPoolFields(question);
		
		question.setUseCount(0);
        question.setDateLastUsed(null);
		question = dao.saveAndFlush(question);
        entityManager.refresh(question);

        return question;
	}

//RETRIEVE---------------------------------
	@Override
	public Page<QuestionPool> getAllQuestions(Pageable pageable, String subject) {
		
		if("all".equalsIgnoreCase(subject)){
			return dao.findAll(pageable);
		} else {
			return dao.findAllBySubjectSubjectIgnoreCase(pageable, subject);
		}
	}
	
	@Override
	public Page<QuestionPool> searchQuestions(Pageable pageable, String text, String subject) {
		
		if("all".equalsIgnoreCase(subject)){
			return dao.findBySearchTerm(pageable, text);
		} else {
			return dao.findBySearchTerm(pageable, text, subject);
		}

	}
	
	@Override
	public QuestionPool getQuestionById(Integer id) {
        QuestionPool question = dao.findOne(id);

        if (question == null) {
            throw new NotFoundException("Question with id " + id + " not found");
        }

		return question;
	}

//UPDATE-----------------------------------
	@Override
	@Transactional
	public QuestionPool updateQuestion(QuestionPool question, Integer questionId) {

        QuestionPool currQuestion = getQuestionById(questionId);

		if(question.getQuestionText() != null){
			currQuestion.setQuestionText(question.getQuestionText());
		}
		if(question.getMaxCommunicationScore() != null){
            // TODO Add logic to find questions that use this and scale scores based on new maximum
			currQuestion.setMaxCommunicationScore(question.getMaxCommunicationScore());
		}
		if(question.getMaxKnowledgeScore() != null){
			currQuestion.setMaxKnowledgeScore(question.getMaxKnowledgeScore());
		}
		if(question.getSubject() != null){
			currQuestion.setSubject(question.getSubject());
		}
		if(question.getUseCount() != null){
			currQuestion.setUseCount(question.getUseCount());
		}
		if(question.getDateLastUsed() != null){
			currQuestion.setDateLastUsed(question.getDateLastUsed());
		}

		dao.saveAndFlush(currQuestion);
		entityManager.refresh(currQuestion);
		return currQuestion;
	}

    @Override
	@Transactional
	public QuestionPool updateQuestionUsed(Integer questionId) {
		
		// validate that question exists
        validation.validateQuestionPoolExists(questionId);

        QuestionPool question = getQuestionById(questionId);
        question.setUseCount(question.getUseCount()+1);
        Date date = new Date((new java.util.Date()).getTime());
        question.setDateLastUsed(date);
        return dao.save(question);
	}

//DELETE-----------------------------------
	@Override
	@Transactional
	public String deleteQuestion(Integer questionId) {
		QuestionPool question = getQuestionById(questionId);
		dao.delete(question);
		return "Question: " + questionId + " - DELETED";
	}

}
