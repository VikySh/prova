package com.generation.nh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generation.nh.model.House;
import com.generation.nh.model.Review;
import com.generation.nh.model.User;

public class ReviewController extends AbstractController {

	@Override
	public void handle(User user, HttpServletRequest request, HttpServletResponse response, String cmd)
			throws ServletException, IOException 
	{
		switch(cmd)
		{
			case "new":
				_newReview(user,request,response);
			break;
			default:
				errorPage(user, request, response, "BAD COMMAND");
		}
	}

	private void _newReview(User user, HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException
    {
	   if(user.isCustomer())
		   try
		   {
			   Review r = new Review();
			   r.setTitle(request.getParameter("title"));
			   r.setContent(request.getParameter("content"));
			   r.setScore(Integer.parseInt(request.getParameter("score")));
			   r.setAuthor(user.getEmail());
			   int houseid = Integer.parseInt(request.getParameter("houseid"));
			   House master = houseRepo.findById(houseid);
			   r.setHouse(master);				// COLLEGO FIGLIO AL PADRE
			   master.getReviews().add(r);
			   houseRepo.save(master);
			   request.setAttribute(ViewAttribute.house.toString(), master);
			   render(View.HOUSE, request, response);
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
			   errorPage(user, request, response, "Bad review data");
		   }
	   else
		   errorPage(user, request, response, "FORBIDDEN");
   }
			   
	
}
