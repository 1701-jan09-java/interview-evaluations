package com.revature.repositories;


import org.springframework.stereotype.Repository;

import com.revature.domain.Subject;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface SubjectRepository extends PagingAndSortingRepository<Subject, Integer> {
	
}
