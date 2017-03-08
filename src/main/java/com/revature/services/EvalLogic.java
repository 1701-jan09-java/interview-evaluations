package com.revature.services;

import java.util.ArrayList;

import com.revature.domain.Eval;

public interface EvalLogic {
	
	Eval getEvalById(int id);
	
	ArrayList<Eval> getEvalsByBatch(int id, String evalParam);
	
	ArrayList<Eval> getEvalsByWeek(int id, int num, String evalParam);
	
	ArrayList<Eval> getEvalsByPerson(int id, String evalParam);

	ArrayList<Eval> getPersonEvalsByWeek(int id, int num, String evalParam);

	Eval createEval(Eval eval);
	
	Eval updateEval(Eval eval);
	
	Eval deleteEval(Eval eval);
}
