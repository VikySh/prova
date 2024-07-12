package com.generation.bazar.repo;

import java.util.List;

import com.generation.bazar.model.Product;

import jakarta.persistence.EntityManager;

public class ProductRepo
{

	EntityManager em;

	public ProductRepo()
	{
		this.em = Context.em;
	}

	public List<Product> findAll()
	{
		return em.createQuery("select p from product p").getResultList();
	}

	public void save(Product p)
	{
		try
		{
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
		}
		catch (Exception e)
		{
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new RuntimeException(e.getMessage());
		}
	}
}
