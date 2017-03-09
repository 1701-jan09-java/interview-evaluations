package com.revature.controllers;

import com.revature.domain.QuestionComment;
import com.revature.domain.QuestionPool;
import com.revature.services.QuestionLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<QuestionPool> getQuestionById(@PathVariable("id") Integer id){
		return ResponseEntity.ok(questionLogic.getQuestionById(id));
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
		
		System.out.println(question);
		return ResponseEntity.ok(questionLogic.createQuestion(question));
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
	public ResponseEntity<QuestionPool> updateQuestion(@RequestBody QuestionPool question, @PathVariable ("id") Integer id){
		
		QuestionPool currQuestion = questionLogic.getQuestionById(id);
		
		if(currQuestion == null){
			System.out.println("Question with id " + id + " not found");
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if(question.getQuestionText() != null){
			currQuestion.setQuestionText(question.getQuestionText());
		}
		if(question.getMaxCommunicationScore() != null){
			currQuestion.setMaxCommunicationScore(question.getMaxCommunicationScore());
		}
		if(question.getMaxKnowledgeScore() != null){
			currQuestion.setMaxKnowledgeScore(question.getMaxKnowledgeScore());
		}
		if(question.getSubject() != null){
			currQuestion.setSubject(question.getSubject());
		}
		if(question.getUseCount() != null){
			currQuestion.setUseCount(question.getUseCount());
		}
		if(question.getDateLastUsed() != null){
			currQuestion.setDateLastUsed(question.getDateLastUsed());
		}
		
		return ResponseEntity.ok(questionLogic.updateQuestion(currQuestion));
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public ResponseEntity<QuestionPool> deleteQuestion(@PathVariable ("id") int id){
		QuestionPool currQuestion = questionLogic.getQuestionById(id);
		
		if(currQuestion == null){
			System.out.println("Question with id " + id + " not found");
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(questionLogic.deleteQuestion(id));
	}
}
