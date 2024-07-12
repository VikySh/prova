package com.generation.bazar.repo;

import com.generation.bazar.model.Product;

public class TestSave
{

	public static void main(String[] args)
	{
		ProductRepo repo = (ProductRepo) Context.getDependency(ProductRepo.class);

		Product p = new Product();
		p.setName("Dishonored");
		p.setPrice(5);

		repo.save(p);

	}

}
