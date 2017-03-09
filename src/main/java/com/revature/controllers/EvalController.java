package com.revature.controllers;

import com.revature.domain.Eval;
import com.revature.domain.EvalComment;
import com.revature.services.EvalLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
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
	
//EVAL CUD---------------------------------------------
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public ResponseEntity<Eval> addEval(@RequestBody Eval eval){
		
		return ResponseEntity.ok(evalLogic.createEval(eval));
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
	public ResponseEntity<Eval> updateEval(@RequestBody Eval eval, @PathVariable ("id") Integer id){
		
		Eval currEval = evalLogic.getEvalById(id);
		
		if(currEval == null){
			System.out.println("Eval with id " + id + " not found");
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if(eval.getBatch() != null){
			currEval.setBatch(eval.getBatch());
		}
		if(eval.getDate() != null){
			currEval.setDate(eval.getDate());
		}
		if(eval.getEvalType() != null){
			currEval.setEvalType(eval.getEvalType());
		}
		if(eval.getTrainee() != null){
			currEval.setTrainee(eval.getTrainee());
		}
		if(eval.getWeek() != null){
			currEval.setWeek(eval.getWeek());
		}
		
		return ResponseEntity.ok(evalLogic.updateEval(currEval));
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public ResponseEntity<Eval> deleteEval(@PathVariable ("id") int id){
		
		Eval currEval = evalLogic.getEvalById(id);
		
		if(currEval == null){
			System.out.println("Eval with id " + id + " not found");
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(evalLogic.deleteEval(id));
	}
	
	
//COMMENT CUD------------------------------------------
	@RequestMapping(method = RequestMethod.POST, value = "/comments")
	public ResponseEntity<EvalComment> addEvalComment(@RequestBody EvalComment comment){
		return ResponseEntity.ok(evalLogic.createComment(comment));
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/comments/update/{id}")
	public ResponseEntity<EvalComment> updateEvalComments(@RequestBody EvalComment comment, @PathVariable ("id") Integer id){
		
		EvalComment currComment = evalLogic.getCommentById(id);
		
		if(currComment == null){
			System.out.println("Comment with id " + id + " not found");
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if(comment.getCommentText() != null){
			currComment.setCommentText(comment.getCommentText());
		}
		if(comment.getEval() != null){
			currComment.setEval(comment.getEval());
		}
		
		return ResponseEntity.ok(evalLogic.updateComment(currComment));
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/comments/delete/{id}")
	public ResponseEntity<EvalComment> deleteEvalComment(@PathVariable ("id") int id){
		
		EvalComment currComment = evalLogic.getCommentById(id);
		
		if(currComment == null){
			System.out.println("Comment with id " + id + " not found");
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(evalLogic.deleteComment(id));
	}
}
