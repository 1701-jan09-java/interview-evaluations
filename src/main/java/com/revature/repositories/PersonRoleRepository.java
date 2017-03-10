package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.domain.PersonRole;

public interface PersonRoleRepository extends JpaRepository<PersonRole,Integer> {

	// this is a marker interface 
}
