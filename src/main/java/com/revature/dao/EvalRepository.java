package com.revature.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.domain.Eval;

public interface EvalRepository extends JpaRepository<Eval, Integer> {

	ArrayList<Eval> findAllByBatchId(int id);

	ArrayList<Eval> findAllByWeekNumber(int id, int num);
	
	ArrayList<Eval> findAllByPersonId(int id);

	ArrayList<Eval> findAllByPersonIdAndWeekNumber(int id, int num);

}
