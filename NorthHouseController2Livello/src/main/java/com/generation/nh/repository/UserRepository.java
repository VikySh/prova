package com.generation.nh.repository;

import java.util.List;

import com.generation.nh.model.User;

public interface UserRepository 
{

	
	List<User> findAll();
	
	void save(User user);
	
	/**
	 * fornisco al metodo login email e password
	 * mi restituisce l'utente se c'è
	 * altrimenti null. null = bad login
	 * @param email
	 * @param password
	 * @return
	 */
	User login(String email, String password);
	
	
	User findByEmail(String email);
	
}
