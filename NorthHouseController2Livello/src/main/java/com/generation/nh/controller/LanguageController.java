package com.generation.nh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generation.nh.model.User;
import com.generation.nh.view.LanguageFactory;

public class LanguageController extends AbstractController {

	@Override
	public void handle(User user, HttpServletRequest request, HttpServletResponse response, String cmd)
			throws ServletException, IOException 
	{
		switch(cmd)
		{
			case "change":
				_changeLanguage(user,request,response);
			break;
			default:
				errorPage(user, request, response, "BAD COMMAND");
		}
	}

	private void _changeLanguage(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getSession().setAttribute("language", LanguageFactory.make(request.getParameter("language")));
		homePage(user,request,response);
	}

	
	
}
