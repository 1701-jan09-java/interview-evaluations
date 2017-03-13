package com.revature.controllers;

import com.revature.domain.QuestionComment;
import com.revature.domain.QuestionEval;
import com.revature.services.QuestionEvalLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1/evaluations/{evalId}/questions")
public class QuestionEvalController {
	
	@Autowired
	private QuestionEvalLogic questionEvalLogic;
	
//QUESTIONCOMMENT CUD---------------------------------
	
	@RequestMapping(method = RequestMethod.POST, value = "{questionId}/comments")
	public ResponseEntity<QuestionComment> addQuestionComment(
            @RequestBody QuestionComment comment
            , @PathVariable ("questionId") Integer questionId
    ){
		return ResponseEntity.ok(questionEvalLogic.createComment(comment,questionId));
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "{questionId}/comments/{commentId}")
	public ResponseEntity<QuestionComment> updateQuestionComment(
            @RequestBody QuestionComment comment
            , @PathVariable Integer commentId
    ){
		return ResponseEntity.ok(questionEvalLogic.updateComment(comment, commentId));
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "{questionId}/comments/{commentId}")
	public ResponseEntity<String> deleteQuestionComment(@PathVariable Integer commentId){
		return ResponseEntity.ok(questionEvalLogic.deleteComment(commentId));
	}
	
//QUESTIONEVAL CUD-------------------------------------
	
	@RequestMapping(method = RequestMethod.POST, value = "")
	public ResponseEntity<QuestionEval> addQuestionEval(
            @RequestBody QuestionEval qEval
            , @PathVariable Integer evalId
    ){
		return ResponseEntity.ok(questionEvalLogic.createQuestionEval(qEval,evalId));
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "{questionId}")
	public ResponseEntity<QuestionEval> updateQuestionEval(
            @RequestBody QuestionEval qEval
            , @PathVariable Integer questionId){
		return ResponseEntity.ok(questionEvalLogic.updateQuestionEval(qEval,questionId));
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "{questionId}")
	public ResponseEntity<String> deleteQuestionEval(@PathVariable int questionId){
		return ResponseEntity.ok(questionEvalLogic.deleteQuestionEval(questionId));
	}
}
