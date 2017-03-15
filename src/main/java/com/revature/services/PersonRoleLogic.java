package com.revature.services;

import com.revature.domain.PersonRole;

public interface PersonRoleLogic {

	PersonRole findRoleById(int id);
	PersonRole getRoleByTitle(String title);
	
	//used if both string and int are inputs--> to check if both inputs correspond
	PersonRole getRoleByTitleAndId(String title, int id); 
}
