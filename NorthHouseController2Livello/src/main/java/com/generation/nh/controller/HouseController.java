package com.generation.nh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generation.nh.model.House;
import com.generation.nh.model.User;

/**
 * Io sono un controller di secondo livello.
 * Mi occupo delle funzioni
 * che riguardano le case.
 * Index mi passa le request
 * e io svolgo il lavoro vero.
 * @author FP80
 *
 */
public class HouseController extends AbstractController
{

	@Override
	public void handle(User user, HttpServletRequest request, HttpServletResponse response, String cmd) 
	throws ServletException, IOException 
	{
		switch(cmd)
		{
			case "formnew":
				_formNewHouse(user,request,response);
			break;
			case "new":
				_newHouse(user,request,response);
			break;
			case "list":
				homePage(user,request,response);
			break;
			case "delete":
				_delete(user,request,response);
			break;
			case "detail":
				detail(user,request,response);
			break;
			case "search":
				search(user,request,response);
			break;
			default:
				homePage(user,request,response);
		}
				
	}
	
	
	
	
	private void search(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String city = notNull(request.getParameter("city"));
		String budget = notNull(request.getParameter("budget"));
		if(!isNumeric(budget))
			budget="";
		
		if(city.isBlank() && budget.isBlank())
			homePage(user,request,response);
		else
			if(!city.isBlank() && budget.isBlank())
			{
				request.setAttribute("houses", houseRepo.findByCity(city));
				render(View.INDEX, request, response);				
			}
			else
			{
				request.setAttribute("houses", houseRepo.findByCityAndPrice(city,Integer.parseInt(budget)));
				render(View.INDEX, request, response);				
			}
	}




	private void _newHouse(User user, HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException
	{
	   if(user.isAdmin())   
	   {
		   try
		   {
				House h = new House();
				h.setCity(request.getParameter("city"));
				h.setAddress(request.getParameter("address"));
				h.setFloor(Integer.parseInt(request.getParameter("floor")));
				h.setImage(request.getParameter("image"));
				h.setPrice(Integer.parseInt(request.getParameter("price")));
				h.setName(request.getParameter("name"));
				h.setDescription(request.getParameter("description"));
				houseRepo.save(h);
				homePage(user,request,response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				errorPage(user, request, response, "INVALID DATA");
			}	   
	   }
	   else
		   errorPage(user,request,response,"FORBIDDEN");
   }




	private void _formNewHouse(User user, HttpServletRequest request, HttpServletResponse response) 		   throws ServletException, IOException
	{
	  if(user.isAdmin())	   
		   render(View.FORMNEWHOUSE, request, response);
	   else
		   errorPage(user,request,response,"FORBIDDEN");
	}

	   private void _delete(User user, HttpServletRequest request, HttpServletResponse response) 		   throws ServletException, IOException
	   {
		   if(user.isAdmin())
			   try
			   {
				   int id = Integer.parseInt(request.getParameter("id"));
				   houseRepo.delete(id);
				   homePage(user, request, response);
			   }
			   catch(Exception e)
			   {
				   e.printStackTrace();
				   errorPage(user, request, response, e.getMessage());
			   }
		   else
			   errorPage(user, request, response, "FORBIDDEN");
	   }


	   private void detail(User user, HttpServletRequest request, HttpServletResponse response) 
	   throws ServletException, IOException
	   {
		   try 
		   {
			   int id = Integer.parseInt(request.getParameter("id"));
			   House h = houseRepo.findById(id);
			   if(h==null)
				   errorPage(user,request,response,"House with id "+id+" not found");
			   else
			   {
				   request.setAttribute(ViewAttribute.house.toString(), h);
				   render(View.HOUSE, request, response);
			   }
		   }
		   catch(RuntimeException e)
		   {
			   errorPage(user, request, response, "Id not passed or not numeric");
		   }
	   }
}
