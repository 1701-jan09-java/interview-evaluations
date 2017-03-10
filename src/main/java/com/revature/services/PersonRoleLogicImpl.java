package com.revature.services;

import javax.validation.ConstraintViolationException;

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

	@Override
	public PersonRole getRoleByTitle(String title) {
		return dao.findAllByTitleIgnoreCase(title);
	}

	@Override
	public PersonRole getRoleByTitleAndId(String title, int id) {
		PersonRole pRId = dao.findOne(id);
		PersonRole pRName = dao.findAllByTitleIgnoreCase(title);
		if(pRId.getId() == pRName.getId()){
			return pRId;
		}
		throw new ConstraintViolationException("PersonRole Name and PersonRole Id do not correspond to each other", null);
	}

}
