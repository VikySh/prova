package com.generation.bazar.repo;



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Context
{
	public static EntityManager em;

	static List<Object> dependencies = new ArrayList<>();

	static
	{
		try
		{
			EntityManagerFactory f = Persistence.createEntityManagerFactory("bazar");
			em = f.createEntityManager();

			dependencies.add(new ProductRepo());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ERRORE");
			System.exit(-1);
		}
	}

	/**
	 * needed potrebbe essere una classe o una interfaccia
	 * @param needed
	 * @return
	 */
	//										 UserRepository
	public static Object getDependency(Class needed)
	{
		//         o => un oggetto di tipo UserRepositoryJPA
		for(Object o:dependencies)
		{
				  //UserRepositoryJPA
			Class myClass = o.getClass();
			if(myClass==needed) { // UserRepositoryJPA != UserRepository
				return o;
			}

			// scorro le interfacce di questa classe
			for(Class i:myClass.getInterfaces())
			 { // getInterfaces() = [UserRepository]
				if(i==needed)		// i = UserRepository, needed = UserRepository => true
									return o;		//o, di classe myClass, implementa needed
			}
		}

		throw new RuntimeException("DIPENDENZA NON SODDISFATTA "+needed);
	}
}
