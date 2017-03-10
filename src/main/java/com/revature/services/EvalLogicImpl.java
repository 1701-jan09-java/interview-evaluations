package com.revature.services;

import com.revature.domain.Eval;
import com.revature.domain.EvalComment;
import com.revature.repositories.EvalCommentRepository;
import com.revature.repositories.EvalRepository;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
public class EvalLogicImpl implements EvalLogic {

	@Autowired
	private EvalRepository dao;
	
	@Autowired
	private EvalCommentRepository commentDao;
	
	@PersistenceContext
    private EntityManager entityManager;
	

//CREATE----------------------------
	@Override
	@Transactional
	public Eval createEval(Eval eval) {
		
		System.out.println(eval);
		
		if(eval.getId() != null || eval.getId() != 0) {
			throw new ConstraintViolationException("Id is automatically generated - do not include in post request", null);
		}
		
		if(eval.getTrainee() == null || eval.getTrainee().getId() == 0){
			throw new ConstraintViolationException("Missing required field trainee.id (Integer)", null);
		}
		
		if(eval.getWeek() == null || eval.getWeek() == 0){
			throw new ConstraintViolationException("Missing required field week (Integer)", null);
		}
		
		if(eval.getEvalType() == null || eval.getEvalType().getId() == 0){
			throw new ConstraintViolationException("Missing required field evalType.id (Integer)", null);
		}
		
		if(eval.getBatch() == null || eval.getBatch().getId() == 0){
			throw new ConstraintViolationException("Missing required field batch.id (Integer)", null);
		}
		
		eval.setDate(new java.sql.Date((new Date()).getTime()));
		
		// Set eval property of questions and qEval property of question comments
		// This is required because these fields are not auto-generated in json mapping
		if (eval.getQuestions() != null) {
			eval.getQuestions().forEach((question) -> {
				question.setEval(eval);
				// also set qEval of question comments
				if (question.getComments() != null) {
					question.getComments().forEach((comment) -> {
						comment.setQuestionEval(question);
					});
				}
			});
		}
		
		// Set eval property of comments so cascade works
		if (eval.getComments() != null) {
			eval.getComments().forEach((comment) -> {
				comment.setEval(eval);
			});
		}
		
		dao.save(eval);
		System.out.println(eval);
		
		// Bypass cache for fresh version with all fields filled in
		entityManager.flush();
		entityManager.refresh(eval);
		return eval;
	}
	
	@Override
	@Transactional
	public EvalComment createComment(EvalComment comment, Integer evalId) {
		
		if(comment.getId() != null || comment.getId() != 0) {
			throw new ConstraintViolationException("Id is automatically generated - do not include in post request", null);
		}
		
		if (comment.getCommentText() == null) {
			throw new ConstraintViolationException("Missing required field commentText (String)", null);
		}
		
		comment.setEval(dao.findOne(evalId));
		System.out.println(comment.getEval());
		if (comment.getEval() == null) {
			throw new ConstraintViolationException("Evaluation " + evalId + " does not exist", null);
		}
		
		commentDao.save(comment);
		return comment;
	}

//RETRIEVE--------------------------
	@Override
	public Eval getEvalById(int id) {
		Eval eval = dao.findOne(id);
		return eval;
	}
	
	@Override
	public Page<Eval> getEvalsByBatch(Pageable pageable, int id, String evalParam){
		Page<Eval> evals;
		if ("both".equalsIgnoreCase(evalParam)) {
			evals = dao.findAllByBatchId(pageable, id);
		} else {
			evals = dao.findAllByBatchIdAndEvalTypeDescriptionIgnoreCase(pageable, id, evalParam);
		}
		return evals;
	}
	
	@Override
	public Page<Eval> getEvalsByWeek(Pageable pageable, int id, int num, String evalParam){
		Page<Eval> evals;
		if ("both".equalsIgnoreCase(evalParam)) {
			evals = dao.findAllByBatchIdAndWeek(pageable, id, num);
		} else {
			evals = dao.findAllByBatchIdAndWeekAndEvalTypeDescriptionIgnoreCase(pageable, id, num, evalParam);
		}
		return evals;
	}
	
	@Override
	public Page<Eval> getEvalsByPerson(Pageable pageable, int id, String evalParam) {
		Page<Eval> evals;
		if ("both".equalsIgnoreCase(evalParam)) {
			evals = dao.findAllByTraineeId(pageable, id);
		} else {
			evals = dao.findAllByTraineeIdAndEvalTypeDescriptionIgnoreCase(pageable, id, evalParam);
		}
		return evals;
	}
	
	@Override
	public Page<Eval> getPersonEvalsByWeek(Pageable pageable, int id, int num, String evalParam) {
		Page<Eval> evals;
		if ("both".equalsIgnoreCase(evalParam)) {
			evals = dao.findAllByTraineeIdAndWeek(pageable, id, num);
		} else {
			evals = dao.findAllByTraineeIdAndWeekAndEvalTypeDescriptionIgnoreCase(pageable, id, num, evalParam);
		}
		System.out.println(evals);
		return evals;
	}
	
	@Override
	public EvalComment getCommentById(Integer id) {
		return commentDao.findOne(id);
	}
	
//UPDATE-------------------------------	
	@Override
	@Transactional
	public Eval updateEval(Eval eval) {
		
		return dao.save(eval);
	}
	
	@Override
	@Transactional
	public EvalComment updateComment(EvalComment comment, Integer id) {
		
		EvalComment currComment = getCommentById(id);
		
		if(currComment == null){
	        throw new ConstraintViolationException("Evaluation " + id + " does not exist", null);
		}
		
		if(comment.getCommentText() != null){
			currComment.setCommentText(comment.getCommentText());
		}
		if(comment.getEval() != null){
			currComment.setEval(comment.getEval());
		}
		if (comment.getCommentText() == null) {
			throw new ConstraintViolationException("Missing required field commentText (String)", null);
		}
		
		return commentDao.save(currComment);
	}
	
//DELETE-------------------------------
	@Override
	@Transactional
	public Eval deleteEval(int id) {
		Eval eval = dao.findOne(id);
		dao.delete(eval);
		return eval;
	}
	
	@Override
	@Transactional
	public EvalComment deleteComment(int id) {
		EvalComment comment = getCommentById(id);
		
		if(comment == null){
	        throw new ConstraintViolationException("Comment with id " + id + " not found", null);
		}
		
		commentDao.delete(comment);
		return comment;
	}
	
}
