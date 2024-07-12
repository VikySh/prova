<%@page import="com.generation.nh.model.Review"%>
<%@page import="com.generation.nh.model.House"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%
	House h = (House) request.getAttribute("house");
%>
<% for(Review r:h.getReviews()){ %>
	<div style="margin-bottom:20px">
		<h4> <%=r.getTitle() %> </h4>
		<%=r.getAuthor() %> <br />
		<%
			for(int i=0;i<r.getScore();i++)
			{
		%>		
			<%="*" %>	
		<%
			}
		%>
	</div>
<% } %>





