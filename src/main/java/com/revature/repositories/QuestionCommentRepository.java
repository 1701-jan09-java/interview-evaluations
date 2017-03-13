package com.revature.repositories;

import com.revature.domain.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Integer> {

}
