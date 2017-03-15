package com.revature.repositories;

import com.revature.domain.EvalComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvalCommentRepository extends JpaRepository<EvalComment, Integer>{
	
}
