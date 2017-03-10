package com.revature.controllers;

import com.revature.domain.QuestionComment;
import com.revature.domain.QuestionEval;
import com.revature.services.QuestionLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1/evaluations/{evalId}/questions")
public class QuestionEvalController {
	
	@Autowired
	QuestionLogic questionLogic;
	
//QUESTIONCOMMENT CUD---------------------------------
	
	@RequestMapping(method = RequestMethod.POST, value = "{questionId}/comments")
	public ResponseEntity<QuestionComment> addQuestionComment(@RequestBody QuestionComment comment, @PathVariable ("questionId") Integer questionId){
		return ResponseEntity.ok(questionLogic.createComment(comment,questionId));
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "{questionId}/comments/{id}")
	public ResponseEntity<QuestionComment> updateQuestionComment(@RequestBody QuestionComment comment, @PathVariable ("id") Integer id){
		
		QuestionComment currComment = questionLogic.getCommentById(id);
		
		if(currComment == null){
			System.out.println("Comment with id " + id + " not found");
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if(comment.getCommentText() != null){
			currComment.setCommentText(comment.getCommentText());
		}
		if(comment.getQuestionEval() != null){
			currComment.setQuestionEval(comment.getQuestionEval());
		}
		
		return ResponseEntity.ok(questionLogic.updateComment(currComment));
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "{questionId}/comments/{id}")
	public ResponseEntity<QuestionComment> deleteQuestionComment(@PathVariable ("id") int id){
		
		QuestionComment currComment = questionLogic.getCommentById(id);
		
		if(currComment == null){
			System.out.println("Comment with id " + id + " not found");
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(questionLogic.deleteComment(id));
	}
	
//QUESTIONEVAL CUD-------------------------------------
	
	@RequestMapping(method = RequestMethod.POST, value = "")
	public ResponseEntity<QuestionEval> addQuestionEval(@RequestBody QuestionEval qEval, @PathVariable ("evalId") Integer evalId){
		return ResponseEntity.ok(questionLogic.createQuestionEval(qEval,evalId));
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "update/{id}")
	public ResponseEntity<QuestionEval> updateQuestionEval(@RequestBody QuestionEval qEval, @PathVariable ("id") Integer id){
		
		QuestionEval currQEval = questionLogic.getQuestionEvalById(id);
		
		if(currQEval == null){
			System.out.println("QuestionEval with id " + id + " not found");
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if(qEval.getCommunicationScore() != null){
			currQEval.setCommunicationScore(qEval.getCommunicationScore());
		}
		if(qEval.getKnowledgeScore() != null){
			currQEval.setKnowledgeScore(qEval.getKnowledgeScore());
		}
		
		return ResponseEntity.ok(questionLogic.updateQuestionEval(currQEval));
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "delete/{id}")
	public ResponseEntity<String> deleteQuestionEval(@PathVariable ("id") int id){
		
		QuestionEval currQEval = questionLogic.getQuestionEvalById(id);
		
		if(currQEval == null){
			System.out.println("QuestionEval with id " + id + " not found");
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(questionLogic.deleteQuestionEval(id));
	}
}
