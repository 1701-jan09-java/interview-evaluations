package com.revature.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.domain.QuestionPool;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionPool, Integer> {

	ArrayList<QuestionPool> findAllBySubject(String type);

}
