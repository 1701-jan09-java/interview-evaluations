package com.revature.controllers;

import com.revature.domain.Eval;
import com.revature.services.EvalLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/eval")
public class EvalController {

	@Autowired
	private EvalLogic evalLogic;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Eval getEvaluation(@PathVariable("id") Integer id) {

		Eval eval = evalLogic.getEvalById(id);
		return eval;
	}

}
