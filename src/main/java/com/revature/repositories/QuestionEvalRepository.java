package com.revature.repositories;

import com.revature.domain.QuestionEval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionEvalRepository extends JpaRepository<QuestionEval, Integer> {

}
