package com.generation.bazar.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Index")
public class Index extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	Map<String, AbstractController> controllers = new HashMap<>();

	final ProductController PRODUCTCONTROLLER = new ProductController();
	final ReviewController REVIEWCONTROLLER = new ReviewController();

    public Index()
    {
    	super();

    	controllers.put("", PRODUCTCONTROLLER);
    	controllers.put("house", PRODUCTCONTROLLER);
    	controllers.put("review", REVIEWCONTROLLER);
    }

	// DISPATCHER - SMISTATORE
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// mi aspetto che arrivino due parametri
		// il cmd e il controller a cui passarlo

		String controllerKey = request.getParameter("controller");

		// dalla chiave scelgo in controller
		AbstractController secondLevelController = 	chooseController(controllerKey);

		// gli passo la palla
		secondLevelController.handle(request, response, notNull(request.getParameter("cmd")));


	}


	private AbstractController chooseController(String controllerKey)
	{
				// se non mi è arrivata nessuna chiave
		return 	controllerKey==null || !controllers.containsKey(controllerKey) 	?
				PRODUCTCONTROLLER													:
				controllers.get(controllerKey)									;
	}

	private String notNull(String parameter)
	{
		return parameter==null ? "" : parameter;
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
