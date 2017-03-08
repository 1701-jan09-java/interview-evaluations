package com.revature.services;

import com.revature.repositories.EvalRepository;
import com.revature.repositories.EvalCommentRepository;
import com.revature.domain.Eval;
import com.revature.domain.EvalComment;

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
	

//CREATE----------------------------
	@Override
	@Transactional
	public Eval createEval(Eval eval) {
		dao.save(eval);
		return eval;
	}
	
	@Override
	@Transactional
	public EvalComment createComment(EvalComment comment) {
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
		if (evalParam.equalsIgnoreCase("both")) {
			evals = dao.findAllByBatchId(pageable, id);
			System.out.println(evals);
		} else {
			evals = dao.findAllByBatchIdAndEvalTypeDescriptionIgnoreCase(pageable, id, evalParam);
			System.out.println(evals);
		}
		System.out.println(evals);
		return evals;
	}
	
	@Override
	public Page<Eval> getEvalsByWeek(Pageable pageable, int id, int num, String evalParam){
		Page<Eval> evals;
		if (evalParam.equalsIgnoreCase("both")) {
			evals = dao.findAllByBatchIdAndWeek(pageable, id, num);
		} else {
			evals = dao.findAllByBatchIdAndWeekAndEvalTypeDescriptionIgnoreCase(pageable, id, num, evalParam);
		}
		System.out.println(evals);
		return evals;
	}
	
	@Override
	public Page<Eval> getEvalsByPerson(Pageable pageable, int id, String evalParam) {
		Page<Eval> evals;
		if (evalParam.equalsIgnoreCase("both")) {
			evals = dao.findAllByTraineeId(pageable, id);
		} else {
			evals = dao.findAllByTraineeIdAndEvalTypeDescriptionIgnoreCase(pageable, id, evalParam);
		}
		return evals;
	}
	
	@Override
	public Page<Eval> getPersonEvalsByWeek(Pageable pageable, int id, int num, String evalParam) {
		Page<Eval> evals;
		if (evalParam.equalsIgnoreCase("both")) {
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
	public EvalComment updateComment(EvalComment comment) {
		
		return commentDao.save(comment);
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
		EvalComment comment = commentDao.findOne(id);
		commentDao.delete(comment);
		return comment;
	}
}
