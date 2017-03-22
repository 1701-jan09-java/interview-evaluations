package com.revature.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.revature.domain.Subject;

public interface SubjectLogic {
	
	Subject createSubject(Subject subject);
	Page<Subject> getAllSubjects(Pageable pageable);
	Subject getSubjectById(int id);
	Page<Subject> getSubjectBySubjectName(Pageable pageable, String subjectName);
	Subject updateSubject (Subject subject);
	String deleteSubject (int id);

}
