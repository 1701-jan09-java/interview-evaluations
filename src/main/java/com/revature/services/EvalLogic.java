package com.revature.services;

import java.util.ArrayList;

import com.revature.domain.Eval;

public interface EvalLogic {
	
	Eval getEvalById(int id);
	
	ArrayList<Eval> getEvalsByBatch(int id);
	
	ArrayList<Eval> getEvalsByWeek(int id, int num);
	
	ArrayList<Eval> getEvalsByPerson(int id);

	ArrayList<Eval> getPersonEvalsByWeek(int id, int num);
}
