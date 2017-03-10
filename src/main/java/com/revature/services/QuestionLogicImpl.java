package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.domain.QuestionComment;
import com.revature.domain.QuestionPool;
import com.revature.repositories.QuestionCommentRepository;
import com.revature.repositories.QuestionEvalRepository;
import com.revature.repositories.QuestionRepository;
import com.revature.repositories.SubjectRepository;
import javax.validation.ConstraintViolationException;
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
	private QuestionEvalRepository qEvalDao;
	
	@Autowired
	private QuestionCommentRepository commentDao;
	
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

	@Override
	@Transactional
	public QuestionComment createComment(QuestionComment comment) {
		return commentDao.save(comment);
	}
	
	@Override
	@Transactional
	public QuestionComment createComment(QuestionComment comment, Integer questionId) {
		comment.setQuestionEval(qEvalDao.findOne(questionId));
		return commentDao.save(comment);
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
		return dao.findOne(id);
	}
	
	@Override
	public QuestionComment getCommentById(Integer id) {
		return commentDao.findOne(id);
	}

//UPDATE-----------------------------------
	@Override
	@Transactional
	public QuestionPool updateQuestion(QuestionPool question) {
		return dao.save(question);
	}
	
	@Override
	public QuestionComment updateComment(QuestionComment comment) {
		return commentDao.save(comment);
	}

//DELETE-----------------------------------
	@Override
	@Transactional
	public QuestionPool deleteQuestion(int id) {
		QuestionPool question = dao.findOne(id);
		dao.delete(question);
		return question;
	}

	@Override
	public QuestionComment deleteComment(int id) {
		QuestionComment comment = commentDao.findOne(id);
		commentDao.delete(comment);
		System.out.println(comment);
		return comment;
	}

}
