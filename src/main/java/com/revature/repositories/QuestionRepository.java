package com.revature.repositories;


import org.springframework.stereotype.Repository;

import com.revature.domain.QuestionPool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface QuestionRepository extends PagingAndSortingRepository<QuestionPool, Integer> {

	Page<QuestionPool> findAllBySubjectSubjectIgnoreCase(Pageable pageable, String type);
	
	@Query("SELECT q FROM QuestionPool q WHERE " +
            "LOWER(q.questionText) LIKE LOWER(CONCAT('%',:searchTerm, '%'))")
    Page<QuestionPool> findBySearchTerm(Pageable pageable, @Param("searchTerm") String searchTerm);
	
	@Query("SELECT q FROM QuestionPool q WHERE LOWER(q.questionText) LIKE " +
			"LOWER(CONCAT('%',:searchTerm, '%')) and " +
			"LOWER(q.subject.subject) = LOWER(:subject)")
    Page<QuestionPool> findBySearchTerm(Pageable pageable, @Param("searchTerm") String searchTerm, @Param("subject") String subject);
	
}
