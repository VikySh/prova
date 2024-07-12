package com.generation.bazar.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductController extends AbstractController
{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, String cmd) throws ServletException, IOException
	{
		switch (cmd)
		{
			case "formnew":
				response.getWriter().append("hai chiamato il cmd formnew di productController");
			break;

			case "new":
				response.getWriter().append("hai chiamato il cmd new di productController");
			break;

			case "delete":
				response.getWriter().append("hai chiamato il delete formnew di productController");
			break;

			default:
				homePage(request, response);
		}

	}

}
