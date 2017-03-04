package com.revature.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.domain.Eval;
import org.springframework.stereotype.Repository;

@Repository
public interface EvalRepository extends JpaRepository<Eval, Integer> {

	ArrayList<Eval> findAllByBatchId(int id);
	
	ArrayList<Eval> findAllByBatchIdAndEvalTypeDescription(int id, String type);

	ArrayList<Eval> findAllByBatchIdAndWeek(int id, int num);
	
	ArrayList<Eval> findAllByBatchIdAndWeekAndEvalTypeDescription(int id, int num, String type);
	
	ArrayList<Eval> findAllByTraineeId(int id);
	
	ArrayList<Eval> findAllByTraineeIdAndEvalTypeDescription(int id, String type);

	ArrayList<Eval> findAllByTraineeIdAndWeek(int id, int num);
	
	ArrayList<Eval> findAllByTraineeIdAndWeekAndEvalTypeDescription(int id, int num, String type);

}
