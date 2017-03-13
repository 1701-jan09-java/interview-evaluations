package com.revature.services;

import com.revature.domain.Eval;
import com.revature.domain.EvalComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EvalLogic {
	
	Eval getEvalById(Integer id);
		
	Page<Eval> getEvalsByBatch(Pageable pageable, Integer id, String evalParam);

	Page<Eval> getEvalsByWeek(Pageable pageable, Integer id, Integer num, String evalParam);

	Page<Eval> getEvalsByPerson(Pageable pageable, Integer id, String evalParam);
	
	Page<Eval> getPersonEvalsByWeek(Pageable pageable, Integer id, Integer num, String evalParam);

	Eval createEval(Eval eval);
	
	Eval updateEval(Eval eval, Integer id);
	
	String deleteEval(Integer id);

	EvalComment getCommentById(Integer id);

	EvalComment createComment(EvalComment comment, Integer evalId);

	EvalComment updateComment(EvalComment comment, Integer commentId);

	String deleteComment(Integer id);

}
