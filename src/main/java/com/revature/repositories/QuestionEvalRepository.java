package com.revature.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.revature.domain.QuestionEval;

@Repository
public interface QuestionEvalRepository extends PagingAndSortingRepository<QuestionEval, Integer> {

}
