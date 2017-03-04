package com.revature.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.QuestionPool;
import com.revature.services.QuestionLogic;

@RestController
@RequestMapping(value="/api/v1/questions")
public class QuestionController {
	
	@Autowired
	QuestionLogic questionLogic;
	
	@RequestMapping(method = RequestMethod.GET, value="")
	public ResponseEntity<ArrayList<QuestionPool>> getAllQuestions(@RequestParam(defaultValue="both") String type){
		return ResponseEntity.ok(questionLogic.getAllQuestions(type));
	}
}
