package com.revature.controllers;

import com.revature.domain.Eval;
import com.revature.services.EvalLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/evaluations")
public class EvalController {

	@Autowired
	private EvalLogic evalLogic;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Eval> getEvaluation(@PathVariable("id") Integer id) {

		return ResponseEntity.ok(evalLogic.getEvalById(id));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/batches/{id}/week/{num}")
	public ResponseEntity<Page<Eval>> getEvalsByWeek(
			@PathVariable("id") Integer id
			, @PathVariable("num") Integer num
			, @RequestParam(defaultValue="both") String type
			, @PageableDefault(size = 10)
				@SortDefault.SortDefaults({@SortDefault(sort = "trainee.id"), @SortDefault(sort = "evalType")})
				Pageable pageable
	){
		return ResponseEntity.ok(evalLogic.getEvalsByWeek(pageable,id,num,type));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/batches/{id}")
	public ResponseEntity<Page<Eval>> getEvalsByBatch(
			@PathVariable("id") Integer id
			, @RequestParam(defaultValue="both") String type
			, @PageableDefault(size = 10)
				@SortDefault.SortDefaults({@SortDefault(sort = "trainee.id"), @SortDefault(sort = "evalType")})
				Pageable pageable
	){
		return ResponseEntity.ok(evalLogic.getEvalsByBatch(pageable,id,type));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/trainees/{id}")
	public ResponseEntity<Page<Eval>> getEvalsByPerson(
			@PathVariable("id") Integer id
			, @RequestParam(defaultValue="both") String type
			, @PageableDefault(size = 10)
				@SortDefault.SortDefaults({@SortDefault(sort = "trainee.id"), @SortDefault(sort = "evalType")})
				Pageable pageable
	){
		return ResponseEntity.ok(evalLogic.getEvalsByPerson(pageable,id,type));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/trainees/{id}/week/{num}")
	public ResponseEntity<Page<Eval>> getTraineeEvalsByWeek(
			@PathVariable("id") Integer id
			, @PathVariable("num") Integer num
			, @RequestParam(defaultValue="both") String type
			, @PageableDefault(size = 10)
				@SortDefault.SortDefaults({@SortDefault(sort = "trainee.id"), @SortDefault(sort = "evalType")})
				Pageable pageable
	){
		return ResponseEntity.ok(evalLogic.getPersonEvalsByWeek(pageable,id,num,type));
	}
}
