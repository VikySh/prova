package com.generation.nh.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generation.nh.model.User;
import com.generation.nh.view.LanguageFactory;

/**
 * Servlet implementation class ReadByCity
 */

@WebServlet({ "/Index"})
public class Index extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	Map<String,AbstractController> behaviour = 
		new HashMap<String,AbstractController>();
	
    public Index() 
    {
        super();
        HouseController houseController = new HouseController();
        behaviour.put("house", houseController);
        behaviour.put("user", new UserController());
        behaviour.put("review", new ReviewController());
        behaviour.put("language", new LanguageController());
        behaviour.put("booking", new BookingController());
        
        behaviour.put("", houseController);
        
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
    	String cmd = request.getParameter("cmd");
    	String controllername = request.getParameter("controller");
    	
    	cmd = notNull(cmd);
    	controllername = notNull(controllername);
    	
    	
    	User user = request.getSession().getAttribute("user")!=null 	? 
    				(User) request.getSession().getAttribute("user") 	:
    				User.GUEST											;
    	request.getSession().setAttribute("user", user);
    	if(request.getSession().getAttribute("language")==null)
    		request.getSession().setAttribute("language", LanguageFactory.make("eng"));
    	
    	
    	
    	// ho passato la palla al controller di secondo livello
    	behaviour.get(controllername).handle(user, request, response, cmd);
    	
    	
	}
    
    
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
    	doGet(request,response);
	}
	
	private String notNull(String v)
	{
		return v==null ? "" : v;
	}

}
