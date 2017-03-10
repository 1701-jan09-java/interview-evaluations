package com.revature.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.revature.domain.EvalComment;

@Repository
public interface EvalCommentRepository extends PagingAndSortingRepository<EvalComment, Integer>{
	
}
