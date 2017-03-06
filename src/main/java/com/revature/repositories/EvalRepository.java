package com.revature.repositories;

import java.util.ArrayList;


import com.revature.domain.Eval;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvalRepository extends PagingAndSortingRepository<Eval, Integer> {

	ArrayList<Eval> findAllByBatchId(int id);
	
	ArrayList<Eval> findAllByBatchIdAndEvalTypeDescription(int id, String type);

	ArrayList<Eval> findAllByBatchIdAndWeek(int id, int num);
	
	ArrayList<Eval> findAllByBatchIdAndWeekAndEvalTypeDescription(int id, int num, String type);
	
	ArrayList<Eval> findAllByTraineeId(int id);
	
	ArrayList<Eval> findAllByTraineeIdAndEvalTypeDescription(int id, String type);

	ArrayList<Eval> findAllByTraineeIdAndWeek(int id, int num);
	
	ArrayList<Eval> findAllByTraineeIdAndWeekAndEvalTypeDescription(int id, int num, String type);

}
