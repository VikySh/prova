package com.generation.nh.repository;

import java.util.List;

import com.generation.nh.model.House;
import com.generation.nh.model.Review;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;


/**
 * 1 - Mavenizzare
 * 2 - Seguire la guida di Stefano per configurare dipendenze nel pom
 * (le dipendenze del pom NON sono le dipendenze delle factory)
 * 3 - Configurare persistence.xml
 * 4 - Modificare classe Blook, aggiungendo annotazione
 * 5 - Scrivere BookRepositoryJPA
 * 6 - Scrivere RepositoryFactory
 * 7 - Fare in modo che le servlet usino LA FACTORY per prendere il repository
 * 8 - Avviare e testare in sistema
 * 
 * 
 * @author FP80
 *
 */

public class HouseRepositoryJPA implements HouseRepository
{

	EntityManager em;
	
	
	public HouseRepositoryJPA()
	{
		//compro la EntityManager dalla classe Context
		//Context è una FACTORY, anche lei.
		em = Context.em;
	}


	@Override
	public List<House> findHouses() 
	{
		// prendo tutte le case h dall'insieme di case House
		// h non è una riga. è un oggetto House
		// che potrebbe contenere degli oggetti collegati
		Query query = em.createQuery("select h from House h");
		return query.getResultList();
		
	}


	@Override
	public void save(House house) 
	{
		try
		{
			em.getTransaction().begin();
			em.persist(house);
			em.getTransaction().commit();
		}
		catch(Exception e)
		{
			em.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}
	}


	@Override
	public House findById(int id) 
	{
		return em.find(House.class, id);
	}


	@Override
	public List<House> findByCity(String c) 
	{
		// 												JPQL
		//							  seleziona tutti gli oggetti h
		//										dell'insieme House h (h è un alias di insieme)
		//										per cui la proprietà city sia uguale al
		//										parametro di query city
		//										h indica l'intero oggetto
		Query query = em.createQuery("select h from House h where h.city=:city");
		query.setParameter("city", c);
		// select h from House h where h.city='Varenna';
		return query.getResultList();
	}


	@Override
	public List<House> findByCityAndPrice(String city, int maxprice) 
	{
		Query query = em.createQuery("select h from House h where h.city=:city and h.price<:maxprice");
		query.setParameter("city", city);
		query.setParameter("maxprice", maxprice);
		return query.getResultList();
	}


	@Override
	public List<House> findAll() 
	{
		return findHouses();
	}


	@Override
	public void delete(int id) 
	{
		try
		{
			em.getTransaction().begin();
			em.remove(findById(id));
			em.getTransaction().commit();
		}
		catch(Exception e)
		{
			em.getTransaction().rollback();
		}
	}

	
}
