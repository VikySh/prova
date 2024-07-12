package com.generation.bazar.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReviewController extends AbstractController
{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, String cmd) throws IOException
	{
		switch (cmd)
		{
			case "formnew":
				response.getWriter().append("hai chiamato il cmd formnew di reviewController");
			break;

			case "new":
				response.getWriter().append("hai chiamato il cmd new di reviewController");
			break;

			case "delete":
				response.getWriter().append("hai chiamato il delete formnew di reviewController");
			break;

			default:
				response.getWriter().append("comando non riconosciuto");
		}

	}

}
