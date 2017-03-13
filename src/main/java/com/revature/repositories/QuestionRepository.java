package com.revature.repositories;


import com.revature.domain.QuestionPool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionPool, Integer> {

	Page<QuestionPool> findAllBySubjectSubjectIgnoreCase(Pageable pageable, String type);
	
	@Query("SELECT q FROM QuestionPool q WHERE " +
            "LOWER(q.questionText) LIKE LOWER(CONCAT('%',:searchTerm, '%'))")
    Page<QuestionPool> findBySearchTerm(Pageable pageable, @Param("searchTerm") String searchTerm);
	
	@Query("SELECT q FROM QuestionPool q WHERE LOWER(q.questionText) LIKE " +
			"LOWER(CONCAT('%',:searchTerm, '%')) and " +
			"LOWER(q.subject.subject) = LOWER(:subject)")
    Page<QuestionPool> findBySearchTerm(Pageable pageable, @Param("searchTerm") String searchTerm, @Param("subject") String subject);
	
}
