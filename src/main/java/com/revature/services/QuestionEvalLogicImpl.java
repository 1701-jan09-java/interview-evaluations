package com.revature.services;

import com.revature.domain.QuestionComment;
import com.revature.domain.QuestionEval;
import com.revature.repositories.EvalRepository;
import com.revature.repositories.QuestionCommentRepository;
import com.revature.repositories.QuestionEvalRepository;
import com.revature.validation.JsonValidation;
import com.revature.validation.exceptions.NotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
public class QuestionEvalLogicImpl implements QuestionEvalLogic{
	
	@Autowired
	private QuestionEvalRepository dao;
	
	@Autowired
	private QuestionCommentRepository commentDao;
	
	@Autowired
	private EvalRepository evalDao;

    @Autowired
    private QuestionLogic questionLogic;

    @Autowired
    private JsonValidation validation;
	
	@PersistenceContext
    private EntityManager entityManager;
	
//CREATE-----------------------------------

    @Override
	@Transactional
	public QuestionEval createQuestionEval(QuestionEval qEval, Integer evalId) {
        validation.validateQuestionEvaluationFieldsExistingEval(qEval,evalId);
		
		qEval.setEval(evalDao.findOne(evalId));
        if (qEval.getEval() == null) {
            throw new ConstraintViolationException("Evaluation with id " + evalId + " does not exist", null);
        }

		// Set eval property of comments so cascade works
		if (qEval.getComments() != null) {
			qEval.getComments().forEach((comment) -> {
				comment.setQuestionEval(qEval);
			});
		}

        // increment question usage count and update date last used
        questionLogic.updateQuestionUsed(qEval.getQuestionPool().getId());

		dao.saveAndFlush(qEval);
		entityManager.refresh(qEval);

		return qEval;
	}

	@Override
	@Transactional
	public QuestionComment createComment(QuestionComment comment, Integer questionId) {

        comment.setQuestionEval(dao.findOne(questionId));

        if(comment.getId() != null && comment.getId() != 0) {
			throw new ConstraintViolationException("Id is automatically generated - do not include in post request", null);
		}

        if (comment.getQuestionEval() == null) {
            throw new ConstraintViolationException("Question Evaluation with id " + questionId + " does not exist", null);
        }

        if (comment.getCommentText() == null) {
            throw new ConstraintViolationException("Missing required field commentText (String)", null);
        }

		return commentDao.save(comment);
	}

//RETRIEVE---------------------------------
	@Override
	public QuestionEval getQuestionEvalById(Integer id) {
        
		QuestionEval qEval = dao.findOne(id);

        if (qEval == null) {
            throw new NotFoundException("Question Evaluation with id " + id + " not found");
        }

        return qEval;
	}

	@Override
	public QuestionComment getCommentById(Integer id) {
		
        QuestionComment comment = commentDao.findOne(id);

        if(comment == null){
	        throw new NotFoundException("Comment with id " + id + " not found");
		}

        return comment;
	}

//UPDATE-----------------------------------
	@Override
	public QuestionEval updateQuestionEval(QuestionEval qEval, Integer id) {
        QuestionEval currQEval = getQuestionEvalById(id);

		if(qEval.getCommunicationScore() != null){
			currQEval.setCommunicationScore(qEval.getCommunicationScore());
		}

		if(qEval.getKnowledgeScore() != null){
			currQEval.setKnowledgeScore(qEval.getKnowledgeScore());
		}

		validation.validateScores(currQEval);
		
        dao.saveAndFlush(currQEval);
        entityManager.refresh(currQEval);

        return currQEval;
	}

	@Override
	public QuestionComment updateComment(QuestionComment comment, Integer id) {

        QuestionComment currComment = getCommentById(id);

		if(comment.getCommentText() != null){
			currComment.setCommentText(comment.getCommentText());
		}

		if(comment.getQuestionEval() != null){
			currComment.setQuestionEval(comment.getQuestionEval());
		}
        
		return commentDao.save(currComment);
	}

//DELETE-----------------------------------
	@Override
	public String deleteQuestionEval(Integer qEvalId) {
        QuestionEval qEval = getQuestionEvalById(qEvalId);
        dao.delete(qEval);
		return "Question Eval: " + qEvalId + " - DELETED";
	}

	@Override
	public String deleteComment(Integer qCommentId) {
        QuestionComment qComment = getCommentById(qCommentId);
		commentDao.delete(qComment);
		return "Question Comment: " + qCommentId + " - DELETED";
	}
}
