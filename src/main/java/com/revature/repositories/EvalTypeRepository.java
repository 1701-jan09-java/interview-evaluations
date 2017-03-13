package com.revature.repositories;


import com.revature.domain.EvalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvalTypeRepository extends JpaRepository<EvalType, Integer> {
	
}
