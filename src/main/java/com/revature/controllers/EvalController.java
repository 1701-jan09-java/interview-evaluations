package com.revature.controllers;

import com.revature.domain.Eval;
import com.revature.services.EvalLogic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/")
public class EvalController {

	@Autowired
	private EvalLogic evalLogic;

	@RequestMapping(method = RequestMethod.GET, value = "evaluations/{id}")
	public Eval getEvaluation(@PathVariable("id") Integer id) {

		Eval eval = evalLogic.getEvalById(id);
		return eval;
	}

	@RequestMapping(method = RequestMethod.GET, value = "batches/{id}/week/{num}")
	public ArrayList<Eval> getEvalsByWeek(@PathVariable("id") Integer id, @PathVariable("num") Integer num){
		
		ArrayList<Eval> evals = evalLogic.getEvalsByWeek(id,num);
		return evals;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "batches/{id}")
	public ArrayList<Eval> getEvalsByBatch(@PathVariable("id") Integer id){
		
		ArrayList<Eval> evals = evalLogic.getEvalsByBatch(id);
		return evals;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "trainees/{id}")
	public ArrayList<Eval> getEvalsByPerson(@PathVariable("id") Integer id){
		
		ArrayList<Eval> evals = evalLogic.getEvalsByPerson(id);
		return evals;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "trainees/{id}/week/{num}")
	public ArrayList<Eval> getTraineeEvalsByWeek(@PathVariable("id") Integer id, @PathVariable("num") Integer num){
		
		ArrayList<Eval> evals = evalLogic.getPersonEvalsByWeek(id,num);
		return evals;
	}
}
