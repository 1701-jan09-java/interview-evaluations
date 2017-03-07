package com.revature.services;

import com.revature.repositories.EvalRepository;
import com.revature.domain.Eval;

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
	

//CREATE----------------------------

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
	
//UPDATE-------------------------------
	
//DELETE-------------------------------
}
