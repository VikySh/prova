<%@page import="com.generation.nh.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.generation.nh.view.*"
%>
<%
	User user = (User) request.getSession().getAttribute("user");
	Language language = (Language) request.getSession().getAttribute("language");
	//							   HttpServletRequest.HttpSession.Object
	
	//		Alfonso			-> 	Session			-> User Alfonso
	//											-> Language Italiano
	
	//		Ferdinando		-> Session			-> User Ferdinasndo
	//											-> Language Italiano
%>
<img src="img/logo.jpeg.jpeg" id="logo" />
<!--  target del link -->
<div id="menu">


	<h3> <%=user.getEmail() %><br /><i><%=user.getRole() %></i></h3>
	<% if(user==User.GUEST){ %>
	<a href="Index?controller=user&cmd=formlogin">
		Login
	</a>
	<% } %>
	<a href="Index">
		<!--  corpo del link -->
		<%=language.translate("OURHOUSES") %>
	</a>
	<% if(user.isCustomer()){ %>
		<a href="Index?controller=user&cmd=info">
			<%=language.translate("USERHISTORY") %>
		</a>
	<%} %>
	<% if(user.getRole().equals("admin")){ %>
	<a href="Index?cmd=formnew&controller=house">
		<!--  corpo del link -->
		<%=language.translate("NEWHOUSE") %>
	</a>
	<% } %>
	<% if(user!=User.GUEST){ %>
	<a href="Index?controller=user&cmd=logout">
		<%=language.translate("LOGOUT") %>
	</a>
	<% } %>

</div>