package com.revature.controllers;

import com.revature.domain.QuestionPool;
import com.revature.services.QuestionLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.*;
import org.springframework.data.web.SortDefault.SortDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value="/api/v1/questions")
public class QuestionController {
	
	@Autowired
	private QuestionLogic questionLogic;
	
	@RequestMapping(method = RequestMethod.GET, value="{questionId}")
	public ResponseEntity<QuestionPool> getQuestionById(@PathVariable Integer questionId){
		return ResponseEntity.ok(questionLogic.getQuestionById(questionId));
	}
	
	@RequestMapping(method = RequestMethod.GET, value="")
	public ResponseEntity<Page<QuestionPool>> getAllQuestions(
			@RequestParam(defaultValue="all") String subject
			, @PageableDefault(size = 10)
				@SortDefaults({@SortDefault(sort = "subject"), @SortDefault(sort = "id")})
				Pageable pageable 
		){
		return ResponseEntity.ok(questionLogic.getAllQuestions(pageable, subject));
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/search")
	public ResponseEntity<Page<QuestionPool>> searchQuestions(
			@RequestParam(defaultValue="all") String subject
			, @PageableDefault(size = 10)
				@SortDefaults({@SortDefault(sort = "subject"), @SortDefault(sort = "id")})
				Pageable pageable 
			, @RequestParam(defaultValue="") String searchText
		){
		
		return ResponseEntity.ok(questionLogic.searchQuestions(pageable,searchText, subject));
	}
	
//QUESTIONPOOL CUD------------------------------------
	@RequestMapping(method = RequestMethod.POST, value="")
	public ResponseEntity<QuestionPool> addQuestion(@RequestBody QuestionPool question){
		return ResponseEntity.ok(questionLogic.createQuestion(question));
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "{questionId}")
	public ResponseEntity<QuestionPool> updateQuestion(@RequestBody QuestionPool question, @PathVariable Integer questionId){
		return ResponseEntity.ok(questionLogic.updateQuestion(question, questionId));
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "{questionId}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Integer questionId){
		return ResponseEntity.ok(questionLogic.deleteQuestion(questionId));
	}
}
