package com.revature.services;

import com.revature.repositories.EvalRepository;
import com.revature.repositories.EvalCommentRepository;
import com.revature.domain.Eval;
import com.revature.domain.EvalComment;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	

//CREATE----------------------------
	@Override
	@Transactional
	public Eval createEval(Eval eval) {
		dao.save(eval);
		return eval;
	}
	
	@Override
	@Transactional
	public EvalComment createComment(EvalComment comment, Integer evalId) {
		
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
