package com.revature.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.domain.Eval;
import org.springframework.stereotype.Repository;

@Repository
public interface EvalRepository extends JpaRepository<Eval, Integer> {

	ArrayList<Eval> findAllByBatchId(int id);

	ArrayList<Eval> findAllByWeek(int id, int num);
	
	ArrayList<Eval> findAllByTraineeId(int id);

	ArrayList<Eval> findAllByTraineeIdAndWeek(int id, int num);

}
