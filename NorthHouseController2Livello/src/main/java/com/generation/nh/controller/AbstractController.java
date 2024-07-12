package com.generation.nh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generation.nh.model.User;
import com.generation.nh.repository.Context;
import com.generation.nh.repository.HouseRepository;
import com.generation.nh.repository.UserRepository;

public abstract class AbstractController 
{
	protected static final String JSPPATH = "WEB-INF/jsp/";
	protected static final String JSPEXT = ".jsp";	

	protected HouseRepository houseRepo = 
			(HouseRepository) Context.getDependency(HouseRepository.class);
	
	protected UserRepository userRepo = 
			(UserRepository) Context.getDependency(UserRepository.class);

	
	protected void homePage(User user, HttpServletRequest request, HttpServletResponse response) 
	throws ServletException,IOException
	{
	   	request.setAttribute(ViewAttribute.houses.toString(), houseRepo.findAll());
	   	render(View.INDEX, request, response);
	}

	
	/**
	 * Pagina di errore, sempre uguale.
	 * @param user
	 * @param request
	 * @param response
	 * @param message
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void errorPage
	(
		User user, 
		HttpServletRequest request, 
		HttpServletResponse response,
		String message
	) 
    throws ServletException, IOException
	{
	   	request.setAttribute(ViewAttribute.message.toString(), message);
	   	render(View.ERROR, request, response);
		   
	}

	/**
     * 
     * @param view						=> nome della jsp da graficare, senza estensione
     * @param request					=> request per il forward
     * @param response					=> response per il forward
     * @throws ServletException
     * @throws IOException
     */
    protected void render
    (
    	View view, 
    	HttpServletRequest request, 
    	HttpServletResponse response
    )
    throws ServletException, IOException
    {
    	request.getRequestDispatcher(JSPPATH+view.toString().toLowerCase()+JSPEXT).forward(request, response);
    }

    /**
     * Metodo principale del controller di secondo livello.
     * Dovrà gestire la richiesta
     * Handle farà le veci di doGet e doPost
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public abstract void handle(User user, HttpServletRequest request,HttpServletResponse response, String cmd)
    throws ServletException, IOException;
    
    protected String notNull(String v)
	{
		return v==null ? "" : v;
	}
    
    protected boolean isNumeric(String v)
    {
    	try
    	{
    		Integer.parseInt(v);
    		return true;
    	}
    	catch(NumberFormatException e)
    	{
    		return false;
    	}
    	
    }
    
}
