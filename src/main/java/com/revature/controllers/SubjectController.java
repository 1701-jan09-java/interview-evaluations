package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Subject;
import com.revature.services.SubjectLogic;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/")
public class SubjectController {
	
	@Autowired
	private SubjectLogic subjectLogic; 
	
	@RequestMapping(method = RequestMethod.GET, value = "subjects/{subjectId}")
	public ResponseEntity<Subject> getSubjectById(@PathVariable Integer subjectId ){
		return ResponseEntity.ok(subjectLogic.getSubjectById(subjectId));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "subjects")
	public ResponseEntity<Page<Subject>> getSubject(Pageable pageable, 
			@RequestParam(defaultValue="", required=false) String subjectName){

		if(!"".equals(subjectName)){
			return ResponseEntity.ok(subjectLogic.getSubjectBySubjectName(pageable, subjectName));
		} else{
			return ResponseEntity.ok(subjectLogic.getAllSubjects(pageable));
		} 
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "subjects/{subjectId}")
	public ResponseEntity<Subject> createSubject(@RequestBody Subject sub){
		return ResponseEntity.ok(subjectLogic.createSubject(sub));
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "subjects/{subjectId}")
	public ResponseEntity<Subject> modifySubject(@RequestBody Subject sub, @PathVariable Integer subjectId ){
		sub.setId(subjectId);
		return ResponseEntity.ok(subjectLogic.updateSubject(sub));
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "subjects/{subjectId}")
	public ResponseEntity<String> deletePerson(@PathVariable Integer subjectId){
			return ResponseEntity.ok(subjectLogic.deleteSubject(subjectId));
	}

}
