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

	@Override
	public Eval getEvalById(int id) {
		Eval eval = dao.findOne(id);
		System.out.println(eval);
		return eval;
	}
	
	@Override
	public ArrayList<Eval> getEvalsByBatch(int id){
		
		ArrayList<Eval> evals = dao.findAllByBatchId(id);
		System.out.println(evals);
		return evals;
	}
	
	@Override
	public ArrayList<Eval> getEvalsByWeek(int id, int num){
		ArrayList<Eval> evals = dao.findAllByWeek(id,num);
		System.out.println(evals);
		return evals;
	}

	@Override
	public ArrayList<Eval> getEvalsByPerson(int id) {
		ArrayList<Eval> evals = dao.findAllByTraineeId(id);
		System.out.println(evals);
		return evals;
	}

	@Override
	public ArrayList<Eval> getPersonEvalsByWeek(int id, int num) {
		ArrayList<Eval> evals = dao.findAllByTraineeIdAndWeek(id,num);
		System.out.println(evals);
		return evals;
	}
}
