package com.revature.services;

import java.util.List;

import com.revature.domain.Eval;

public interface EvalLogic {
	
	Eval getEvalById(int id);
	
	List<Eval> getEvalsByBatch(int id);
	
	List<Eval> getEvalsByWeek(int id, int num);
	
	List<Eval> getEvalsByPerson(int id);

	List<Eval> getPersonEvalsByWeek(int id, int num);
}
