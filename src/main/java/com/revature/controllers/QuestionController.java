package com.revature.controllers;

import com.revature.domain.QuestionPool;
import com.revature.services.QuestionLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.*;
import org.springframework.data.web.SortDefault.SortDefaults;

@RestController
@RequestMapping(value="/api/v1/questions")
public class QuestionController {
	
	@Autowired
	QuestionLogic questionLogic;
	
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
	
}
