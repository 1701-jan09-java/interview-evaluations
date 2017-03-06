package com.revature.services;

import com.revature.repositories.EvalRepository;
import com.revature.domain.Eval;

import java.util.ArrayList;
import java.util.List;

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
		return dao.findOne(id);

	}
	
	@Override
	public List<Eval> getEvalsByBatch(int id){
		
		return dao.findAllByBatchId(id);
	}
	
	@Override
	public List<Eval> getEvalsByWeek(int id, int num){
		return dao.findAllByWeek(id,num);

	}

	@Override
	public List<Eval> getEvalsByPerson(int id) {
		return dao.findAllByTraineeId(id);

	}

	@Override
	public List<Eval> getPersonEvalsByWeek(int id, int num) {
		return dao.findAllByTraineeIdAndWeek(id,num);

	}
}
