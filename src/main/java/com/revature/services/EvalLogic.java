package com.revature.services;

import com.revature.domain.Eval;
import com.revature.domain.EvalComment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EvalLogic {
	
	Eval getEvalById(int id);
		
	Page<Eval> getEvalsByBatch(Pageable pageable, int id, String evalParam);

	Page<Eval> getEvalsByWeek(Pageable pageable, int id, int num, String evalParam);

	Page<Eval> getEvalsByPerson(Pageable pageable, int id, String evalParam);
	
	Page<Eval> getPersonEvalsByWeek(Pageable pageable, int id, int num, String evalParam);

	Eval createEval(Eval eval);
	
	Eval updateEval(Eval eval);
	
	Eval deleteEval(int id);

	EvalComment getCommentById(Integer id);

	EvalComment createComment(EvalComment comment, Integer evalId);

	EvalComment updateComment(EvalComment comment, Integer commentId);

	EvalComment deleteComment(int id);
}
