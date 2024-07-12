<%@page import="com.generation.nh.model.House"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.generation.nh.view.*"%>
<%
	House h = (House) request.getAttribute("house");
	Language language  = (Language) request.getSession().getAttribute("language");
%>

<form action="Index" method="post">
	<input type="hidden" name="cmd" value="new" />
	<input type="hidden" name="controller" value="review" />
	Title
	<input type="text" name="title" class="w3-input" required />
	Score
	<input type="number" min="1" max="5" name="score" class="w3-input" required />
	Content
	<textarea class="w3-input" rows="4" name="content"></textarea>
	
	<!-- 
		questo parametro NON è inserito dall'utente
		è fornito dalla jsp
		E' l'id della casa in cui mi trovo.
		E' l'id del master.
	 -->
	<input type="hidden" name="houseid" value="<%=h.getId() %>" />
	
	<input type="submit" value="<%=language.translate("SAVE") %>" class="w3-btn w3-input w3-amber"/>
</form>