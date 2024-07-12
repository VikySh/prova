package com.generation.nh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generation.nh.model.User;

public class UserController extends AbstractController
{

	@Override
	public void handle(User user, HttpServletRequest request, HttpServletResponse response, String cmd)
			throws ServletException, IOException 
	{
		switch(cmd)
		{	
			case "formlogin":
				_formLogin(user, request, response);
			break;
			case "login":
				_login(user, request, response);
			break;
			case "logout":
				_logout(user, request, response);
			break;
			case "info":
				_info(user,request,response);
			default:
				errorPage(user, request, response, "Bad command");
		}
	}
	
	
   private void _info(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
	   render(View.USERHOME, request, response);
   }


   private void _formLogin(User user,HttpServletRequest request, HttpServletResponse response) 
   throws ServletException,IOException
   {
	   render(View.FORMLOGIN, request, response);
   }

   private void _login(User olduser, HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
   {
		String email 	= request.getParameter("email");
		String password	= request.getParameter("password");
		User user = userRepo.login(email, password);
		
		if(user==null)
			errorPage(olduser, request, response, "Bad email or password");
		else
		{
			request.getSession().setAttribute("user", user);
			homePage(user,request,response);
		}
   }

   private void _logout(User user, HttpServletRequest request, HttpServletResponse response) 
   throws ServletException, IOException
   {
	   request.getSession().setAttribute("user", User.GUEST);
	   user = User.GUEST;
	   homePage(user, request, response);
	   
   }

	

}
