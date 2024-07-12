<%@page import="com.generation.nh.model.Booking"%>
<%@page import="com.generation.nh.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="java.util.List,com.generation.nh.view.*"%>
<%
	Language language = (Language) request.getSession().getAttribute("language");
	User user = (User) request.getSession().getAttribute("user");

%>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="ISO-8859-1">
			<title>NH</title>
			<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
			<!--  import main.css -->
			<link rel="stylesheet" href="main.css">
			<style>
				.previewimage
				{
					width:95%; 
					height:auto;
					border-radius:10px;
	
				}
				
				.house
				{
					margin-top:1%;
					padding:0.2%;
					border:1px solid #999;
					border-radius:10px;
				}
			</style>
		</head>
		<body>
			<div class="w3-container">
				<!--  menu -->
				<div class="w3-col m2 l2">
					<!--  import menu -->
					<jsp:include page="menu.jsp" />
				</div>			
				<div class="w3-col m8 l8">
					<h3> <%=user.getEmail() %></h3>
					<% for(Booking b:user.getBookings()){ %>
						<div class="booking">
							<%=language.translate("STAYEDAT") %> <%=b.getHouse().getName() %>
							<br />
							<%=b.getStart() %> to <%=b.getEnd() %>	
							<br />
							<%=language.translate("COST") %> <%=b.getCost() %> &euro;
						</div>
					<% } %>
				</div>
				<div class="w3-col m2 l2">
					&nbsp; <!--  spazio vuoto -->
				</div>			
			</div>
		</body>
</html>