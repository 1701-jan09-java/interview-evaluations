package com.revature.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.domain.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {
	 
	Batch findBatchByName(String name);
		
	@Override
	Page<Batch> findAll(Pageable pageable);

	Batch findByNameIgnoreCase(String batchName);
}
