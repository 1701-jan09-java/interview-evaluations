package com.revature.controllers;

import com.revature.domain.Eval;
import com.revature.services.EvalLogic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Eval> getEvaluation(@PathVariable("id") Integer id) {

		return ResponseEntity.ok(evalLogic.getEvalById(id));
	}

	@RequestMapping(method = RequestMethod.GET, value = "batches/{id}/week/{num}")
	public ResponseEntity<ArrayList<Eval>> getEvalsByWeek(@PathVariable("id") Integer id, @PathVariable("num") Integer num){
		return ResponseEntity.ok(evalLogic.getEvalsByWeek(id,num));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "batches/{id}")
	public ResponseEntity<ArrayList<Eval>> getEvalsByBatch(@PathVariable("id") Integer id){
		return ResponseEntity.ok(evalLogic.getEvalsByBatch(id));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "trainees/{id}")
	public ResponseEntity<ArrayList<Eval>> getEvalsByPerson(@PathVariable("id") Integer id){
		return ResponseEntity.ok(evalLogic.getEvalsByPerson(id));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "trainees/{id}/week/{num}")
	public ResponseEntity<ArrayList<Eval>> getTraineeEvalsByWeek(@PathVariable("id") Integer id, @PathVariable("num") Integer num){
		return ResponseEntity.ok(evalLogic.getPersonEvalsByWeek(id,num));
	}
}
