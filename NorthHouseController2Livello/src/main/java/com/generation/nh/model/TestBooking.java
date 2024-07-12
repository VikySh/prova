package com.generation.nh.model;

import java.time.LocalDate;

public class TestBooking 
{

	public static void main(String[] args) 
	{
	
		Booking b = new Booking();
		b.setStart(LocalDate.now());
		b.setEnd(LocalDate.now().plusDays(5));
		
		System.out.println(b.getDays());
	}

}
