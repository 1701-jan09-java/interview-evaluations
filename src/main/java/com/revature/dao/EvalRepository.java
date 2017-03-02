package com.revature.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.domain.Eval;

public interface EvalRepository extends JpaRepository<Eval, Integer> {

}
