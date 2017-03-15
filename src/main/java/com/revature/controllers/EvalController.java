package com.revature.controllers;

import com.revature.domain.Eval;
import com.revature.domain.EvalComment;
import com.revature.services.EvalLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/evaluations")
public class EvalController {

	@Autowired
	private EvalLogic evalLogic;

	@RequestMapping(method = RequestMethod.GET, value = "/{evalId}")
	public ResponseEntity<Eval> getEvaluation(@PathVariable Integer evalId) {
		return ResponseEntity.ok(evalLogic.getEvalById(evalId));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/batches/{batchId}/week/{weekNumber}")
	public ResponseEntity<Page<Eval>> getEvalsByWeek(
			@PathVariable Integer batchId
			, @PathVariable Integer weekNumber
			, @RequestParam(defaultValue="both") String evalType
			, @PageableDefault(size = 10)
				@SortDefault.SortDefaults({@SortDefault(sort = "trainee.id"), @SortDefault(sort = "evalType")})
				Pageable pageable
	){
		return ResponseEntity.ok(evalLogic.getEvalsByWeek(pageable,batchId,weekNumber,evalType));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/batches/{batchId}")
	public ResponseEntity<Page<Eval>> getEvalsByBatch(
			@PathVariable Integer batchId
			, @RequestParam(defaultValue="both") String evalType
			, @PageableDefault(size = 10)
				@SortDefault.SortDefaults({@SortDefault(sort = "trainee.id"), @SortDefault(sort = "evalType")})
				Pageable pageable
	){
		return ResponseEntity.ok(evalLogic.getEvalsByBatch(pageable,batchId,evalType));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/trainees/{traineeId}")
	public ResponseEntity<Page<Eval>> getEvalsByPerson(
			@PathVariable Integer traineeId
			, @RequestParam(defaultValue="both") String evalType
			, @PageableDefault(size = 10)
				@SortDefault.SortDefaults({@SortDefault(sort = "trainee.id"), @SortDefault(sort = "evalType")})
				Pageable pageable
	){
		return ResponseEntity.ok(evalLogic.getEvalsByPerson(pageable,traineeId,evalType));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/trainees/{traineeId}/week/{weekNumber}")
	public ResponseEntity<Page<Eval>> getTraineeEvalsByWeek(
			@PathVariable Integer traineeId
			, @PathVariable Integer weekNumber
			, @RequestParam(defaultValue="both") String evalType
			, @PageableDefault(size = 10)
				@SortDefault.SortDefaults({@SortDefault(sort = "trainee.id"), @SortDefault(sort = "evalType")})
				Pageable pageable
	){
		return ResponseEntity.ok(evalLogic.getPersonEvalsByWeek(pageable,traineeId,weekNumber,evalType));
	}
	
//EVAL CUD---------------------------------------------
	@RequestMapping(method = RequestMethod.POST, value = "")
	public ResponseEntity<Eval> addEval(@RequestBody Eval eval){
		return ResponseEntity.ok(evalLogic.createEval(eval));
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "{evalId}")
	public ResponseEntity<Eval> updateEval(@RequestBody Eval eval, @PathVariable Integer evalId){
		return ResponseEntity.ok(evalLogic.updateEval(eval, evalId));
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "{evalId}")
	public ResponseEntity<String> deleteEval(@PathVariable Integer evalId){
		return ResponseEntity.ok(evalLogic.deleteEval(evalId));
	}
	
	
//COMMENT CUD------------------------------------------
	@RequestMapping(method = RequestMethod.POST, value = "{evalId}/comments")
	public ResponseEntity<EvalComment> addEvalComment(@RequestBody EvalComment comment, @PathVariable Integer evalId){
		return ResponseEntity.ok(evalLogic.createComment(comment, evalId));
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "{evalId}/comments/{commentId}")
	public ResponseEntity<EvalComment> updateEvalComments(@RequestBody EvalComment comment, @PathVariable Integer commentId){
		return ResponseEntity.ok(evalLogic.updateComment(comment, commentId));
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "{evalId}/comments/{commentId}")
	public ResponseEntity<String> deleteEvalComment(@PathVariable Integer commentId){
		return ResponseEntity.ok(evalLogic.deleteComment(commentId));
	}
}
