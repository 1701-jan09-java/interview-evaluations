package com.revature.services;

import java.util.ArrayList;

import com.revature.domain.Eval;

public interface EvalLogic {
	
	Eval getEvalById(int id);
	
	ArrayList<Eval> getEvalsByBatch(int id);
}
