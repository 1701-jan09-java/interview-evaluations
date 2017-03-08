package com.revature.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.revature.domain.QuestionComment;

@Repository
public interface QuestionCommentRepository extends PagingAndSortingRepository<QuestionComment, Integer> {

}
