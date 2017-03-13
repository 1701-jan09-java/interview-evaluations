package com.revature.services;

import com.revature.domain.QuestionPool;
import com.revature.repositories.QuestionRepository;
import com.revature.repositories.SubjectRepository;
import com.revature.validation.exceptions.NotFoundException;
import java.sql.Date;
import javax.validation.ConstraintViolationException;
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
	private SubjectRepository subjectDao;

	
//CREATE-----------------------------------
	@Override
	@Transactional
	public QuestionPool createQuestion(QuestionPool question) {
		
		if (question.getMaxCommunicationScore() == null) {
			throw new ConstraintViolationException("Missing required field maxCommunicationScore (Integer)", null);
		}
		
		if (question.getMaxKnowledgeScore() == null) {
			throw new ConstraintViolationException("Missing required field maxKnowledgeScore (Integer)", null);
		}
		
		if (question.getQuestionText() == null) {
			throw new ConstraintViolationException("Missing required field questionText (String)", null);
		}
		
		if (question.getSubject() == null || question.getSubject().getId() == null) {
			throw new ConstraintViolationException("Missing required field subject.id (Integer)", null);
		}
		
		question.setUseCount(0);
		question = dao.save(question);
		// retrieve subject text - not auto filled
		question.getSubject().setSubject(subjectDao.findOne(question.getSubject().getId()).getSubject());
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
	public QuestionPool updateQuestion(QuestionPool question) {
		return dao.save(question);
	}

    @Override
	@Transactional
	public QuestionPool updateQuestionUsed(Integer questionId) {

        QuestionPool question;

        // verify that question exists in question pool
        try {
             question = getQuestionById(questionId);
        } catch (NotFoundException ex) {
            throw new ConstraintViolationException(
                    "Question with id " + questionId + " does not exist",null);
        }
        
        question.setUseCount(question.getUseCount()+1);
        Date date = new Date((new java.util.Date()).getTime());
        question.setDateLastUsed(date);
        return dao.save(question);
	}

//DELETE-----------------------------------
	@Override
	@Transactional
	public QuestionPool deleteQuestion(int id) {
		QuestionPool question = dao.findOne(id);
		dao.delete(question);
		return question;
	}

}
