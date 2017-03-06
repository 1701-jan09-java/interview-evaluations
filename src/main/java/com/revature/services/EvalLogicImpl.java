package com.revature.services;

import com.revature.repositories.EvalRepository;
import com.revature.domain.Eval;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
public class EvalLogicImpl implements EvalLogic {

	@Autowired
	private EvalRepository dao;
	

//CREATE----------------------------
	@Override
	@Transactional
	public Eval createEval(Eval eval) {
		dao.save(eval);
		return eval;
	}

//RETRIEVE--------------------------
	@Override
	public Eval getEvalById(int id) {
		Eval eval = dao.findOne(id);
		try {
			System.out.println(eval);
		} catch (Exception e) {e.printStackTrace();}
		return eval;
	}
	
	@Override
	public ArrayList<Eval> getEvalsByBatch(int id, String evalParam){
		ArrayList<Eval> evals;
		if (evalParam.equalsIgnoreCase("both")) {
			evals = dao.findAllByBatchId(id);
		} else {
			evals = dao.findAllByBatchIdAndEvalTypeDescription(id, matchEvalType(evalParam));
		}
		System.out.println(evals);
		return evals;
	}
	
	@Override
	public ArrayList<Eval> getEvalsByWeek(int id, int num, String evalParam){
		ArrayList<Eval> evals;
		if (evalParam.equalsIgnoreCase("both")) {
			evals = dao.findAllByBatchIdAndWeek(id,num);
		} else {
			evals = dao.findAllByBatchIdAndWeekAndEvalTypeDescription(id, num, matchEvalType(evalParam));
		}
		System.out.println(evals);
		return evals;
	}
	
	@Override
	public ArrayList<Eval> getEvalsByPerson(int id, String evalParam) {
		ArrayList<Eval> evals;
		if (evalParam.equalsIgnoreCase("both")) {
			evals = dao.findAllByTraineeId(id);
		} else {
			evals = dao.findAllByTraineeIdAndEvalTypeDescription(id, matchEvalType(evalParam));
		}
		return evals;
	}

	@Override
	public ArrayList<Eval> getPersonEvalsByWeek(int id, int num, String evalParam) {
		ArrayList<Eval> evals;
		if (evalParam.equalsIgnoreCase("both")) {
			evals = dao.findAllByTraineeIdAndWeek(id,num);
		} else {
			evals = dao.findAllByTraineeIdAndWeekAndEvalTypeDescription(id, num, matchEvalType(evalParam));
		}	
		System.out.println(evals);
		return evals;
	}
	
	private String matchEvalType(String evalParam) {
		if (evalParam.equalsIgnoreCase("trainer")) {
			return "Trainer";
		} else if (evalParam.equalsIgnoreCase("qc")) {
			return "QC";
		} else throw new IllegalArgumentException("EvalType not recognized");
	}
	
//UPDATE-------------------------------
	
//DELETE-------------------------------
}
