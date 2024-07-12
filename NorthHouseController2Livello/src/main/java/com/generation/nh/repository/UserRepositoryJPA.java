package com.generation.nh.repository;

import java.util.List;

import com.generation.nh.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class UserRepositoryJPA implements UserRepository 
{
	EntityManager em;
	
	
	static UserRepositoryJPA instance = new UserRepositoryJPA();
	
	public static UserRepositoryJPA getInstance()
	{
		return instance;
	}
	
	private UserRepositoryJPA()
	{
		this.em = Context.em;
	}


	@Override
	public List<User> findAll() 
	{
		
		String jpql = "select u from User u";
		Query query = em.createQuery(jpql);
		return query.getResultList();		
	}


	@Override
	public void save(User user) 
	{
		try
		{
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			
			e.printStackTrace();
			throw new RuntimeException("Error saving User. Check console log");
		}
	}


	@Override
	public User login(String email, String password) 
	{

		String jpql = "select u from User u where email=:email and password=:password";
		Query query = em.createQuery(jpql);
		query.setParameter("email", email);
		query.setParameter("password", password);
		// 0-1 utenti
		List<User> matches = query.getResultList();		
		
		return matches.size()==1 ? matches.get(0) : null;
	
	}


	@Override
	public User findByEmail(String email) 
	{

		String jpql = "select u from User u where email=:email";
		Query query = em.createQuery(jpql);
		query.setParameter("email", email);
		// 0-1 utenti
		List<User> matches = query.getResultList();		
		
		return matches.size()==1 ? matches.get(0) : null;
	}
	
	
	
	
	
}
