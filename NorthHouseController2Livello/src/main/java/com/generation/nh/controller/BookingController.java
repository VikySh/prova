package com.generation.nh.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generation.nh.model.Booking;
import com.generation.nh.model.House;
import com.generation.nh.model.User;

public class BookingController extends AbstractController {

	@Override
	public void handle(User user, HttpServletRequest request, HttpServletResponse response, String cmd)
			throws ServletException, IOException
	{
		switch(cmd)
		{
			case "new":
				_new(user,request,response);
			break;
			default:
				errorPage(user, request, response, "BAD COMMAND");
		}
		
	}

   private void _new(User user, HttpServletRequest request, HttpServletResponse response) 
   throws ServletException, IOException
   {
	   if(!user.isCustomer())
		   errorPage(user,request,response, "You cannot book if you're not a customer");
	   else
		   try
	   	   {
			   Booking b = new Booking();
			   LocalDate start 	= LocalDate.parse(request.getParameter("start"));			   
			   LocalDate end 	= LocalDate.parse(request.getParameter("end"));
			   b.setStart(start);
			   b.setEnd(end);
			   House h = houseRepo.findById(Integer.parseInt(request.getParameter("houseid")));
			   
			   if(h==null)
				   throw new RuntimeException("Missing house for id "+request.getParameter("id"));
			   
			   if(!h.isFreeFor(b))
				   throw new RuntimeException("The room is not free for these dates");
			   
			   b.setCost(b.getDays().size()*h.getPrice());
			   
			   b.setUser(user);
			   b.setHouse(h);
			   h.getBookings().add(b);
			   houseRepo.save(h);
	   		   errorPage(user,request,response,"TUTTO OK");	   		   
	   	   }
		   catch(DateTimeParseException e)
	   	   {
	   		   errorPage(user,request,response,"Bad date format:"+e.getMessage());	   		   
	   	   }
	   	   catch(NumberFormatException e)
	       {
	   		   errorPage(user,request,response,"Invalid house id");	   		   
	       }
	   	   catch(RuntimeException e)
	       {
	   		   errorPage(user,request,response,e.getMessage());	   		   
	       }

   }

	
}
