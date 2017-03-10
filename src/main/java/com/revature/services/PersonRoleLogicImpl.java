package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.PersonRole;
import com.revature.repositories.PersonRoleRepository;

@Service
@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
public class PersonRoleLogicImpl implements PersonRoleLogic {

	@Autowired
	private PersonRoleRepository dao; 
	
	@Override
	public PersonRole findRoleById(int id) {
		return dao.findOne(id);
	}

}
