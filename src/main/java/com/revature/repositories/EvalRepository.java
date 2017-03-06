package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.domain.Eval;

@Repository
public interface EvalRepository extends JpaRepository<Eval, Integer> {

	List<Eval> findAllByBatchId(int id);

	List<Eval> findAllByWeek(int id, int num);
	
	List<Eval> findAllByTraineeId(int id);

	List<Eval> findAllByTraineeIdAndWeek(int id, int num);

}
