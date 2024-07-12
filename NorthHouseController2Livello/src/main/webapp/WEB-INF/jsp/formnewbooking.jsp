<%@page import="com.generation.nh.model.House"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.generation.nh.view.*"%>
<%
	House house = (House) request.getAttribute("house");
	Language language  = (Language) request.getSession().getAttribute("language");
%>

<form action="Index" method="post">
	<input type="hidden" name="cmd" value="new" />
	<input type="hidden" name="controller" value="booking" />
	<%=language.translate("ARRIVAL") %>
	<input type="text" name="start" class="w3-input" required />
	<%=language.translate("DEPARTURE") %>
	<input type="text" name="end" class="w3-input" required />
	<input type="hidden" name="houseid" value="<%=house.getId() %>" />	

	<input type="submit" value="<%=language.translate("SAVE") %>" class="w3-btn w3-input w3-amber"/>
</form>