package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.domain.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {
	 
	Batch findBatchByName(String name);
	
	Batch findBatchById(int id);
	@Override
	void delete(Batch batchName);
	
	@Override
	Batch save(Batch batchName);
	
	@Override
	List<Batch> findAll();
	
//	@Modifying
//	@Query("update ie_batch Batch set Batch.b_name = ?1, where Batch.b_id = ?2")
//	void setBatchInfoById(String name, int id);

}
