package com.revature.services;

import java.util.ArrayList;

import com.revature.domain.QuestionPool;

public interface QuestionLogic {
	
	ArrayList<QuestionPool> getAllQuestions(String type);
}
