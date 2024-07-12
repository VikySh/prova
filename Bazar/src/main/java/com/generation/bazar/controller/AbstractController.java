package com.generation.bazar.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generation.bazar.repo.Context;
import com.generation.bazar.repo.ProductRepo;

/**
 * base per tutti i controller di secondo livello che scriverò
 */
public abstract class AbstractController
{
	protected static final String JSPPATH = "WEB-INF/jsp/";
	protected static final String JSPEXT = ".jsp";


	protected final ProductRepo repo = (ProductRepo) Context.getDependency(ProductRepo.class);

	/**
	 * Renderizza una View passandogli request e response
	 * @param view
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void render(View view, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String jspurl = JSPPATH + view.toString().toLowerCase() + JSPEXT;
		request.getRequestDispatcher(jspurl).forward(request, response);
	}

	/**
	 * Il front controller chiamerà il metodo handle degli abstract controller
	 * @param request
	 * @param responde
	 * @param cmd
	 */
	public abstract void handle(HttpServletRequest request, HttpServletResponse response, String cmd) throws ServletException, IOException;

	public void homePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("products", repo.findAll());
		render(View.HOME, request, response);
	}

}
