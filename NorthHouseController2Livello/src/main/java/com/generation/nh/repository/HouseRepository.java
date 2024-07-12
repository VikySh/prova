package com.generation.nh.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.generation.nh.model.House;
import com.generation.nh.model.Review;

public interface HouseRepository 
{
	
	
	List<House> findHouses();

	void save(House house);
	
	House findById(int id);

	List<House> findByCity(String city);

	List<House> findByCityAndPrice(String city, int maxprice);
	

	List<House> findAll();

	default List<House> findByCriteria(Map<String,String> criteria)
	{
		List<House> res = new ArrayList<House>();
		res.addAll(findHouses());
		
		if(criteria.containsKey("city"))
			res = res.stream().filter(h->h.getCity().toLowerCase().equals(criteria.get("city"))).toList();
		
		if(criteria.containsKey("address"))
			res = res.stream().filter(h->h.getAddress().toLowerCase().contains(criteria.get("address").toLowerCase())).toList();
		
		
		if(criteria.containsKey("maxprice"))
			res = res.stream().filter(h->h.getPrice()<=Integer.parseInt(criteria.get("maxprice"))).toList();
	
		if(criteria.containsKey("minprice"))
			res = res.stream().filter(h->h.getPrice()>=Integer.parseInt(criteria.get("minprice"))).toList();
	
	
		return res;
	}
	
	void delete(int id);
	
	
	default List<House> findByCriteria(String criteriaCSV) 
	{
		Map<String,String> criteria = new HashMap<String,String>();
		String[] parts = criteriaCSV.split("&");
		for(String part:parts)
			criteria.put(part.split("=")[0], part.split("=")[1]);
		
		return findByCriteria(criteria);
		
	}
	
	

	
}
