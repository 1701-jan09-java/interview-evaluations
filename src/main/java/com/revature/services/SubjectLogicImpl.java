package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.Subject;
import com.revature.repositories.SubjectRepository;
import com.revature.validation.JsonValidation;
import com.revature.validation.exceptions.NotFoundException;

@Service
@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
public class SubjectLogicImpl implements SubjectLogic {
	
	@Autowired
	private SubjectRepository dao;
	
	@Autowired
    private JsonValidation validation;
	

	@Override
	public Subject createSubject(Subject subject) {
		validation.validateIdNotSpecified(subject.getId());
		validation.validateSubjectFields(subject);
		dao.saveAndFlush(subject);
		return subject;
	}

	@Override
	public Page<Subject> getAllSubjects(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public Subject getSubjectById(int id) {
		Subject subject = dao.findOne(id);
		
		if (subject == null){
			throw new NotFoundException("Subject with id " + id + " does not exist");
		}
		return subject;
	}

	@Override
	public Page<Subject> getSubjectBySubjectName(Pageable pageable, String subjectName) {
		return dao.findAllBySubjectIgnoreCase(pageable, subjectName);
	}

	@Override
	public Subject updateSubject(Subject subject) {
		Subject updatedSubject = getSubjectById(subject.getId());
		if(subject.getSubject() !=  null){
			updatedSubject.setSubject(subject.getSubject());
		}
		dao.saveAndFlush(updatedSubject);
		return updatedSubject;
	}

	@Override
	@Transactional
	public String deleteSubject(int id) {
		Subject sub = getSubjectById(id);

		dao.delete(sub);
		return "{\"text\":\"Subject: " + id + " - DELETED\"}";
	}

}
