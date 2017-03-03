package com.revature.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.domain.QuestionPool;
import com.revature.repositories.QuestionRepository;

public class QuestionLogicImpl implements QuestionLogic{
	
	@Autowired
	private QuestionRepository dao;

	@Override
	public ArrayList<QuestionPool> getAllQuestions(String type) {
		ArrayList<QuestionPool> questions;
		
		if(type.equalsIgnoreCase("both")){
			questions = (ArrayList<QuestionPool>) dao.findAll();
		} else {
			questions = (ArrayList<QuestionPool>) dao.findAllBySubject(type);
		}
		return questions;
	}
	
}
