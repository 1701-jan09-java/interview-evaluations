package com.revature.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.domain.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
	
	Page<Subject> findAllBySubjectIgnoreCase(Pageable pageable, String subject);
}
